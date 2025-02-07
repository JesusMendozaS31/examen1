package com.mendoza.infracciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendoza.infracciones.entity.Infracciones;

@Repository
public interface InfraccionRepository extends JpaRepository<Infracciones, Integer>{
 
}
