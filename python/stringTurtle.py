#문자열을 입력 받고,
#입력 받은 문자열을 한 글자씩 임의의 크기와 색상으로 임의의 위치에 거북이가 출력되도록


import turtle
import random
from tkinter.simpledialog import *

inputStr= ''
sWidth, sHeight= 500, 700
tX,tY,tSize,r,g,b = [0]*6

turtle.title("거북이 글씨 쓰기")
turtle.shape("turtle")
turtle.setup(width=sWidth+50, height=sHeight+50)
turtle.screensize(sWidth,sHeight)
turtle.penup()

inputStr =askstring("문자열을 입력","문자열을 입력하세요")

for char in inputStr :
    tX=random.randrange(-250,250)
    tY=random.randrange(-250,250)
    r=random.random();g=random.random();b=random.random()
    tSize=random.randrange(10,50)
    turtle.goto(tX,tY)

    turtle.pencolor(r,g,b)
    turtle.write(char,font=("Arial",tSize,"normal"))
turtle.done()


