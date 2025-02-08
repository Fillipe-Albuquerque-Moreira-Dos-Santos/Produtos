package com.example.demo.ProdutoMapper;

import com.example.demo.entities.Produto;
import com.example.demo.model.ProdutoDTO;

public interface ProdutoMapper {
    Produto toEntity(ProdutoDTO produtoDTO);
    ProdutoDTO toDTO(Produto produto);
}
