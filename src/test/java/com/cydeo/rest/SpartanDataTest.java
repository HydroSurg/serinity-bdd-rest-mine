package com.cydeo.rest;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class SpartanDataTest extends SpartanTestBase {

    @DisplayName("Test user able to get single Spartan Data")
    @Test
    public void test1Spartan(){
        // get last valis /ID from /spartans
        int lastValidId = SerenityRest.get("/spartans").path("id[-1]");
        SerenityRest.given()
                .log().uri()
                .pathParam("id",lastValidId) // must randomize later to optimize the code
            .when()
                .get("/spartans/{id}");
        Ensure.that("Status code is 200",validatableResponse -> validatableResponse.statusCode(200));
//            .then()
//                .statusCode(200)
//                .log().all();
        Ensure.that("Contenty-Type is JSON",validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("Status code is 200",validatableResponse -> validatableResponse.statusCode(200)).
                andThat("Content-Type",validatableResponse -> validatableResponse.contentType(ContentType.JSON))
                .andThat("The Id value is "+lastValidId,validatableResponse -> validatableResponse.body("id",is(lastValidId)));




    }

    @Tag("ddt")
    // Repeat above tests for 5 valid id
    @ParameterizedTest(name = "Testing Spartan Data with ID of {0}")
    @ValueSource(ints = {910,911,917,918,919}) // use method source instead of magic number so we can have true data
    // get 10 data from the system pass it here
    public void testOneSpartanDDT(int idParam){

        SerenityRest.given()
                .pathParam("id", idParam)
                .log().uri().
                when()
                .get("/spartans/{id}");

        Ensure.that("Status code is 200", v -> v.statusCode(200)    )
                .andThat("Content Type is JSON", v-> v.contentType(ContentType.JSON) )
                .andThat("The Id value is "+idParam
                        , v -> v.body("id" , is(idParam)   )      );

    }







    @DisplayName("Homework Parameterized method")
    public static List<Integer> getValidId(){
        List<Integer> listOfValidId = new ArrayList<>();


      for (int i = 0; i < 11; i++) {
          listOfValidId.add(SerenityRest.get("/spartans").jsonPath().getInt("id["+i+"]"));
       }
        System.out.println(listOfValidId);
        return listOfValidId;
    }

    @DisplayName("Test with 10 ID retrieve using MethodSource")
    @Tag("ddt")
    @ParameterizedTest
    @MethodSource("getValidId")
    public void testOneSpartanDDTRandom(int idDDT){

        SerenityRest.given()
                .pathParam("id", idDDT)
                .log().uri().
                when()
                .get("/spartans/{id}");

        Ensure.that("Status code is 200", v -> v.statusCode(200)    )
                .andThat("Content Type is JSON", v-> v.contentType(ContentType.JSON) )
                .andThat("The Id value is "+idDDT
                        , v -> v.body("id" , is(idDDT   )  )    );




    }








}
