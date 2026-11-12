package com.gestao.estoque.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestao.estoque.model.Produto;
import com.gestao.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));
    }

    // Cadastrar produto
    public Produto cadastrar(Produto produto) {
    verificarCodigoDuplicado(produto.getCodigo());
    return produtoRepository.save(produto);
}

    // Atualizar produto
    public Produto atualizar(Long id, Produto dadosAtualizados) {

        Produto produto = buscarPorId(id);

        produto.setNome(dadosAtualizados.getNome());
        produto.setPreco(dadosAtualizados.getPreco());
        produto.setQuantidadeEstoque(dadosAtualizados.getQuantidadeEstoque());
        produto.setCategoria(dadosAtualizados.getCategoria());
        produto.setCodigo(dadosAtualizados.getCodigo());

        return produtoRepository.save(produto);
    }

    // Excluir produto
    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    // Verificar se o código já está cadastrado
    private void verificarCodigoDuplicado(String codigo) {
        if (produtoRepository.existsByCodigo(codigo)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um produto cadastrado com esse código"
            );
        }
    }
    // Verificar código duplicado durante a atualização
    private void verificarCodigoDuplicadoNaAtualizacao(String codigo, Long id) {
    if (produtoRepository.existsByCodigoAndIdNot(codigo, id)) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe outro produto cadastrado com esse código"
        );
    }
}
}