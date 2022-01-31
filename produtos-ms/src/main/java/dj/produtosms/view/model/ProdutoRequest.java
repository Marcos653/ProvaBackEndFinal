package dj.produtosms.view.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ProdutoRequest {
    @NotBlank(message = "o Id do Inventario não pode estar em branco")
    @NotEmpty(message = "o Id do Inventario não pode estar vazio")
    private String inventario;
    @NotBlank(message = "Nome n pode ser vazio")
    private String nome;
    @NotNull(message = "Quantidade n pode ser nulo")
    private Integer quant;
    @DecimalMin(value = "0.01", message = "Valor invalido")
    private double valor;
    
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
