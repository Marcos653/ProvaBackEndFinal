package dj.produtosms.view.model;

public class ProdutoResponse {
    private String id;
    private String inventario;
    private String nome;
    private Integer quant;
    private double valor;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getInventario() {
        return inventario;
    }
    public void setInventario(String inventario) {
        this.inventario = inventario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQuant() {
        return quant;
    }
    public void setQuant(Integer quant) {
        this.quant = quant;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
