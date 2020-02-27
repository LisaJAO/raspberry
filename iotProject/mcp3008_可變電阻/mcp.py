#!/usr/bin/python3.7
'''
MCP3008 ,可變電阻
'''

from gpiozero import MCP3008
from tkinter import *

channel0 = MCP3008(0)

class App():
    def __init__(self,win):
        #tkinter
        mainFrame = Frame(win,borderwidth=2,relief=GROOVE)
        displayBar = Scale(mainFrame, from_=0, to=100, orient=HORIZONTAL,width=100)
        displayBar.pack()
        mainFrame.pack()

if __name__ == '__main__':
    window = Tk()
    window.title("MCP3008_可變電阻")
    window.option_add("*font",('verdana',18,'bold'))
    window.option_add('*background','#333333')
    window.option_add('*foreground','#ffffff')
    app = App(window)
    window.mainloop()
