package com.vemser.rest.data.factory;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.model.Login;
import com.vemser.rest.model.Usuario;
import com.vemser.rest.model.UsuarioResponse;
import com.vemser.rest.utils.constants.LoginConstants;
import io.restassured.response.Response;
import net.datafaker.Faker;

import java.util.Locale;

public class LoginDataFactory {

    private static LoginClient loginClient = new LoginClient();
    static Faker faker = new Faker(new Locale("PT-BR"));

    public static Login loginValido() {
        return  novoLogin();
    }

    public static String tokenAdministrador() {
        Login loginAdmin = loginAdministrador();
        Response response = loginClient.realizarLogin(loginAdmin)
        .then()
                .extract().response()
        ;
        return response.jsonPath().getString(LoginConstants.AUTHORIZATION);
    }

    public static String tokenUsuarioComum() {
        Login loginUsuario = loginUsuarioComum();
        Response response = loginClient.realizarLogin(loginUsuario)
        .then()
                .extract().response()
        ;
        return response.jsonPath().getString(LoginConstants.AUTHORIZATION);
    }

    public static String tokenInvalido() {
        return faker.internet().uuid();
    }

    public static Login loginInvalido() {
        Login login = novoLogin();
        login.setEmail(faker.internet().emailAddress());
        login.setPassword(faker.internet().password());

        return login;
    }

    public static Login loginComCamposVazios() {
        Login login = novoLogin();
        login.setEmail("");
        login.setPassword("");

        return login;
    }

    public static Login loginAdministrador() {
        Usuario usuario = UsuarioDataFactory.usuarioAdministradorCadastrado();
        Login login = new Login();
        login.setEmail(usuario.getEmail());
        login.setPassword(usuario.getPassword());

        return login;
    }

    public static Login loginUsuarioComum() {
        Usuario usuario = UsuarioDataFactory.usuarioComumCadastrado();
        Login login = new Login();
        login.setEmail(usuario.getEmail());
        login.setPassword(usuario.getPassword());

        return login;
    }

    private static Login novoLogin() {
        UsuarioResponse usuarioCadastrado = UsuarioDataFactory.usuarioJaCadastrado();
        Login login = new Login();
        login.setEmail(usuarioCadastrado.getEmail());
        login.setPassword(usuarioCadastrado.getPassword());

        return login;
    }
}
