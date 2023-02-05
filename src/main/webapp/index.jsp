
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

    <script src="./assets/js/index.js"></script>
    <script>
        const locationBtnEl = document.querySelector('.location-btn');
        const searchBtnEl = document.querySelector('.search-btn');
        const locationInfoForm = document.querySelector('.location-info-form');

        const geolocation = navigator.geolocation;

        // 위치정보 가져오기 이벤트
        locationBtnEl.addEventListener('click', (e) => {
            getLocationHandler(e);
        });

        const callbackRequest = (e) => {
            let result = JSON.parse(e.target.response);
            let element = '';
            console.log(result);

            result.map(item =>
                element += `
                    <tr>
                        <td>\${item.DISTANCE}</td>
                        <td>\${item.X_SWIFI_MGR_NO}</td>
                        <td>\${item.X_SWIFI_WRDOFC}</td>
                        <td>\${item.X_SWIFI_MAIN_NM}</td>
                        <td>\${item.X_SWIFI_ADRES1}</td>
                        <td>\${item.X_SWIFI_ADRES2}</td>
                        <td>\${item.X_SWIFI_INSTL_FLOOR}</td>
                        <td>\${item.X_SWIFI_INSTL_TY}</td>
                        <td>\${item.X_SWIFI_INSTL_MBY}</td>
                        <td>\${item.X_SWIFI_SVC_SE}</td>
                        <td>\${item.X_SWIFI_CMCWR}</td>
                        <td>\${item.X_SWIFI_CNSTC_YEAR}</td>
                        <td>\${item.X_SWIFI_INOUT_DOOR}</td>
                        <td>\${item.X_SWIFI_REMARS3}</td>
                        <td>\${item.LNT}</td>
                        <td>\${item.LAT}</td>
                        <td>\${item.WORK_DTTM}</td>
                    </tr>
                `
            )

            document.querySelector('table').insertAdjacentHTML('beforeend', element);
        }

        const callbackRequestHistory = (e) => {
            console.log(e);
        }

        const onSubmitHandler = () => {
            const data = new FormData(locationInfoForm);

            let latValue = data.get('lat');
            let lntValue = data.get('lnt');

            let params = {
                url: "get-wifi-list?" + "lat="+latValue+"&lnt="+lntValue,
                method: 'GET'
            };

            let historyParams = {
                url: "location-history?" + "lat="+latValue+"&lnt="+lntValue,
                method: 'POST'
            }

            requestApi(params, callbackRequest)
                .then(() => requestApi(historyParams, callbackRequestHistory))
                .catch((error) => console.error(error));
        }
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
        searchBtnEl.addEventListener('click', (e) => {
            // e.preventDefault();
            onSubmitHandler();
        })




    </script>
</body>
</html>