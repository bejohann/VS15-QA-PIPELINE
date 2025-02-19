package com.vemser.rest.tests.contrato;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContratoTest {

    LoginClient loginClient = new LoginClient();
    UsuarioClient usuarioClient = new UsuarioClient();
    ProdutoClient produtoClient = new ProdutoClient();

    @Test
    @Tag("Contrato")
    public void testSchemaRealizarLoginComSucesso() {

        loginClient.realizarLogin(LoginDataFactory.loginValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/realizar_login.json"))
        ;
    }

    @Test
    @Tag("Contrato")
    public void testSchemaAtualizarUsuarioComSucesso() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idValido(), UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/atualizar_usuario.json"))
        ;
    }

    @Test
    @Tag("Contrato")
    public void testSchemaDeveCadastrarUsuarioComDadosValidos() {

        usuarioClient.cadastrarUsuario(UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/cadastrar_usuario.json"))
        ;
    }

    @Test
    @Tag("Contrato")
    public void testSchemaExcluirUsuarioComSucesso() {

        usuarioClient.excluirUsuario(UsuarioDataFactory.idValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/excluir_usuario.json"))
        ;
    }

    @Test
    @Tag("Contrato")
    public void testSchemaListarTodosUsuariosComSucesso() {

        usuarioClient.listarUsuarios()
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/listar_todos_usuarios.json"))
        ;
    }

    @Test
    @Tag("Contrato")
    public void testSchemaDeveCadastrarProdutoComSucesso() {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.produtoValido())
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/cadastrar_produto.json"))
        ;
    }

}
