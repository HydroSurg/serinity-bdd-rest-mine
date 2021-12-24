package com.cydeo.utility;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpartanUtil {
    public static Map<String, Object> getRandomSpartanMapBody() {
        // create random data instead of always creating with same body
        // optionally: create a utility out of it
        Faker faker = new Faker();
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName());
        bodyMap.put("gender", faker.demographic().sex());
        // get a number between 5000000000L,9999999999L
        bodyMap.put("phone", faker.number().numberBetween(5000000000L, 9999999999L));

        return bodyMap;

    }

    // this is a method to get valid random id from spartan app

    public static int getRandomID(){

        List<Integer> listOfAllSpartanIDs = SerenityRest.get("/spartans").path("id");
        Faker faker = new Faker();
        int validIndex = faker.number().numberBetween(0, listOfAllSpartanIDs.size());

        return listOfAllSpartanIDs.get(validIndex);
    }

}
