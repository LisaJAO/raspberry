#/usr/bin/python3.7
import RPi.GPIO as GPIO



if __name__ ==  '__main__':
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(25,GPIO.OUT)
    GPIO.output(25,GPIO.HIGH)
    print("執行")


