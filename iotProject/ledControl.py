#!/usr/bin/python3.7

'''
控制Led
'''

from gpiozero import LED
from time import sleep
from tkinter import *
import firebase_admin
from firebase_admin import credentials


class App:
    def __init__(self,window):
        #建立gpiozero led
        self.red = LED(25)


        #建立window and Layout
        self.buttonName = StringVar()
        mainFrame = Frame(window, borderwidth=2, relief=GROOVE)
        Button(mainFrame,textvariable=self.buttonName,command=self.callback).pack(expand=YES, fill=BOTH, padx=40, pady=25)
        self.buttonName.set("開燈")
        mainFrame.pack(expand=YES,fill=BOTH, padx=5, pady=20)

        #firebase init
        cred = credentials.Certificate("/home/pi/Documents/certificate/raspberryfirebase-firebase-adminsdk-y4f0x-cf4be2ca1a.json")
        firebase_admin.initialize_app(cred)

    def callback(self):
        if self.buttonName.get() == '開燈':
            self.buttonName.set('關燈')
            self.red.on()
        else:
            self.buttonName.set('開燈')
            self.red.off()


if __name__ == '__main__':
    window = Tk()
    window.title('LED Control')
    window.geometry("300x200")
    window.option_add("*Font",('verdana', 18))
    app = App(window)
    window.mainloop()