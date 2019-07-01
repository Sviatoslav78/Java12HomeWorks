package com.java12.HTTPhome;

import org.jsoup.HttpStatusException;

public interface ConstUrlsInterface {
    String baseUrl ="http://petstore.swagger.io/v2";
    String basePetUrl = "/pet";
    String findByStatusUrl = "/findByStatus?status=";
    String uploadImageUrl = "/uploadImageUrl";
    String errorType = "Error type is: ";
    String infoType = "Method executed successful: ";
    String baseStoreUrl = "/store";
    String orderUrl = "/order";
    String inventoryUrl = "/inventory";

    String catchStatusFormatException(HttpStatusException exception, String plusInfo);



}
