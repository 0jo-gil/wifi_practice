
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>

    <link rel="stylesheet" href="./assets/style/style.css" />
</head>
<body>
    <div id="wrap">
        <div class="title">
            <h1>와이파이 정보 구하기</h1>
        </div>
        <%-- 위치 검색 입력 폼 시작 --%>
        <div class="gnb-wrap">
            <ul class="gnb">
                <li><a href="index.jsp">홈</a> </li>
                <li><a href="history.jsp">위치 히스토리 목록</a> </li>
                <li><a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> </li>
            </ul>
        </div>
        <form class="location-info-form" action="/history" method="POST">
            <fieldset>
                <legend>공공와이 파이 위치정보 입력 폼</legend>
                <div class="form-item">
                    <label for="lat">LAT: </label>
                    <input type="text" name="lat" id="lat"/>
                </div>

                <div class="form-item">
                    <label for="lnt">LNT: </label>
                    <input type="text" name="lnt" id="lnt"/>
                </div>

                <button type="button" class="form-btn location-btn">내 위치정보 가져오기</button>
                <button type="button" class="form-btn search-btn">근처 WIFI정보 보기</button>

            </fieldset>

        </form>

        <table>
            <thead>
            <tr>
                <th>거리(km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
            </thead>
        </table>
    </div>


    <script>
        const locationBtnEl = document.querySelector('.location-btn');
        const searchBtnEl = document.querySelector('.search-btn');
        const locationInfoForm = document.querySelector('.location-info-form');

        const geolocation = navigator.geolocation;


        const locationValue = ({coords}) => {
            let lat = coords.latitude;
            let lnt = coords.longitude;

            setInputValue('lat', lat);
            setInputValue('lnt', lnt).then(() => {
                console.log('완료');

                document.querySelector('.modal-wrap').remove();
            });
        }

        const setInputValue = async (key, value) => {
            const element = document.querySelector("input[name="+ key +"]");
            element.value = value;
        }

        const getLocation = async (e) => {
            e.preventDefault();
            if(!geolocation) throw "위치정보 지원 불가!";
            await geolocation.getCurrentPosition(locationValue);
        }

        const loadingModal = (title) => {
            console.log(title);
            const element = `
                <div class="modal-wrap">
                    <h1>LOADING...</h1>
                </div>
            `

            document.body.insertAdjacentHTML('beforeend', element);
        }


        // 위치정보 가져오기 이벤트
        locationBtnEl.addEventListener('click', (e) => {
            getLocation(e);
            loadingModal('사용자 위치정보');
        });
        //
        // const onSubmitHandler = () => {
        //     const data = new FormData(locationInfoForm);
        //
        //     let latValue = data.get('lat');
        //     let lntValue = data.get('lnt');
        //
        //     let params = "lat="+latValue+"&lnt="+lntValue;
        //
        //     callPostHistory(params);
        // }
        //
        //
        // const callPostHistory = (params) => {
        //     const xhr = new XMLHttpRequest();
        //
        //     xhr.open('POST', "history?"+params, true);
        //
        //     xhr.onreadystatechange = (e) => {
        //
        //         const {target} = e;
        //
        //         if(target.readyState === XMLHttpRequest.DONE){
        //             console.log(e);
        //             console.log('시작');
        //         }
        //     }
        //
        //     xhr.send();
        // }
        //
        //
        // searchBtnEl.addEventListener('click', (e) => {
        //     // e.preventDefault();
        //     onSubmitHandler();
        // })




    </script>
</body>
</html>