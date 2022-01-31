package dj.produtosms.service;

import java.util.List;
import java.util.Optional;

import dj.produtosms.shared.ProdutoDTO;

public interface ProdutoService {
    List<ProdutoDTO> obterProdutos();
    ProdutoDTO cadastrar(ProdutoDTO produto);
    Optional<ProdutoDTO> obterPorId(String id);
    List<ProdutoDTO> obterPorInventario(String inventario);
    void excluirProduto(String id);
    ProdutoDTO atualizarProduto(String id, ProdutoDTO newProduto);
}
