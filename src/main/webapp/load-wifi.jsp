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
        <h1>
            WIFI 정보를 정상적으로 저장하였습니다.
        </h1>

        <a href="index.jsp">홈으로 가기</a>

    </div>

</div>

<script type="text/javascript">
    const submitReqWifiInfo = () => {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', 'get-wifi-list');

        xhr.onreadystatechange = (e) => {
            const {target} = e;

            if(target.readyState === XMLHttpRequest.DONE){
                try{
                    console.log(e);
                    console.log('시작');

                } catch (err){
                    console.log("에러발생", e);
                }
            } else {
                console.log("error")
            }
        }

        xhr.send();
    }

    // window.onload = () => {
        submitReqWifiInfo();
    // }
</script>
</body>
</html>