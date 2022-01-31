package dj.inventarioms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dj.inventarioms.integration.ProdutosFeignClient;
import dj.inventarioms.model.Inventario;
import dj.inventarioms.repository.InventarioRepository;
import dj.inventarioms.shared.InventarioDTO;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository repo;

    @Autowired
    private ProdutosFeignClient ProdutosMsClient;

    @Override
    public InventarioDTO abrirInventario(InventarioDTO inventario) {
        return salvarInvetario(inventario);
    
    }

    @Override
    public List<InventarioDTO> checkInventario() {
        List<Inventario> inventarios = repo.findAll();

        return inventarios.stream()
            .map(i -> new ModelMapper().map(i, InventarioDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<InventarioDTO> checkPorId(String id) {
        Optional<Inventario> inventario = repo.findById(id);

        
        if(inventario.isPresent()) {
            InventarioDTO dto = new ModelMapper().map(inventario.get(), InventarioDTO.class);
            dto.setProdutos(ProdutosMsClient.obterProdutos(id));
            return Optional.of(dto);
        }
 
        return Optional.empty();
    }

    @Override
    public void removerInventario(String id) {
        repo.deleteById(id);     
    }

    @Override
    public InventarioDTO updateInventario(String id, InventarioDTO newInventory) {
       newInventory.setId(id);
       return salvarInvetario(newInventory);
    }


    private InventarioDTO salvarInvetario(InventarioDTO inventario){
        ModelMapper mapper = new ModelMapper();
        Inventario inventarioE = mapper.map(inventario, Inventario.class);
        inventarioE = repo.save(inventarioE);
        return mapper.map(inventarioE, InventarioDTO.class);
    }
    
}
