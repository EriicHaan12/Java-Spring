a= 99
if a<100 :
    print("100보다 작다")
elif a==100 :
    print("100과 같다.") 
else:
     print("100보다 작지 않다.")


#리스트와 함께 사용하는 if문 (다른 프로그래밍 언어에서는 반복문을 돌린 후 해당 요소가 있는지 확인 해봐야 하지만, 파이썬에서는 if문으로 리스트의 모든 요소들을 비교 할 수 있다.)
favoriteFruits = ['사과','귤','포도','배','딸기']
if '참외' in favoriteFruits :
    print("참외가 있습니다.")

