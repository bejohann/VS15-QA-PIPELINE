package com.vemser.rest.client;

import com.vemser.rest.model.Login;
import com.vemser.rest.utils.constants.LoginConstants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LoginClient extends BaseClient {

    private final String LOGIN = LoginConstants.ENDPOINT_LOGIN;

    public Response realizarLogin(Login login) {

        return
                given()
                        .spec(super.set())
                        .body(login)
                .when()
                        .post(LOGIN)
                ;
    }

}
