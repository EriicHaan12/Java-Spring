
list1=[10,20,30,40,50]
print(list1[2:4])

#문자열처리의 기본: 리스트와 사용 방법이 비슷
str='우리나라 대한민국'
print(str[1:3])
print(str+'최고')
str= '최고'*5
print(len(str))



#문자열 관련 함수
str= 'Python is Best programming Language'
print(str.upper()) #대문자로
print(str.lower()) #소문자로

print(str.swapcase()) #대문자를 소문자로, 소문자를 대문자로
print(str.title())#문장 첫글자만 대문자로

str="Python is very simple.Python 예제 너무 구림~!"

print(str.count("Python")) #Python 단어가 나온 갯수

print(str.find("Python")) #indexOf() 와 동일한 기능, indexof처럼 찾을 단어가 없을 때는 -1을 반환

print(str.rfind("Python"))#lastIndextOf()와 동일
print(str.find("good")) # 
# print(str.index("good"))
# index도 마찬가지로 단어를 찾는 기능을 가졌지만
 # index는 찾을 단어가 없을 때 오류가 난다
print(str.startswith("Python")) #startswith()~으로 시작하는지 bool값 반환

print(str.endswith("!")) #startswith()~으로 시작하는지 bool값 반환

str= "  파    이      썬  "

print(str.strip())# 앞 뒤 공백 모두 제거
print(str.rstrip())# 뒤 공백 제거
print(str.lstrip())# 앞 공백 제거
print(str.replace(" ","")) #공백을 찾아서 모두 제거

str="하나:둘:셋:넷"

print(str.split(":")) # js의 split과 같은 함수 
#js에서는 배열로 반환 해줬지만, python에서는 리스트로 반환


before=['2023','04','28']
after= list(map(int,before)) 

#map : before의 모든 원소를 하나씩 꺼내 int()에 대입시켜 호출해 준다.

print(after)

print('1234'.isdigit())#숫자타입으로 반환 할 수 있는지 bool 타입으로 반환해줌
print('가나다라'.isdigit())

print('1234'.isalpha()) #영문자인지 확인 (bool타입 반환)
print('abcd'.islower()) #영문자인지 확인 (bool타입 반환)
print('    '.isspace()) #공백인지 확인 (bool타입 반환)