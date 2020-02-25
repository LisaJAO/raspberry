
from gpiozero import Button,RGBLED
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

def rgbListener(event):
    print(event.data)

if __name__ == "__main__":
    cred = credentials.Certificate("/home/pi/Documents/certificate/raspberryfirebase-firebase-adminsdk-y4f0x-cf4be2ca1a.json")
    firebase_admin.initialize_app(cred)
    rgb = db.reference('iot20191126/RGBLed')
    rgb.listen(rgbListener)
    button = Button(18)
    rgbLed = RGBLED(17,27,22)

    while True:
        if button.is_pressed:
            rgbLed.color = (1, 0, 0)

        else:
            rgbLed.color = (0, 1, 0)
