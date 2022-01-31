package dj.produtosms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dj.produtosms.service.ProdutoService;
import dj.produtosms.shared.ProdutoDTO;
import dj.produtosms.view.model.ProdutoAlteracao;
import dj.produtosms.view.model.ProdutoRequest;
import dj.produtosms.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }

    
    @GetMapping(value="/{inventario}/lista")
    public ResponseEntity<List<ProdutoResponse>> obterPorInventario(@PathVariable String inventario) {
        List<ProdutoDTO> dtos = service.obterPorInventario(inventario);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProdutoResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, ProdutoResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
 
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterProdutos() {
        List<ProdutoDTO> produtoDTOS = service.obterProdutos();

        if(produtoDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProdutoResponse> produtoResponses = produtoDTOS.stream()
                .map(c -> mapper.map(c, ProdutoResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(produtoResponses, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(@PathVariable String id){

        Optional<ProdutoDTO> produto = service.obterPorId(id);

        if(produto.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(produto.get(), ProdutoResponse.class),HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid ProdutoRequest produto) {
        ModelMapper map = new ModelMapper();
        ProdutoDTO prodDto= map.map(produto, ProdutoDTO.class);
        prodDto = service.cadastrar(prodDto);
        return new ResponseEntity<>(map.map(prodDto, ProdutoResponse.class), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable String id){
        service.excluirProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value= "/{id}")
    public ResponseEntity< ProdutoResponse> atualizarProduto(@PathVariable String id, @Valid @RequestBody ProdutoAlteracao novoProduto) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO dto = mapper.map(novoProduto, ProdutoDTO.class);
        dto = service.atualizarProduto(id, dto);
        
        return new ResponseEntity<>(mapper.map(dto, ProdutoResponse.class), HttpStatus.OK);
    }
}
