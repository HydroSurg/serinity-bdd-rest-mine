package com.cydeo.utility;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.cydeo.utility.SerenityConfigReader.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class SpartanTestBase {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI=read("spartan.base.url");
        RestAssured.basePath="/api";

    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
        // there is also a method to ensure the reset all restassured static field
        // that serenity use SerenityRest.reset() method
        SerenityRest.reset();
    }

}
