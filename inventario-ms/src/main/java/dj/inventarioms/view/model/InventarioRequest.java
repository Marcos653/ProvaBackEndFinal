package dj.inventarioms.view.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class InventarioRequest {
    @NotBlank(message = "categoria não pode estar em branco")
    @NotEmpty(message = "categoria não pode estar vazio")
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

}
