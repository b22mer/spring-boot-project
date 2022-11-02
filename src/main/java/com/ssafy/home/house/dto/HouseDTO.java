package com.ssafy.home.house.dto;

public class HouseDTO {
    private long no;
    private String dong;
    private String roadName;
    private String apartmentName;
    private String lat;
    private String lng;
    private String floor;
    private String area;
    private String dealAmount;

    public HouseDTO() {
    }

    public HouseDTO(long no, String dong, String roadName, String apartmentName, String lat, String lng, String floor, String area, String dealAmount) {
        this.no = no;
        this.dong = dong;
        this.roadName = roadName;
        this.apartmentName = apartmentName;
        this.lat = lat;
        this.lng = lng;
        this.floor = floor;
        this.area = area;
        this.dealAmount = dealAmount;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }
}
