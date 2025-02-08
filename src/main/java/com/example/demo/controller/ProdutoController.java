package com.example.demo.controller;

import com.example.demo.ProdutoMapper.ProdutoMapper;
import com.example.demo.entities.Produto;
import com.example.demo.model.ProdutoDTO;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @PostMapping("/salvar-produtos")
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    @GetMapping("/listar/{id}")
    public ProdutoDTO listar(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produtoMapper.toDTO(produto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/atualizar/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

}
