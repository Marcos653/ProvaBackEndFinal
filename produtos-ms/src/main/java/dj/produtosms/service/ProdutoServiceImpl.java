package dj.produtosms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dj.produtosms.model.Produto;
import dj.produtosms.repository.ProdutoRepository;
import dj.produtosms.shared.ProdutoDTO;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository repo;

    @Override
    public List<ProdutoDTO> obterProdutos() {
        List<Produto> produtos = repo.findAll();
        return produtos.stream()
            .map(p -> new ModelMapper().map(p, ProdutoDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO cadastrar(ProdutoDTO produto) {
        return salvarProduto(produto);
    }

    @Override
    public Optional<ProdutoDTO> obterPorId(String id) {
        Optional <Produto> produtoBuscar = repo.findById(id);

        if(produtoBuscar.isPresent()){
            return Optional.of(new ModelMapper().map(produtoBuscar.get(), ProdutoDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void excluirProduto(String id) {
        repo.deleteById(id);
        
    }

    @Override
    public ProdutoDTO atualizarProduto(String id, ProdutoDTO newProduto) {
        newProduto.setId(id);
        return salvarProduto(newProduto);
    }

    
    @Override
    public List<ProdutoDTO> obterPorInventario(String inventario) {
        List<Produto> produtos = repo.findByInventario(inventario);

        return produtos.stream()
            .map(p -> new ModelMapper().map(p, ProdutoDTO.class))
            .collect(Collectors.toList());
    } 

    private ProdutoDTO salvarProduto(ProdutoDTO produto){
        ModelMapper mapper = new ModelMapper();
        Produto produtoEntidade = mapper.map(produto, Produto.class);
        produtoEntidade = repo.save(produtoEntidade);
        return mapper.map(produtoEntidade, ProdutoDTO.class);
    }

    
}
