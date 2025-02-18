package com.vemser.rest.data.provider;


import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.utils.constants.ProdutoConstants;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProdutoProvider {

    private static Stream<Arguments> produtoDataProvider() {
        return Stream.of(
                Arguments.of(ProdutoDataFactory.produtoComNomeEmBranco(), ProdutoConstants.NOME, ProdutoConstants.MSG_NOME_EM_BRANCO),
                Arguments.of(ProdutoDataFactory.produtoComDescricaoEmBranco(), ProdutoConstants.DESCRICAO, ProdutoConstants.MSG_DESCRICAO_EM_BRANCO),
                Arguments.of(ProdutoDataFactory.produtoComPrecoNegativo(), ProdutoConstants.PRECO, ProdutoConstants.MSG_PRECO_NEGATIVO),
                Arguments.of(ProdutoDataFactory.produtoComQuantidadeNegativa(), ProdutoConstants.QUANTIDADE, ProdutoConstants.MSG_QUANTIDADE_NEGATIVA)
        );
    }

}
