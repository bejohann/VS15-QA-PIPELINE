package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.model.ProdutoResponse;
import com.vemser.rest.utils.constants.ProdutoConstants;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ListarProdutosTest {

    private ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testDeveListarTodosProdutosComSucesso() {
        Response response = produtoClient.listarProdutos()
        .then()
                .statusCode(200)
                .extract().response()
        ;
        List<ProdutoResponse> produtos = response.jsonPath().getList(ProdutoConstants.PRODUTOS, ProdutoResponse.class);
        int quantidade = response.jsonPath().getInt(ProdutoConstants.QUANTIDADE);

        Assertions.assertAll("response",
                () -> Assertions.assertEquals(quantidade, produtos.size()),
                () -> Assertions.assertNotNull(produtos.get(0).getNome()),
                () -> Assertions.assertNotNull(produtos.get(0).getPreco()),
                () -> Assertions.assertNotNull(produtos.get(0).getDescricao()),
                () -> Assertions.assertNotNull(produtos.get(0).getQuantidade()),
                () -> Assertions.assertNotNull(produtos.get(0).getId())
        );
    }

    @Test
    public void testSchemaDeveListarProdutosComSucesso() {
        produtoClient.listarProdutos()
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/listar_todos_produtos.json"))
        ;
    }

    @Test
    public void testListarProdutosPorNomeInvalido() {

        produtoClient.listarProdutosPorNome(ProdutoDataFactory.nomeInvalido())
        .then()
                .statusCode(200)
                .body(ProdutoConstants.QUANTIDADE, is(0))
                .body(ProdutoConstants.PRODUTOS, notNullValue())
        ;
    }

    @Test
    public void testListarProdutosPorDescricaoInvalida() {

        produtoClient.listarProdutosPorDescricao(ProdutoDataFactory.descricaoInvalida())
        .then()
                .statusCode(200)
                .body(ProdutoConstants.QUANTIDADE, is(0))
                .body(ProdutoConstants.PRODUTOS, notNullValue())
        ;
    }

    @Test
    public void testDeveListarProdutosPorIdComSucesso() {

        ProdutoResponse response = produtoClient.listarProdutoPorId(ProdutoDataFactory.idValido())
        .then()
                .extract().as(ProdutoResponse.class)
        ;

        Assertions.assertAll("response",
                () -> Assertions.assertNotNull(response.getNome()),
                () -> Assertions.assertNotNull(response.getPreco()),
                () -> Assertions.assertNotNull(response.getDescricao()),
                () -> Assertions.assertNotNull(response.getQuantidade()),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @Test
    public void testSchemaDeveListarProdutosPorIdComSucesso() {

        produtoClient.listarProdutoPorId(ProdutoDataFactory.idValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/produtos_por_id.json"))
        ;
    }

    @Test
    public void testTentarListarProdutosPorIdInvalido() {

        produtoClient.listarProdutoPorId(ProdutoDataFactory.idInvalido())
        .then()
                .statusCode(400)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_PRODUTO_NAO_ENCONTRADO))
        ;
    }

}
