<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="./assets/style/style.css" />
</head>
<body>

<div id="wrap" class="load-wifi-wrap">
    <div class="contents-wrap">
        <h1>LOADING...</h1>

        <a href="index.jsp">홈으로 가기</a>

    </div>

</div>

<script src="assets/js/index.js"></script>
<script type="text/javascript">
    const textElement = document.querySelector('h1');

    let params = {
        url: 'save-wifi-list',
        method: 'GET'
    };

    const callbackRequest = (e) => {
        try{
            let count = e.target.response;
            textElement.innerHTML = `\${count}개의 WIFI 정보를 정상적으로 저장했습니다.`;
        } catch (err){
            textElement.innerHTML = `WIFI 정보 저장을 실패했습니다.`;
            console.log("에러발생", e);
        }
    }

    requestApi(params, callbackRequest, textElement);
</script>
</body>
</html>