package dj.inventarioms.service;

import java.util.List;
import java.util.Optional;

import dj.inventarioms.shared.InventarioDTO;

public interface InventarioService {
    InventarioDTO abrirInventario(InventarioDTO inventario);
    List<InventarioDTO> checkInventario();
    Optional<InventarioDTO> checkPorId(String id);
    void removerInventario(String id);
    InventarioDTO updateInventario(String id, InventarioDTO newInventory);
}
