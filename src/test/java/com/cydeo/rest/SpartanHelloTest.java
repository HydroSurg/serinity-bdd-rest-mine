package com.cydeo.rest;

//import static io.restassured.RestAssured.*;

import com.cydeo.utility.SpartanTestBase;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


//how do we tell this is not a regular test but it is a serenity test that meant to generate a report

public class SpartanHelloTest extends SpartanTestBase {


    @Tag("smoke")

    @Test
    public void testHello(){

        SerenityRest.given()
                .log().all().
                when()
                .get("/hello").
                then()
                .log().all()
                .statusCode(200);

        // get response object from above request
        //.lastResponse() method from SerenityRest will return
        // the response object from previous request

        // can you print out the Date header using the response object
        // SerenityRest.lastResponse() return Response Object from previous request
        System.out.println("SerenityRest.lastResponse().header(\"Date\") = "
                + SerenityRest.lastResponse().header("Date"));

        System.out.println("SerenityRest.lastResponse().contentType() = "
                + SerenityRest.lastResponse().contentType());
    }
    @DisplayName("Test Hello with detailed steps in report")
    @Test
    public void testHelloResponseStepByStep(){

        SerenityRest.given()
                        .log().uri()
                .when()
                        .get("/hello");
        // verify status code is 200, content type is text
        // body is "Hello From Sparta"
        // this is a good way but it wont show up in the report
        assertEquals(200, SerenityRest.lastResponse().statusCode());

        // if we want to add the assertion of the response
        // in the report as separate step,
        // Ensure class from Serenity provide the way
        // to let our response assertion show up as a step
        Ensure.that("Status Code is 200",
                validatableResponse -> validatableResponse.statusCode(200));

        //SerenityRest.lastResponse().

        Ensure.that("Content Type is Text Plain",
                validatableResponse -> validatableResponse.contentType(ContentType.TEXT));

        Ensure.that("Body is Hello From Sparta",
                validatableResponse -> validatableResponse.body(is("Hello from Sparta")));

    }

}