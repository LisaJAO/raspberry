package com.roberthsu2003.raspberry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.opencensus.resource.Resource;
class RecordsAdapter extends ArrayAdapter<Record>{
    public RecordsAdapter(Context context, List<Record> object){
        super(context,0, object);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_record,parent,false);
        }

        TextView idTextView = (TextView) convertView.findViewById(R.id.cardID);
        Record record = getItem(position);
        idTextView.setText(record.cardID);
        return convertView;
    }
}

class Record{
    public String cardID;
    public String date;
    public long timestamp;

    public Record(){}
    public Record(String cardID, String date, long timestamp){
        this.cardID = cardID;
        this.date = date;
        this.timestamp = timestamp;
    }
}

public class MainActivity extends ListActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //getResource
        Resources res = getResources();
        names = res.getStringArray(R.array.userName);


        /*
        ArrayList<String> names = new ArrayList<String>();
        names.add("Robert");
        names.add("John");
        names.add("Tom");
        names.add("Alice");
        */

        //getListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);



        mAuth = FirebaseAuth.getInstance();
        //firestore get 30 pieces data
        Query doorRef = firestore.collection("Doors").orderBy("timestamp", Query.Direction.ASCENDING).limit(30);
        doorRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            List<Record> records = new ArrayList<Record>();
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    /*
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Firestore", document.getId() + " => " + document.getData());
                        Record record = document.toObject(Record.class);
                        records.add(record);
                    }

                     */
                }else{
                    Log.d("Firestore", "firestore read fails");
                }
                /*
                RecordsAdapter recordArrayAdapter = new RecordsAdapter(MainActivity.this,records);

                MainActivity.this.setListAdapter(recordArrayAdapter);

                 */
            }
        });



        firestore.collection("Doors").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d("listener","listener err",e);
                    return;
                }

                for(DocumentChange dc:snapshots.getDocumentChanges()){
                    switch(dc.getType()){
                        case ADDED:
                            Log.d("listener","add:" + dc.getDocument().getData());
                        case REMOVED:
                            Log.d("listener","removed:" + dc.getDocument().getData());
                        case MODIFIED:
                            Log.d("listener","modified:" + dc.getDocument().getData());
                    }
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            Log.d("Login","no login");
            mAuth.signInAnonymously();
        }else{
            Log.d("Login","logined");
            Log.d("Login",currentUser.getUid());
        }
    }
}
