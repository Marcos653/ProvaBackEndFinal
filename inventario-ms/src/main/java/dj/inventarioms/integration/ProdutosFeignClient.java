package dj.inventarioms.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dj.inventarioms.shared.Produto;

@FeignClient(name = "produtos-ms", fallback = ProdutosFeignClientFallback.class)
public interface ProdutosFeignClient {
    @GetMapping(path = "/api/produto/{inventario}/lista")
    List<Produto> obterProdutos(@PathVariable String inventario);
}

@Component
class ProdutosFeignClientFallback implements ProdutosFeignClient {

    @Override
    public List<Produto> obterProdutos(String inventario) {
        return new ArrayList<>();
    }

}
