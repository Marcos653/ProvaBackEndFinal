package dj.inventarioms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dj.inventarioms.model.Inventario;
import dj.inventarioms.service.InventarioService;
import dj.inventarioms.shared.InventarioDTO;
import dj.inventarioms.view.model.InventarioRequest;
import dj.inventarioms.view.model.InventarioResponse;
import dj.inventarioms.view.model.InventarioResponseDetalhes;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService service;

    @PostMapping
    public ResponseEntity<InventarioResponse> criarInventario(@RequestBody @Valid InventarioRequest inventario) {
        ModelMapper mapper = new ModelMapper();
        InventarioDTO dto = mapper.map(inventario, InventarioDTO.class);
        dto = service.abrirInventario(dto);
        return new ResponseEntity<>(mapper.map(dto, InventarioResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<InventarioResponse>> obterTodos() {
        List<InventarioDTO> dtos = service.checkInventario();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<InventarioResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, InventarioResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<InventarioResponseDetalhes> obterPorId(@PathVariable String id) {
        Optional<InventarioDTO> inventario = service.checkPorId(id);

        if(inventario.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(inventario.get(), InventarioResponseDetalhes.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<InventarioResponse> atualizarinventario(@PathVariable String id,
        @Valid @RequestBody Inventario inventario) {
        ModelMapper mapper = new ModelMapper();
        InventarioDTO dto = mapper.map(inventario, InventarioDTO.class);
        dto = service.updateInventario(id, dto);

        return new ResponseEntity<>(mapper.map(dto, InventarioResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerInventario(@PathVariable String id) {
        service.removerInventario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
