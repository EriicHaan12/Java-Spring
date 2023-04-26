#tuple 생성

tuple1=  (10,20,30,40,50)

print(tuple1)
# tuple1.append(40) # 튜플은 읽기 전용 이므로 예외가 발생함
# tuple1[0] =100  #튜플은 데이터 수정도 되지 않는다.
# 주로 상수로 지정할 때 많이 쓰인다.

print(tuple1[0]) 
#데이터의 접근은 list에서처럼 접근 가능하다.
print(tuple1[1:3])

#tuple의 삭제

#del(tuple1[1]) # tuple의 요소는 따로 삭제가 안된다.

# del(tuple1) # tuple list 자체는 삭제가 가능하다

#튜플을 list로 바꾸기
myList =  list(tuple1) # tuple로 지정된 list를 새로운 list로 할당 할 수 있다.(얕은 복사 개념?)
print(myList)

#list를 튜플로 바꾸기
list2 = [100,200,300]
myTuple = tuple(list2) # 이렇게 되면 myTuple은 튜플의 속성을 띄게 된다.
