<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<div id="wrap">
    <div class="contents-wrap">
        <div class="title">
            <h1>와이파이 정보 구하기</h1>
        </div>

        <div class="gnb-wrap">
            <ul class="gnb">
                <li><a href="index.jsp">홈</a> </li>
                <li><a href="history.jsp">위치 히스토리 목록</a> </li>
                <li><a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> </li>
            </ul>
        </div>

    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <%--lat--%>
                <th>X좌표</th>
                <%--lnt--%>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>

</div>

<script type="text/javascript">
    const tbodyEl = document.querySelector('tbody');

    const submitReqWifiInfo = () => {
        const xhr = new XMLHttpRequest();
        let element = '';

        xhr.open('GET', 'history');

        xhr.onreadystatechange = (e) => {
            const {target} = e;

            if (target.readyState === XMLHttpRequest.DONE) {
                let result = e.target?.response;

                if (result) {
                    result = JSON.parse(result);

                    result.map((item) => {
                        const {ID, LAT, LNT, SEARCH_DATE} = item;
                        element += `
                            <tr>
                                <td>${'${ID}'}</td>
                                <td>${'${LAT}'}</td>
                                <td>${'${LNT}'}</td>
                                <td>${'${SEARCH_DATE}'}</td>
                                <td><button class="delete-btn" type="button" data-id="${'${ID}'}">삭제</button></td>
                            </tr>
                        `;
                    })

                    tbodyEl.insertAdjacentHTML('beforeend', element);
                }
            }
        }

        xhr.send();
    }

    const deleteSearchHistory = (e) => {
        const xhr = new XMLHttpRequest();
        let idVal = e.target.getAttribute("data-id");

        let params = "id="+idVal;
        console.log(idVal);

        xhr.open('POST', 'history_delete?'+params, true);

        xhr.onreadystatechange = (e) => {
            const {target} = e;

            if(target.readyState === XMLHttpRequest.DONE){
                console.log('삭제')
            }
        }
        xhr.send();

    }



    window.onload = () => {
        submitReqWifiInfo();

        window.addEventListener('click', e => {
            if(e.target.className === 'delete-btn'){
                deleteSearchHistory(e);
            }
        })
    }
</script>
</body>
</html>