#!/usr/bin/python3.7

'''
控制Led
'''

from gpiozero import LED
from time import sleep
red = LED(25)

if __name__ == '__main__':
    while True:
        red.on()
        sleep(1)
        red.off()
        sleep(1)