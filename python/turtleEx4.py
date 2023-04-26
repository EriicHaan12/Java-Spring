#거북이 100마리의 리스트를 아래와 같이 생성 후 거북이 100마리가 화면 중앙에서 임의의 위치로 차례대로 이동 하도록 한다.

#거북이는 화면 중앙(0,0)에서 지정된 위치(x,y)까지 선을 그리며 이동하고, 크기와 색상은 임의의 값으로 한다.
#[거북이모양, x위치,y위치, 거북이 크기, 거북이 색상(R),거북이 색상(G),거북이 색상(B)]

#거북이 100마리를 하나의 리스트에 담아 관리한다.
#기본 화면 사이즈는 500,500 으로 한다.
#거북이의 t사이즈는 1~3의 난수로 정의 한다.

import turtle
import random
myTurtle, tX,tY,tColor, tSize, tShape = [None]* 6

shapeList=[]
playerTurtles=[]
sWidth, sHeight= 500,500

turtle.title("리스트를 활용한 거북이 그림 그리기")
turtle.setup(sWidth+50, sHeight+50)
turtle.screensize(sWidth,sHeight)

shapeList = turtle.getshapes() #커서 모양 지정

tSize = random.randrange(1,4)




for i in range (0,100) : 
    random.shuffle(shapeList) #랜덤하게 섞기
    myTurtle=turtle.Turtle(shapeList[0]) #거북이 모양 설정

    #전체 스크린 크기가 500이기 때문에 0,0 기준으로 좌표의 최대, 최소값은 250~-250 이 된다.

    #이동할 좌표
    tX= random.randrange(-250, 250) #-250~250 사이 숫자로 랜덤하게 x좌표 위치 설정
    tY=random.randrange(-250, 250)
    r= random.random(); g= random.random(); b= random.random() #랜덤한 색상   
    playerTurtles.append([myTurtle, tX, tY, tSize, r, g, b])

turtle.done()
    
print(playerTurtles)
for tList in playerTurtles :
    myTurtle = tList[0]
    myTurtle.color(tList[4],tList[5],tList[6])
    myTurtle.pencolor(tList[4],tList[5],tList[6])
    myTurtle.tutlesize(tList[3])
    myTurtle.goto(tList[1],tList[2])

turtle.done()