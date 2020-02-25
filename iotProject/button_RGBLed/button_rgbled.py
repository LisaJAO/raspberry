
from gpiozero import Button,RGBLED

button = Button(18)
rgbLed = RGBLED(17,27,22)

while True:
    if button.is_pressed:
        rgbLed.color = (1, 0, 0)
    else:
        rgbLed.color = (0, 1, 0)