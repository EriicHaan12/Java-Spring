import turtle
import random

# 함수 선언 부
def screenLeftClick(x,y) :
    global r, g ,b    #함수 외부에서 선언된 r,g,b 변수를 함수 내부에서도 사용하겠다
    turtle.pencolor((r, g, b))
    turtle.pendown()
    turtle.goto(x, y) # x, y  좌표로 거북이를 이동 시키기

def screenRightClick(x,y):
    #주어진 x,y 좌표로 거북이 이동시키기(그림이 그려지면 안됨)
    turtle.penup()
    turtle.goto(x,y)

def screenMidClick(x,y):
    #랜덤하게 색상 변경
     global r, g ,b, pSize
     r= random.random()
     g= random.random()
     b= random.random()
     pSize=random.randrange(1,10)
     turtle.pensize(pSize)
    

#디버그 실행 버튼 f5키 

pSize = 10 # 펜의 크기
r, g, b = 0.0, 0.0, 0.0 #여러개의 변수를 한꺼번에 선언 할 수 있고 초기화 할 수 도 있다.

turtle.title("거북이로 그림 그리기")
turtle.shape("turtle")
turtle.pensize(pSize)

turtle.onscreenclick(screenLeftClick, 1) # 마우스 왼쪽 버튼이 클릭되면 screenLeftClick() 함수가 호출된다.
turtle.onscreenclick(screenRightClick, 3) #마우스 오른쪽 버튼이 클릭되면 screenRightClick()함수가 호출된다
turtle.onscreenclick(screenMidClick,2)# 마우스 휠 버튼이 클릭되면 screenMidClick()함수가 호출된다.


turtle.done()