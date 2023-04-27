#물건을 수송하는 기차 여러대의 수송량을 합산하여 순위를 매기시오
#기차의 이름과 수송량은 아래와 같다
#토마스 5, 헨리 8,에드워드 9, 에밀리 5, 토마스 4, 헨리 7, 토마스 3, 에밀리 8, 퍼시 5, 고든 13


traninTupleList =[ ('토마스', 6), ("헨리", 8),( "에드워드", 9),("토마스", 4) ,("헨리",7) , ("토마스", 3),("에밀리", 8),("퍼시",5),("고든", 13) ]

traninDict={}

rank, curRank =1, 1

for traninTuple in traninTupleList : 
    trainName = traninTuple[0] # 기차이름
    trainWeight = traninTuple[1] # 수송량
    if trainName in traninDict : # 기차이름이 딕셔너리에 있다면
        traninDict[trainName]+= trainWeight #기차이름의 수송량에 수송량 누적
    else: 
        traninDict[trainName] = trainWeight #딕셔너리에 새로운 기차이름과 수송량 대입

print(traninDict)

import operator
trainSortedList=sorted(traninDict.items(), key=operator.itemgetter(1),reverse=True)
 
print(trainSortedList)

print('기차\t 총 수송량 \t 순위')
print('-----------------------------------------------')
print(trainSortedList[0][0], '\t', trainSortedList[0][1], '\t\t', curRank)
for i in range(1,len(trainSortedList)) :
    rank+=1
    if trainSortedList[i][1]==trainSortedList[i-1][1] : #현재 기차의 수송량이 전 기차의 수송량과 같다
        pass # 아무 할일이 없어 빠져나올 때 쓰는 코드
    else : 
        curRank= rank
        
    print(trainSortedList[i][0], '\t', trainSortedList[0][1], '\t\t', curRank)




