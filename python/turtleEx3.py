#거북이 세마리가 임의로 화면을 돌아다니게 한다.

#세마리 중 서로 만나는 거북이가 있다면 움직임을 멈추고 모든 거북이의 크기를 세배로 키운다.
import turtle
import random
import math

#초기화 시키기 (None = null)
turtle1, turtle2, turtle3 = [None] * 3 #거북이 세마리의 객체

#숫자값 초기화 시키기
turtle1X,turtle1Y,turtle2X,turtle2Y,turtle3X,turtle3Y =[0]*6 #거북이 세마리의 x,y 좌표
screenWidth, screenHeight = 300,300 # 윈도우 사이즈

turtle.title("거북이 만나기")
turtle.setup(width=screenWidth + 50, height=screenHeight+50)
turtle.screensize(screenWidth,screenHeight)
#각 거북이의 초기값 위치 설정
turtle1 = turtle.Turtle("turtle")
turtle1.color("red")
turtle1.penup()

turtle2 = turtle.Turtle("turtle")
turtle2.color("green")
turtle2.penup()

turtle3 = turtle.Turtle("turtle")
turtle3.color("blue")
turtle3.penup()

turtle1.goto(20, 20)
turtle2.goto(0, 0)
turtle3.goto(100, 100)

#무한 반복
while True: 
    angle = random.randrange(0, 360) #0보다 크거나 같고 360보다 작은 난수
    dist = random.randrange(1, 50) #1보다 크거나 같고 50보다 작은 난수
    turtle1.left(angle); turtle1.forward(dist)
    

    angle = random.randrange(0, 360) 
    dist = random.randrange(1, 50) 
    turtle2.left(angle); turtle2.forward(dist)

    angle = random.randrange(0, 360) 
    dist = random.randrange(1, 50) 
    turtle3.left(angle); turtle3.forward(dist)


#각 거북이들의 좌표를 구하자
    turtle1X = turtle1.xcor(); turtle1Y = turtle1.ycor()
    turtle2X = turtle2.xcor(); turtle2Y = turtle2.ycor()
    turtle3X = turtle3.xcor(); turtle3Y = turtle3.ycor()

#거북이들 사이의 거리

#turtle1과 turtle2 사이 거리
    if math.sqrt(((turtle2X-turtle1X)**2) + ((turtle2Y-turtle1Y)**2) )<=20 or math.sqrt(((turtle2X-turtle3X)**2) + ((turtle2Y-turtle3Y)**2)) <=20 or math.sqrt(((turtle1X-turtle3X)**2) + ((turtle1Y-turtle3Y)**2) <=20) :
        turtle1.turtlesize(3); 

       
    turtle.done()
