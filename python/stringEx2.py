#아래의 장문에서 각 문자의 발생빈도를 세어 출력하고,
#출력은 빈도수가 높은 글자부터 출력한다.

str='''내가 그의 이름을 불러주기 전에는 그는 다만 하나의 몸짓에 지나지 않았다.
내가 그의 이름을 불러주었을 때, 그는 나에게로 와서 꽃이 되었다.
내가 그의 이름을 불러준 것처럼
나의 이 빛깔과 향기에 알맞는
누가 나의 이름을 불러다오.
그에게로 가서 나도
그의 꽃이 되고 싶다.

우리들은 모두
무엇이 되고 싶다.
너는 나에게 나는 너에게
잊혀지지 않는 하나의 눈짓이 되고 싶다. '''


print(str)

import operator
countDic ={}

for char in str : 
    if char in countDic :
        countDic[char]+= 1
    else:
        countDic[char]=1
    print(countDic)

    sortedList = sorted(countDic.items(),key=operator.itemgetter(1),reverse=True)
print(sortedList)