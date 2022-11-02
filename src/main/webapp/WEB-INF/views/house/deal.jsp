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

<br>
<h2 class="text-center">Apartment Deal Info Search</h2>

<!--1. 카카오맵 설정  -->
<div class="container">
    <div id="dealmap" style="width: 1320px; height: 500px"></div>
</div>


<!--2. 거래가 정보 컨트롤  -->
<div class="real-container mt-5 text-center">
    <div class="container mb-5">
        <div class="row col-md-12 justify-content-center mb-2">
            <div class="form-group col-md-2">
                <select class="form-select bg-secondary text-light" id="sido">
                    <option value="">시도선택</option>
                    <option value="서울특별시">서울특별시</option>
                    <option value="부산광역시">부산광역시</option>
                    <option value="인천광역시">인천광역시</option>
                    <option value="광주광역시">광주광역시</option>
                    <option value="대전광역시">대전광역시</option>
                    <option value="대구광역시">대구광역시</option>
                    <option value="울산광역시">울산광역시</option>
                    <option value="세종특별자치시">세종특별자치시</option>
                    <option value="제주특별자치도">제주특별자치도</option>
                    <option value="경기도">경기도</option>
                    <option value="강원권">강원권</option>
                    <option value="충청북도">충청북도</option>
                    <option value="충청남도">충청남도</option>
                    <option value="전라북도">전라북도</option>
                    <option value="전라남도">전라남도</option>
                    <option value="경상북도">경상북도</option>
                    <option value="경상남도">경상남도</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <select class="form-select bg-secondary text-light" id="gugun">
                    <option value="">구군 선택</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <select class="form-select bg-secondary text-light" id="dong">
                    <option value="">동 선택</option>
                </select>
            </div>

            <div class="form-group col-md-2">
                <select class="form-select bg-secondary text-light" id="year">
                    <option value="">년도 선택</option>
                    <option value="2022">2022</option>
                    <option value="2021">2021</option>
                    <option value="2020">2020</option>
                    <option value="2019">2019</option>
                    <option value="2018">2018</option>
                    <option value="2017">2017</option>
                </select>
            </div>


            <div class="form-group col-md-2">
                <select class="form-select bg-secondary text-light" id="month">
                    <option value="">월 선택</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>

                </select>
            </div>


            <div class="form-group col-md-2">
                <button type="button" id="list-btn" class="btn btn-outline-primary"
                        style="color: green; border: 1px solid green; font-weight: bold;">
                    매매정보가져오기
                </button>
            </div>
        </div>
        <table class="table table-hover text-center mb-5">
            <tr style="background-color: rgba(23,11,10,0.22)">
                <th>아파트이름</th>
                <th>층</th>
                <th>면적</th>
                <th>도로명</th>
                <th>법정동</th>
                <th>거래금액</th>
            </tr>
            <tbody id="aptlist">

            </tbody>
        </table>
    </div>


</div>


<!--1-1 카카오맵 연동 -->
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d162619984216a37e85bbf4799648857&libraries=services"></script>

<!--1-2 카카오맵 띄우기ㅏ -->
<script>

    let mapContainer = document.getElementById('dealmap'), // 지도를 표시할 div

        mapOption = {
            center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 생성합니다
    let map = new kakao.maps.Map(mapContainer, mapOption);
    let date = new Date();

    function kakaoMap(lat, lng) {
        console.log(lat);
        mapOption = {
            center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
        let map = new kakao.maps.Map(mapContainer, mapOption);

        let markerPosition = new kakao.maps.LatLng(lat, lng);

        let marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
    }

    $(document).on("change", "#sido", getGugun);
    $(document).on("change", "#gugun", getDong);
    $(document).on("change", "#dong", selectDong);
    $(document).on("change", "#year", selectYear);
    $(document).on("change", "#month", selectMonth);
    $(document).on("click", "#list-btn", houseDealInfo);


    let month;

    function selectMonth() {
        month = $("#month option:selected").val();
    }

    let year;

    function selectYear() {
        year = $("#year option:selected").val();
    }

    let dong;

    function selectDong() {
        dong = $("#dong option:selected").val();
    }

    let sidoName;
    let gugunName;

    async function getDong() {
        gugunName = $("#gugun option:selected").val();
        if (gugunName !== "구군 선택") {
            let data = {
                method: "POST",
                body: JSON.stringify({sidoName, gugunName}),
                headers: {"Content-Type": "application/json"}
            }

            data = await fetch("dong", data);
            data = await data.text();
            data = JSON.parse(data);
            let options = `<option value="">동 선택</option>`;
            data = data.body;

            for (const item in data) {
                options += `<option value="\${data[item]}">\${data[item]}</option>`
            }
            $("#dong").html(options);
        }

    }

    async function getGugun() {
        sidoName = $("#sido option:selected").val();
        if (sidoName.length > 2) {
            let data = {
                method: "POST",
                body: JSON.stringify({sidoName}),
                headers: {"Content-Type": "application/json"}
            }

            data = await fetch("gugun", data);
            data = await data.text();
            data = JSON.parse(data);
            let options = `<option value="">구군 선택</option>`;
            data = data.body;

            for (const item in data) {
                options += `<option value="\${data[item]}">\${data[item]}</option>`
            }
            $("#gugun").html(options);
        }

    }

    async function houseDealInfo() {
        if (sidoName === undefined || gugunName === undefined || dong === undefined || year === undefined || month === undefined) {
            alert("검색 조건을 확인해 주세요");
        } else {
            let data = {
                method: "POST",
                body: JSON.stringify({sidoName, gugunName, dong, year, month}),
                headers: {"Content-Type": "application/json"}
            };
            data = await fetch("dealinfo", data);
            data = await data.text();
            data = JSON.parse(data);

            let houseDealInfoListTable = "";
            data = data.body;
            for (const x in data) {
                let item = data[x];
                console.log(item.lat);
                houseDealInfoListTable +=
                    `<tr>
                        <th><a href="#" " onclick="kakaoMap('\${item.lat}', '\${item.lng}')">\${item.apartmentName}</a></th>
                        <th>\${item.floor}</th>
                        <th>\${item.area}</th>
                        <th>\${item.roadName}</th>
                        <th>\${item.dong}</th>
                        <th>\${item.dealAmount}</th>
                      </tr>`
            }
            $("#aptlist").html(houseDealInfoListTable);
        }


    }

</script>


</body>

</html>