package dj.inventarioms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dj.inventarioms.model.Inventario;

@Repository
public interface InventarioRepository extends MongoRepository<Inventario, String> {
    
}
