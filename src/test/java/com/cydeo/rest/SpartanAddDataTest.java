package com.cydeo.rest;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;


public class SpartanAddDataTest extends SpartanTestBase {


    @DisplayName("POST /spartans to Add 1 data")
    @Test
    public void testAdd1Data(){

        Spartan sp = new Spartan("B23 Serenity", "Female",1231231231L);
        System.out.println("sp = " + sp);

        SerenityRest.given()
                        .log().body()
                        .contentType(ContentType.JSON)
                        .body(sp).
                     when()
                        .post("/spartans") ;

        Ensure.that("status code 201", v -> v.statusCode(201) )
            .andThat("name is "+ sp.getName()  ,  v -> v.body("data.name" , is(sp.getName()) ) )

        // verify the rest here as homework
        ;

    }


}
