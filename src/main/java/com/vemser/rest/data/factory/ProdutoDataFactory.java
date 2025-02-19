package com.vemser.rest.data.factory;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.model.Produto;
import net.datafaker.Faker;

import java.util.Locale;

public class ProdutoDataFactory {

    private static ProdutoClient produtoClient = new ProdutoClient();
    static Faker faker = new Faker(new Locale("PT-BR"));

    public static Produto produtoValido() {
        return novoProduto();
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
