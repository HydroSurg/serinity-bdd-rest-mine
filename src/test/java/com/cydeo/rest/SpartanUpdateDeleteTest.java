package com.cydeo.rest;

import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;

// Our maven pom.xml is set to run only what's defined in TestRunner
// using mvn clean verify
// if you want to run one specific test class do below
// mvn clean verify -Dtest=YourClassNameGoesHere
// for example : mvn clean verify -Dtest=SpartanUpdateDeleteTest

// How to just run certain tag using maven command

public class SpartanUpdateDeleteTest extends SpartanTestBase {

    @DisplayName("Test PUT /spartans/{id}")
    @Test
    public void testUpdateOneData() {
        Map<String, Object> spartanOneBody = SpartanUtil.getRandomSpartanMapBody();
        int validID = SpartanUtil.getRandomID();
        SerenityRest.given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(spartanOneBody)
                .pathParam("id", validID)
        .when()
                .put("/spartans/{id}");
        // if there is error it will show up in the html report not in the console food for thought :)
        Ensure.that("status code 204", v -> v.statusCode(204));

        // verify this worked by sending another get request
        SerenityRest.given()
                .pathParam("id",validID)
        .when()
                .get("/spartans/{id}");
        Ensure.that("name is "+spartanOneBody.get("name") , v -> v.body("name",is(equalTo(spartanOneBody.get("name")))));
        Ensure.that("gender is "+spartanOneBody.get("gender") , v -> v.body("gender",is(equalTo(spartanOneBody.get("gender")))));
        Ensure.that("phone is "+spartanOneBody.get("phone") , v -> v.body("phone",is(equalTo(spartanOneBody.get("phone")))));


        // another equivalence of Ensure.that is restAsurredThat method
        // restAssuredThat method accept 1 parameter Consumer < ValidatableResponse>
        // you can use same lambda expression did before
        SerenityRest.restAssuredThat(v->v.statusCode(200));



        // verify this worked from DB - Homework
    }


    // DELETE /spartans/{id}
    @Tag("wip")
    @DisplayName("Send DELETE /spartans/{id}")
    @Test
    public void testDelete(){

        int validRandomId = SpartanUtil.getRandomID() ;

        SerenityRest.given().pathParam("id",validRandomId)
                .when()
                .delete("/spartans/{id}");

        // if there is error it will show up in the html report not in the console food for thought :)
        Ensure.that("Delete Successful !", v -> v.statusCode(204));

        // verify this worked by sending another get request
        SerenityRest.given()
                .pathParam("id",validRandomId)
                .when().get("/spartans/{id}");
        // verify this time we get 404
        Ensure.that("Deleted Data does not exists ! ",validatableResponse -> validatableResponse.statusCode(404));
    }



}
