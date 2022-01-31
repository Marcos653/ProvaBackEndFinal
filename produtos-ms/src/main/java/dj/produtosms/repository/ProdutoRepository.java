package dj.produtosms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dj.produtosms.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByInventario(String inventario);
}
