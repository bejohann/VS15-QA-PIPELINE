package com.vemser.rest.data.factory;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.model.Produto;
import com.vemser.rest.model.ProdutoResponse;
import com.vemser.rest.utils.constants.ProdutoConstants;
import io.restassured.response.Response;
import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ProdutoDataFactory {

    private static ProdutoClient produtoClient = new ProdutoClient();
    static Faker faker = new Faker(new Locale("PT-BR"));
    static Random gerador = new Random();

    public static Produto produtoValido() {
        return novoProduto();
    }

    public static String idInvalido() {
        return faker.internet().uuid();
    }

    public static String nomeInvalido() {
        return faker.commerce().productName();
    }

    public static String descricaoInvalida() {
        return faker.lorem().sentence();
    }

    public static String idValido() {
        ProdutoResponse produtoJaCadastrado = ProdutoDataFactory.produtoJaCadastrado();

        return produtoJaCadastrado.getId();
    }

    public static Produto produtoComNomeEmBranco() {
        Produto produto = novoProduto();
        produto.setNome("");

        return produto;
    }

    public static Produto produtoComDescricaoEmBranco() {
        Produto produto = novoProduto();
        produto.setDescricao("");

        return produto;
    }

    public static Produto produtoComPrecoNegativo() {
        Produto produto = novoProduto();
        produto.setPreco(-1);

        return produto;
    }

    public static Produto produtoComQuantidadeNegativa() {
        Produto produto = novoProduto();
        produto.setQuantidade(-1);

        return produto;
    }

    public static Produto produtoComNomeJaCadastrado() {
        Response response = produtoClient.listarProdutos()
        .then()
                .extract().response()
        ;
        List<ProdutoResponse> produtos = response.jsonPath().getList(ProdutoConstants.PRODUTOS, ProdutoResponse.class);
        ProdutoResponse produtoCadastrado = produtos.get(gerador.nextInt(produtos.size()));

        Produto novoProduto = new Produto();
        novoProduto.setNome(produtoCadastrado.getNome());
        novoProduto.setPreco(faker.number().numberBetween(50, 1000));
        novoProduto.setDescricao(faker.lorem().sentence());
        novoProduto.setQuantidade(faker.number().numberBetween(1, 100));

        return novoProduto;
    }

    public static ProdutoResponse produtoJaCadastrado() {
        Response response = produtoClient.listarProdutos()
        .then()
                .extract().response()
        ;
        List<ProdutoResponse> produtos = response.jsonPath().getList(ProdutoConstants.PRODUTOS, ProdutoResponse.class);
        return produtos.get(gerador.nextInt(produtos.size()));
    }

    private static Produto novoProduto() {
        Produto produto = new Produto();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(faker.number().numberBetween(50, 1000));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(faker.number().numberBetween(1, 100));

        return produto;
    }
}
