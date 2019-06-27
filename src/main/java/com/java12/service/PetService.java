package com.java12.service;

import com.google.gson.Gson;
import com.java12.ConstUrlsInterface;
import com.java12.entity.Pet.Pet;
import com.java12.entity.Pet.PetStatus;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PetService implements ConstUrlsInterface {
    private Gson jsonParser = new Gson();

    /* methods for user*/
    public String getPetById(int petId) throws IOException {
        String answer = null;
        try {
            answer = Jsoup.
                    connect(baseUrl + basePetUrl + "/" + petId).
                    ignoreContentType(true).method(Connection.Method.GET).
                    execute().body();

        } catch (HttpStatusException exception) {
            answer = catchStatusFormatException(exception, "ID");
        }

        return answer;

    }

    public String updatePetByIdStatusName(Pet pet) throws IOException {

        try {
            Jsoup.connect(baseUrl + basePetUrl + "/" + pet.getId()).
                    ignoreContentType(true)
                    .data("name", pet.getName())
                    .data("status", pet.getStatus().name()).post();
        } catch (HttpStatusException e) {
            return catchStatusFormatException(e, "");
        }

        return infoType + "Pet " + pet.getId() + " | " + pet.getName() + " | " + pet.getStatus() + " just updated";

    }

    public String getPetsByStatus(PetStatus status) throws IOException {
        String answer = null;
        try {
            answer = Jsoup.connect(baseUrl + basePetUrl + findByStatusUrl + status.name())
                    .ignoreContentType(true)
                    .get().text();
        } catch (HttpStatusException e) {
            answer = catchStatusFormatException(e, "status");
        }

        return answer;
    }

    public String postPetToStore(Pet pet) throws IOException {
        return postAndPutMethodForPet(jsonParser.toJson(pet), Connection.Method.POST);
    }

    public String putPetToStore(Pet pet) throws IOException {
        return postAndPutMethodForPet
                (jsonParser.toJson(pet), Connection.Method.PUT);
    }

    public String uploadFileForPetById(int id, File file, String format) throws IOException {
        try {
            Jsoup.connect(baseUrl + basePetUrl + "/" + id + uploadImageUrl)
                    .header("accept", "application/json")
                    .header("Content-Type", "multipart/form-data")
                    .data("file", file.getName(), new FileInputStream(file))
                    .data("additionalMetadata", format)
                    .ignoreContentType(true).post();
        } catch (HttpStatusException e) {
            return catchStatusFormatException(e, "");
        }

        return infoType + "Your file: " + file.getName() + "is uploaded";
    }

    public String deleteById(int id) throws IOException {
        try {
            Jsoup.connect(baseUrl + basePetUrl + "/" + id)
                    .header("accept", "application/xml")
                    .header("api_key", "special-key")
                    .method(Connection.Method.DELETE).execute();
        } catch (HttpStatusException e) {
            return catchStatusFormatException(e, "ID");
        }

        return infoType + "Pet " + id + " just been deleted";
    }

    /* methods for service*/

    private String postAndPutMethodForPet(String petJson, Connection.Method method) throws IOException {
        String answer = null;
        try {
            Jsoup.connect(baseUrl + basePetUrl).
                    header("accept", "application/xml")
                    .header("Content-Type", "application/json").
                    requestBody(petJson).method(method).execute();
            answer = infoType + method.name();
        } catch (HttpStatusException e) {
            String plusInfo;
            if (method.equals(Connection.Method.POST)) {
                plusInfo = "Invalid input";
            } else {
                plusInfo = "Validation exception";
            }
            answer = catchStatusFormatException(e, plusInfo);
        }

        return answer;

    }


    public String catchStatusFormatException(HttpStatusException exception, String plusInfo) {
        switch (exception.getStatusCode()) {
            case 400:
                return errorType + "Invalid " + plusInfo + " supplied";
            case 404:
                return errorType + "Pet not found";
            case 405:
                return errorType + plusInfo;
            default:
                return errorType + exception.getStatusCode() + "| " + exception.getMessage();
        }
    }


}
