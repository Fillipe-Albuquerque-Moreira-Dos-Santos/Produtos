package com.example.demo.ProdutoMapper;


import com.example.demo.entities.Produto;
import com.example.demo.model.ProdutoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {
    @Override
    public Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setNome(produtoDTO.getNome());
        return produto;
    }

    @Override
    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setNome(produto.getNome());
        return produtoDTO;
    }
}
