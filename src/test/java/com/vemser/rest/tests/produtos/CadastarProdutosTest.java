package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.model.Produto;
import com.vemser.rest.utils.constants.ProdutoConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CadastarProdutosTest {

    private ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testDeveCadastrarProdutoComSucesso() {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(201)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_CADASTRO_SUCESSO))
                .body(ProdutoConstants.ID, notNullValue())
        ;
    }

    @Test
    public void testSchemaDeveCadastrarProdutoComSucesso() {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/cadastrar_produto.json"))
        ;
    }

    @Test
    public void testTentarCadastrarProdutoComNomeJaCadastrado() {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.produtoComNomeJaCadastrado())
        .then()
                .statusCode(400)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_NOME_PRODUTO_JA_CADASTRADO))
        ;
    }

    @Test
    public void testTentarCadastrarProdutoComTokenInvalido() {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenInvalido(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(401)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_TOKEN_INVALIDO))
        ;
    }

    @ParameterizedTest
    @MethodSource("com.vemser.rest.data.provider.ProdutoProvider#produtoDataProvider")
    public void testTentarCadastrarProdutoComDadosInvalidos(Produto produto, String chave, String valor) {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenAdministrador(), produto)
        .then()
                .statusCode(400)
                .body(chave, equalToIgnoringCase(valor))
        ;
    }

}
