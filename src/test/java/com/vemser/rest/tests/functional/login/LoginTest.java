package com.vemser.rest.tests.functional.login;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.utils.constants.LoginConstants;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    private LoginClient loginClient = new LoginClient();

    @Test
    public void testDeveRealizarLoginComSucesso() {

        loginClient.realizarLogin(LoginDataFactory.loginValido())
        .then()
                .statusCode(200)
                .body(LoginConstants.MESSAGE, equalToIgnoringCase(LoginConstants.MSG_LOGIN_COM_SUCESSO))
                .body(LoginConstants.AUTHORIZATION, notNullValue())
        ;
    }



    @Test
    public void testRealizarLoginInvalido() {

        loginClient.realizarLogin(LoginDataFactory.loginInvalido())
        .then()
                .statusCode(401)
                .body(LoginConstants.MESSAGE, equalToIgnoringCase(LoginConstants.MSG_LOGIN_INVALIDO))
        ;
    }

    @Test
    public void testRealizarLoginComCamposVazios () {

        loginClient.realizarLogin(LoginDataFactory.loginComCamposVazios())
        .then()
                .statusCode(400)
                .body(LoginConstants.EMAIL, equalToIgnoringCase(LoginConstants.MSG_EMAIL_EM_BRANCO))
                .body(LoginConstants.PASSWORD, equalToIgnoringCase(LoginConstants.MSG_PASSWORD_EM_BRANCO))
        ;
    }

}
