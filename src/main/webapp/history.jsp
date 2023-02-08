<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="./assets/style/style.css" />
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

<script src="assets/js/index.js"></script>
<script type="text/javascript">
    const tbodyEl = document.querySelector('tbody');

    let params = {
        url: 'location-history',
        method: 'GET'
    }
    const callbackRequest = (e) => {
        let result = JSON.parse(e.target.response);
        let element = '';
        console.log(result);

        result.map(item =>
            element += `
                <tr>
                    <td>\${item.ID}</td>
                    <td>\${item.LNT}</td>
                    <td>\${item.LAT}</td>
                    <td>\${item.SEARCH_DATE}</td>
                    <td><button class="delete-btn" type="button" data-id="\${item.ID}">삭제</button></td>
                </tr>
            `
        )
        document.querySelector('table').insertAdjacentHTML('beforeend', element);
    }

    requestApi(params, callbackRequest);

    const callbackDeleteRequest = (e) => {
        if(e.target.status === 200){
            console.log(e.target)
        } else {
            alert('와이파이 위치 히스토리 목록 삭제 실패');
        }
    }



    window.onload = () => {
        window.addEventListener('click', e => {
            if(e.target.className === 'delete-btn'){
                let idValue = e.target.getAttribute("data-id");
                let deleteParams = {
                    url: 'location-history?'+"id="+idValue,
                    method: 'DELETE'
                }

                requestApi(deleteParams, callbackDeleteRequest)
                    .then(() => e.target.closest('tr').remove())
                    .catch((err) => console.error(err));
            }
        })
    }
</script>
</body>
</html>