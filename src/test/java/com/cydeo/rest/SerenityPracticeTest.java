package com.cydeo.rest;

import static com.cydeo.utility.SerenityConfigReader.*;

import com.cydeo.utility.SerenityConfigReader;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Serenity.properties file is used by serenity to configuration
 * and we can also add our own key value pair directly there
 * and access them using the method provided by the library itself
 */


@ExtendWith(SerenityJUnit5Extension.class)
public class SerenityPracticeTest {

    @Tag("wip2")
    @Test
    public void testOutTheReader(){

        String url = SerenityConfigReader.read("spartan.base.url") ;
        System.out.println("url = " + url);
        String url2 = read("spartan.base.url");
        System.out.println("url2 = " + url2);

        System.out.println("read(\"cool\") = " + read("cool"));
        // if you run it like this
            // mvn clean test -Denvironment=library3
            // you will get https://library3.cybertekschool.com
        // if you run it like this
            // mvn clean test -Denvironment=library2
            // you will get https://library2.cybertekschool.com
        // if you do not specify any environment
        // it will go to default sectin in this case
            // mvn clean test
            // you will get https://library2.cybertekschool.com
        System.out.println("read(\"library.base.url\") = " + read("library.base.url"));

    }


    /**
     * inside Serenity.conf file
     * add library_url , librarian_email , librarian_password
     * add
     *
     *
     * Create a new class called LibraryAPITest
     *
     * Create a test method testDashboard stats
     * inside tests
     *      Send request to POST /login to get token
     *      Send request to GET /dashboard_stats
     *      print out the numbers
     *      assuming that you already know the expected
     *      verify all those 3 numbers match
     *
     *      run this test in both library2 and library3 environment
     */



}