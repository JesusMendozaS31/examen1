package com.mendoza.infracciones.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mendoza.infracciones.entity.Infracciones;
import com.mendoza.infracciones.exception.GeneralException;
import com.mendoza.infracciones.exception.NoDataFoundException;
import com.mendoza.infracciones.exception.ValidateException;
import com.mendoza.infracciones.repository.InfraccionRepository;
import com.mendoza.infracciones.service.InfraccionesService;
import com.mendoza.infracciones.validator.InfraccionValidator;

@Service
public class InfraccionesServiceImpl implements InfraccionesService {
	@Autowired
	private InfraccionesRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Infracciones> findAll() {
		try {
			List<Infracciones> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infracciones findById(int id) {
		try {
			Infracciones registro = repository.findById(id).orElseThrow(
					()-> new NoDataFoundException("No existe un registro con ese ID") 
					);
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional
	public Infracciones save(Categoria obj) {
		InfraccionesValidator.save(obj);
		try {
			if (obj.getId() == 0) {
				Infracciones registro = repository.save(obj);
				return registro;
			}
			Categoria registro = this.findById(obj.getId());
			registro.setNombre(obj.getNombre());
			return repository.save(registro);

		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Categoria registro = this.findById(id);
			repository.delete(registro);
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}
}
