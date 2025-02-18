package com.vemser.rest.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class OlaMundoTeste {

    @Test
    public void testBuscarUsuarioPorIdComSucesso() {

        baseURI = "http://reqres.in";

        given()
                .log().all()
        .when()
                .get("/api/users/2")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

}
