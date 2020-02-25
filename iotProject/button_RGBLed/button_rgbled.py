
from gpiozero import Button,RGBLED
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

def rgbListener(event):
    print(event.data)
    data = event.data
    if event.path == "/":
        r = data['R']
        g = data['G']
        b = data['B']
        rgbLed.color = (r/100, g/100, b/100)
    elif event.path == "/R":
        rgbLed.red = data/100
    elif event.path == "/G":
        rgbLed.green = data / 100
    elif event.path == "/B":
        rgbLed.blue = data/100

if __name__ == "__main__":
    cred = credentials.Certificate("/home/pi/Documents/certificate/raspberryfirebase-firebase-adminsdk-y4f0x-cf4be2ca1a.json")
    firebase_admin.initialize_app(cred,{'databaseURL':'https://raspberryfirebase.firebaseio.com/'})
    rgb = db.reference('iot20191126/RGBLed')
    button = Button(18)
    rgbLed = RGBLED(17,27,22)
    rgb.listen(rgbListener)

    while True:
        if button.is_pressed:
            rgbLed.color = (1, 0, 0)

        else:
            rgbLed.color = (0, 1, 0)
