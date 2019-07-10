# CarrotMarket2
 
중고거래 플랫폼인 당근마켓을 클론 해보았습니다.

## 목차
* 사용기술
* 주요기능

## 사용기술
* **OS** : Android, Linux(Ubuntu)

* **언어** : Java, php, javascript

* **웹서버** : Nginx

* **채팅서버** : Node.js

* **데이터베이스** : MariaDB

* **서버 호스팅** : AWS EC2

* **네트워크 통신** : Retrofit2

* **라이브러리** : gson, okhttp3, facebook_account_kit, butterknife, picasso, glide, google map, otto, socket.io

## 주요기능
**1.회원가입**
 * Facebook_account_kit의 문자인증 서비스를 이용하여, 회원가입을 할 수 있도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.gif" width="300" style="max-width:100%;" aligncenter />

**2.로그인**
 * 로그인도 마찬가지로 문자인증을 통해 할 수 있습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EB%A1%9C%EA%B7%B8%EC%9D%B8.gif" width="300" style="max-width:100%;" />


**3.동네인증**
 * 자신이 설정한 동네와 Google Map을 통한 실제 위치가 일치해야 보다 많은 서비스를 사용할 수 있도록 하였습니다. 
 
 * 동네인증 범위 : 
 주변동네의 범위는 자신이 설정한 동의 '구' 안으로 설정하였습니다. 따라서 자신이 설정한 동의 '구'와 구글 맵이 포착한 실제 '구'가 일치하면 동네인증이 가능하도록 하였습니다. 
 
 * 주소값은 '통계분류포털' 자료실에서 제공하는 한국행정구역분류 현황 자료를 csv 파일로 변환하여 제 개인 DB에서 제공하도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EB%8F%99%EB%84%A4%EC%9D%B8%EC%A6%9D.gif" width="300" style="max-width:100%;" />

**4.게시글 작성**
  

<img 
src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0.gif" width="300" style="max-width:100%;" />


**5.다른 사용자 게시글 보기**

 
<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EB%8B%A4%EB%A5%B8%EC%82%AC%EC%9A%A9%EC%9E%90%EA%B2%8C%EC%8B%9C%EA%B8%80%EB%B3%B4%EA%B8%B0.gif" width="300" style="max-width:100%;" />
 

**6.동네추가**
 * 최대 두개의 동네를 선택하여 활동할 수 있도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EB%8B%A4%EB%A5%B8%EB%8F%99%EB%84%A4%EB%93%B1%EB%A1%9D.gif" width="300" style="max-width:100%;" />

**7.게시글 끌올하기**
 * 최대 10번 까지 게시글 시간을 업데이트 하여 게시글이 상단에 노출될 수 있도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EB%81%8C%EC%96%B4%EC%98%AC%EB%A6%AC%EA%B8%B0.gif" width="300" style="max-width:100%;" />

**8.게시글 필터링**
 * 사용자가 카테고리를 선택하여 게시글을 받아볼 수 있도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%ED%95%84%ED%84%B0.gif" width="300" style="max-width:100%;" />

**9.게시글 삭제하기**

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EA%B2%8C%EC%8B%9C%EA%B8%80%20%EC%82%AD%EC%A0%9C.gif" width="300" style="max-width:100%;" />

**10.게시글 관심목록에 담기**
 * 마음에 드는 상품들을 관심목록에 담아 시간이 지나더라도 쉽게 찾아 볼수 있도록 하였습니다.  

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%A2%8B%EC%95%84%EC%9A%94.gif" width="300" style="max-width:100%;" />

**11.다른 사용자 모아보기**
 * 자신이 좋아하는 사용자들의 게시글만 따로 모아 볼수 있도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EB%AA%A8%EC%95%84%EB%B3%B4%EA%B8%B0.gif" width="300" style="max-width:100%;" />

**12.검색**
 * 자신이 설정한 동네 안에서의 게시글을 검색하고, CarrotMarket의 사용자들을 검색할 수 있습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EA%B2%80%EC%83%89.gif" width="300" style="max-width:100%;" />

**13.게시글 상태 변경**
 * 게시글은 판매중, 예약중, 거래완료, 그리고 숨김 상태로 변경할 수 있습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%98%88%EC%95%BD%EA%B1%B0%EB%9E%98%EC%99%84%EB%A3%8C%EC%88%A8%EA%B9%80.gif" width="300" style="max-width:100%;" />

**14.채팅**
 * 중고거래는 채팅을 통해 가능합니다. 
 * 채팅 서버는 Node.js로 구현해보았습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EC%B1%84%ED%8C%851.gif" width="700" style="max-width:100%;" />

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EC%B1%84%ED%8C%852.gif" width="700" style="max-width:100%;" />
	
**15.거래후기 남기기**
 * 거래 후 서로 거래 후기를 남겨, 계속해서 매너 있는 거래를 할 수 있도록 하였습니다. 

<img src="https://github.com/Gooreum/CarrotMarket2/blob/master/readmeImages/%EA%B1%B0%EB%9E%98%ED%9B%84%EA%B8%B0.gif" width="700" style="max-width:100%;" />
