package com.mendoza.infracciones.service;
import com.mendoza.infracciones.entity.Infracciones;
import java.util.List;

public interface InfraccionesService {
	public List<Infracciones> findAll();
	public Infracciones findById(int id);
	public Infracciones save(Infracciones obj);
	public void delete(int id);
}
