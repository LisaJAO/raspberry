#/usr/bin/python3.7
import RPi.GPIO as GPIO
import time

def setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(25, GPIO.OUT)
    GPIO.setwarnings(False)
    #GPIO.output(25, GPIO.HIGH)

def blink():
    while True:
        GPIO.output(25, GPIO.HIGH)
        time.sleep(1)
        GPIO.output(25, GPIO.LOW)
        time.sleep(1)

if __name__ ==  '__main__':
    setup()
    try:
        blink()
    except:
        print('except')
        GPIO.output(25, GPIO.LOW)
        GPIO.cleanup()


