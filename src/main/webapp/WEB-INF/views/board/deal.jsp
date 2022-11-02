<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="../common/head.jsp" %>
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    
    <title>deal list</title>
</head>
<body>
<%@include file="../common/header.jsp" %>

<br><h2>deal prototype</h2>

<!--1. 카카오맵 설정  -->
<div class="container"> <div id="dealmap" style="width: 1320px; height: 500px" ></div></div>



<!--2. 거래가 정보 컨트롤  -->
 <div class="real-container">
  	<div class="container">
    
    <h2 class="text-center mt-5 mb-3">Apartment Deal Info Search</h2>
    
    
    <div class="row col-md-12 justify-content-center mb-2">
      <div class="form-group col-md-2">
        <select class="form-select bg-secondary text-light" id="sido">
          <option value="">시도선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select class="form-select bg-secondary text-light" id="gugun">
          <option value="">구군선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select class="form-select bg-secondary text-light" id="dong">
          <option value="">동선택</option>
        </select>
      </div>
      
            <div class="form-group col-md-2">
        <select class="form-select bg-dark text-light" id="year"></select>
      </div>
      
      
      <div class="form-group col-md-2">
        <select class="form-select bg-dark text-light" id="month">
          <option value=""></option>
        </select>
    </div>

      
      
      <div class="form-group col-md-2">
        <button type="button" id="list-btn" class="btn btn-outline-primary"  style="color: green; border: 1px solid green; font-weight: bold;">
          매매정보가져오기
        </button>
      </div>
    </div>
    <table class="table table-hover text-center" style="display: none">
      <tr>
        <th>아파트이름</th>
        <th>층</th>
        <th>면적</th>
        <th>법정동</th>
        <th>거래금액</th>
      </tr>
      <tbody id="aptlist"></tbody>
    </table>
  </div>
  


</div>



<!--1-1 카카오맵 연동 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d162619984216a37e85bbf4799648857&libraries=services"></script>

<!--1-2 카카오맵 띄우기ㅏ -->
<script>
var mapContainer = document.getElementById('dealmap'), // 지도를 표시할 div 

mapOption = {
    center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);
let date = new Date();

</script>


</body>

</html>