package com.alura.spring.data.repository;

import com.alura.spring.data.model.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
}
