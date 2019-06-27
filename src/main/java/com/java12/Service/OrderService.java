package com.java12.Service;

import com.google.gson.Gson;
import com.java12.ConstUrlsInterface;
import com.java12.Entity.Order.Order;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;

public class OrderService implements ConstUrlsInterface {
    private final Gson jsonParser = new Gson();

    public String addOrderForPet(Order order) throws IOException {
        try {
            Jsoup.connect(baseUrl + baseStoreUrl + orderUrl)
                    .header("accept", "application/xml")
                    .header("Content-Type", "application/json")
                    .requestBody(jsonParser.toJson(order))
                    .ignoreContentType(true)
                    .post();
        } catch (HttpStatusException exception) {
            return catchStatusFormatException(exception, "order");
        }

        return infoType + "Your order: " + order.getId() + " is just added";


    }

    public String getPetInventory() throws IOException {
        try {
            return Jsoup.connect(baseUrl + baseStoreUrl + inventoryUrl)
                    .header("accept", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET).execute().body();
        } catch (HttpStatusException e) {
            return catchStatusFormatException(e, "");
        }

    }


    public String getOrderById(int id) throws IOException {
        try {
            return Jsoup.connect(baseUrl + baseStoreUrl + orderUrl + "/" + id)
                    .header("accept", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET).execute().body();

        }
        catch (HttpStatusException ex){
            return catchStatusFormatException(ex, " ID supplied");
        }
    }

    public String deleteById( int orderId) throws IOException {
        try{
            Jsoup.connect(baseUrl + baseStoreUrl + orderUrl + "/" + orderId)
                    .header("accept", "application/xml")
                    .ignoreContentType(true)
                    .method(Connection.Method.DELETE).execute();
        }
        catch (HttpStatusException ex){
            return catchStatusFormatException(ex, "ID supplied");
        }

        return infoType + "Your order " + orderId + " just been deleted";
    }

    public String catchStatusFormatException(HttpStatusException exception, String plusInfo) {
        switch (exception.getStatusCode()) {
            case 400:
                return errorType + " Invalid" + plusInfo;
            case 404:
                return errorType + "Order not found";
            default:
                return errorType + exception.getStatusCode() + "| " + exception.getMessage();
        }
    }
}
