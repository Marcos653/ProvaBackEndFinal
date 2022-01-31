package dj.inventarioms.view.model;

import java.util.List;

import dj.inventarioms.shared.Produto;

public class InventarioResponseDetalhes {
    private String id;
    private String categoria;
    private List<Produto> produtos;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}
