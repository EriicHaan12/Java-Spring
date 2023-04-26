#리스트 데이터 타입

list1 = [0,0,0,0]
sum=0
list1[0]=45
list1[1]=33
list1[2]=5
list1[3]=100


sum=list1[0]+list1[1]+list1[2]+list1[3]
print(sum)

list2 =[] #빈배열 생성
list2.append(2)
list2.append(3)
print(list2)

list3=[]
for i in range(1,6):
    list3.append(i)
print(list3)

#파이썬의 list는 모든 데이터타입을 다 원소로 가질 수 있다.
list4 = [10,20,30,'파이썬',False,3.14]
print(list4)

#list의 원소에 접근하는 방법
list5 = [10,20,30,40,50]
print(list5[-1]) #index 값에 음수를 붙이면 리스트의 끝에서 부터 역방향순으로 탐색 하게 된다.

print(list5[2:4]) #2번째 index로 부터 4-1 번째 index 값 까지 
print(list5[0:4])#0~3번째 index값 까지

print(list5[:3]) # 0~3-1 번째 index 값 까지

print(list5[3:]) #3번째 index 부터 끝 index값까지

print(list5[-2:-4]) # [] 출력 (역방향으로는 탐색이 안됨)
print(list5[-3:-1])

#2차원 배열 처럼 사용하기
list6 = [100,200]

list6[1]=[20,30]

print(list6) #기존에 1번째 index였던 200이 사라지고 그 안에 [20,30] 한 배열이 대입되어 2차원 배열이 만들어진다.

del(list6[1]) # 리스트 n번째 요소 삭제

print(list6)

#list 조작 함수
myList= [30,20,10]
print("현재 리스트 : %s " %myList )

myList.append(40)
print("현재 리스트 : %s " %myList )

print("리스트에서 pop()으로 꺼내기 : %s " %myList.pop() )#끝에서 하나 추출

print("현재 리스트 : %s " %myList )

myList.sort()
print("현재 리스트 : %s " %myList ) # 오름차순 정렬

print( "myList에서 20의 위치 : %d" %myList.index(20)) #  괄호안에 데이터 index위치 탐색( 없는 데이터 찾으라고 할 때는 예외가 발생) 

myList.reverse()
print("reverse 후 리스트 : %s" %myList) #현재 나열된 방향에서 역방향으로 list 전환

myList.insert(2,200)
print("insert 후 리스트 : %s" %myList) # 리스트의 2번째 인덱스에 200 값 넣기

myList.remove(20)
print("insert 후 리스트 : %s" %myList) #리스트에 있는 20값을 삭제

myList.extend([1000,2000,3000])
print("extend 후 리스트 : %s" %myList) 

print("myList의 요소 갯수 : %d" %len(myList)) #리스트에 있는 요소의 갯수 .length

myList.append(10)
print(myList.count(10)) #리스트에서 괄호안의 데이터 갯수 찾기

newList= sorted(myList) #정렬 이 후 새로운 list로 반환

print(myList)
print(newList)