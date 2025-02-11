import { Component, OnInit } from '@angular/core';
import { ProdutoService, Produto } from 'src/app/services/produto.service';

@Component({
  selector: 'app-lista-produtos',
  templateUrl: './lista-produtos.component.html',
  styleUrls: ['./lista-produtos.component.scss']
})
export class ListaProdutosComponent implements OnInit {
  produtos: Produto[] = [];

  constructor(private produtoService: ProdutoService) {}

  ngOnInit(): void {
    this.produtoService.listarTodos().subscribe(data => {
      this.produtos = data;
    });
  }

  deletarProduto(id: number): void {
    this.produtoService.deletar(id).subscribe(() => {
      this.produtos = this.produtos.filter(produto => produto.id !== id);
    });
  }
}
