#단순 for문
for i in range(1, 6) : 
    #end = 을 생략하면 print 를 수행한 뒤 "\n"까지 출력해 줄바꿈 하게 된다.
    print("%d " %i, end=" ")

print()
#1부터 100까지 합
sum = 0
for i in range(1,101) : 
    sum+=i
print("1부터 100까지의 합 : %d" %sum )

#2개의 for문을 이용하여 한단을 찍고 줄바꿈 하도록 하는 구구단을 완성하세요 (1단 부터 9단까지)


for i in range(1,10) :
    for j in range(1,10) :
        print("%d * %d= %d" %(i, j, i*j), end=" " )
    print()    