import requests
from bs4 import BeautifulSoup
import pymysql

pageno=1
baseUrl="https://lemite.com/product"

targetUrl = "https://lemite.com/product/list.html?cate_no=43&page="+str(pageno)

headers = {"User-Agent" : "Mozilla/5.0"}

resp= requests.get(targetUrl, headers=headers)

resp.encoding='utf-8'
html=resp.text

if html is not None : #웹 문서를 로딩했다면... (로딩 됬을 때만 파싱되도록 설정)
    html = BeautifulSoup(html, "html.parser") #html로 파싱
    try: #아래의 코드를 수행해서
        products = html.find('ul',class_='prdList column4') #클래스명을 검색할려면 class_="클래스명" 
        
    except: #예외가 발생 했다면
        print("파싱할 상품이 없다....")
    else : #예외가 발생하지 않았다면
        products= products.find_all("li",class_="item xans-record-")
        
        for item in products :
                
            productName =item.find('p',class_='name').text.split(":")[1].strip()
            
            
            detailPage =baseUrl + item.find('p', class_='name').find('a').attrs['href']
            pkStartPos=detailPage.find('product_no=')+len('product_no=')
            pkEndPos=detailPage.find("&",pkStartPos)
            
            productNo=detailPage[pkStartPos:pkEndPos]
            thumbNailImg="https:"+item.find('div', class_='prdimg').find('img').attrs['src']

            description= item.find('li',class_='xans-record-').text.split(':')[1].strip()

            productPrice = item.find('li',class_="xans-record-").find_next('li').text.split(':')[1].replace(',','').replace('원','').strip
            
            print(productNo, productName,productPrice,thumbNailImg, description,detailPage)
            print("-------------")
