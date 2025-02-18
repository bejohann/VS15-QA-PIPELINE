package com.vemser.rest.data.factory;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.model.Usuario;
import com.vemser.rest.model.UsuarioResponse;
import io.restassured.response.Response;
import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class UsuarioDataFactory {

    static Faker faker = new Faker(new Locale("PT-BR"));
    static Random gerador = new Random();
    private static UsuarioClient usuarioClient = new UsuarioClient();

    public static Usuario usuarioValido() {
        return novoUsuario();
    }

    public static String nomeInvalido() {
        return faker.name().fullName();
    }

    public static String emailInvalido() {
        return faker.internet().emailAddress();
    }

    public static String idInvalido() {
        return faker.internet().uuid();
    }

    public static String idValido() {
        UsuarioResponse usuarioCadastrado = UsuarioDataFactory.usuarioJaCadastrado();
        String idUsuario = usuarioCadastrado.getId();

        return idUsuario;
    }

    public static Usuario usuarioComCamposVazios() {
        Usuario usuarioCamposVazios = novoUsuario();
        usuarioCamposVazios.setNome("");
        usuarioCamposVazios.setEmail("");
        usuarioCamposVazios.setPassword("");
        usuarioCamposVazios.setAdministrador("");

        return usuarioCamposVazios;
    }

    public static Usuario usuarioComEmailJaCadastrado() {
        UsuarioResponse usuarioCadastrado = usuarioJaCadastrado();
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(faker.name().fullName());
        novoUsuario.setEmail(usuarioCadastrado.getEmail());
        novoUsuario.setPassword(faker.internet().password());
        novoUsuario.setAdministrador(String.valueOf(gerador.nextBoolean()));

        return novoUsuario;

    }

    public static Usuario usuarioAdministradorCadastrado() {
        Usuario novoUsuario = usuarioValido();
        novoUsuario.setAdministrador("true");
        usuarioClient.cadastrarUsuario(novoUsuario);

        return novoUsuario;
    }

    public static Usuario usuarioComumCadastrado() {
        Usuario novoUsuario = usuarioValido();
        novoUsuario.setAdministrador("false");
        usuarioClient.cadastrarUsuario(novoUsuario);

        return novoUsuario;
    }

    public static UsuarioResponse usuarioJaCadastrado() {
        Response response = usuarioClient.listarUsuarios()
        .then()
                .extract().response()
        ;
        List<UsuarioResponse> listaUsuarios = response.jsonPath().getList("usuarios", UsuarioResponse.class);

        return listaUsuarios.get(gerador.nextInt(listaUsuarios.size()));
    }

    private static Usuario novoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(gerador.nextBoolean()));

        return usuario;
    }

}
