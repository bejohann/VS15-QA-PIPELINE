package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.utils.constants.ProdutoConstants;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class AtualizarProdutosTest {

    private ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testDeveAtualizarProdutoComSucesso() {

        produtoClient.atualizarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.idValido(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(200)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_ALTERADO_SUCESSO))
        ;
    }

    @Test
    public void testSchemaDeveAtualizarProdutoComSucesso() {

        produtoClient.atualizarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.idValido(), ProdutoDataFactory.produtoValido())
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/atualizar_produto.json"))
        ;
    }

    @Test
    public void testTentarAtualizarProdutoComIdInvalidoECadastrarNovoProduto() {

        produtoClient.atualizarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.idInvalido(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(201)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_CADASTRO_SUCESSO))
                .body(ProdutoConstants.ID, notNullValue())
        ;
    }

    @Test
    public void testTentarAtualizarProdutoComTokenInvalido() {

        produtoClient.atualizarProduto(LoginDataFactory.tokenInvalido(), ProdutoDataFactory.idValido(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(401)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_TOKEN_INVALIDO))
        ;
    }

    @Test
    public void testTentarAtualizarProdutoComUsuarioSemPermissao() {

        produtoClient.atualizarProduto(LoginDataFactory.tokenUsuarioComum(), ProdutoDataFactory.idValido(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(403)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_USUARIO_SEM_PERMISSAO))
        ;
    }

}
