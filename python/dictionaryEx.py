#딕셔너리 생성
dict1 = {1:'a', 2:'b',3:'c'}
print(dict1)


student1 ={'학번' : 23042701,'이름': '둘리','나이':20}

#생성된 딕셔너리에 키와 값을 추가 할 수 있다.
student1['연락처']='010-1234-5678'

print(student1)

#키로 값을 찾는 방법
print(student1['학번'])
print(student1.get('이름'))
# 존재하지 않는 key에 접근하려 하면 key Error가 난다
print(student1.keys())

#student1 딕셔너리가 가지고 있는 모든 key를 반환 (dict_keys[])
print(student1.keys())
keys=list(student1.keys())
print(keys)


#student1 딕셔너리가 가지고 있는 모든 values를 반환
values =list(student1.values())
print(values)

#student1 딕셔너리의 모든 값을 tuple형태로 반환
print(student1.items())

#딕셔너리에 해당 키가 있는지 여부 검사
print('이름' in student1)
print('주소' in student1)

if '주소' in student1 :
    print(student1['주소'])
else:
    print('주소가 없습니다!')

#student1에 있는 모든 key와 value를 출력(반복문 이용)

for i in student1.keys() : 
    print('%s : %s' %(i, student1[i]))

#딕셔너리에 있는 데이터를 정렬
import operator
trainDict = {'Thomas' : '토마스','Edward' : '에드워드', 'Henry' : '헨리', 'Gothen' : '고든', 'James' : '제임스'}
sortedTrainList= sorted(trainDict.items(), key = operator.itemgetter(0))
print(sortedTrainList)
