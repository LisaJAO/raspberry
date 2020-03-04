#!/usr/bin/python3.7

'''
控制Relay
'''
from firebase_admin.exceptions import FirebaseError
from gpiozero import LED
from time import sleep
from tkinter import *
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db


class App:
    def __init__(self,window):
        #建立gpiozero led
        self.red = LED(18)


        #建立window and Layout
        self.buttonName = StringVar()
        mainFrame = Frame(window, borderwidth=2, relief=GROOVE)
        Button(mainFrame,textvariable=self.buttonName,command=self.callback).pack(expand=YES, fill=BOTH, padx=40, pady=25)

        mainFrame.pack(expand=YES,fill=BOTH, padx=5, pady=20)

        #firebase init
        cred = credentials.Certificate("/home/pi/Documents/certificate/raspberryfirebase-firebase-adminsdk-y4f0x-cf4be2ca1a.json")
        firebase_admin.initialize_app(cred, {
            'databaseURL': 'https://raspberryfirebase.firebaseio.com/'
        })

        self.ledNote = db.reference('/iot20191126/ledState')
        #register listen self.ledNote changeEvent
        try:
            self.ledNote.listen(self.valueChangeLister)
        except FirebaseError:
            print("listen Error")

    def callback(self):
        try:
            if self.buttonName.get() == '開燈':
                self.ledNote.set(True)
            else:
                self.ledNote.set(False)
        except:
            print("firebase Error")

    def valueChangeLister(self,event):
        '''
        print(event)
        print(event.data)
        print(event.path)
        print(event.event_type)
        print(self.ledNote.get())
        '''
        if event.data:
            self.red.on()
            self.buttonName.set("關燈")
        else:
            self.red.off()
            self.buttonName.set("開燈")




if __name__ == '__main__':
    window = Tk()
    window.title('LED Control')
    window.geometry("300x200")
    window.option_add("*Font",('verdana', 18))
    app = App(window)
    window.mainloop()