package com.mendoza.infracciones.converter;

import com.mendoza.infracciones.entity.Infracciones;

import org.springframework.stereotype.Component;

import com.mendoza.infracciones.dto.InfraccionesDto;

@Component
public class InfraccionesConverter extends AbstractConverter<Infracciones, InfraccionesDto>{

	@Override
	public InfraccionesDto fromEntity(Infracciones entity) {
		if(entity == null) {
			return null;
		}
		return InfraccionesDto.builder()
				.dni(entity.getDni())
				.Id(entity.getDni()).build();
	}

	@Override
	public Infracciones fromDTO(InfraccionesDto dto) {
		if(dto==null) {
			return null;
		}
		return Infracciones.builder()
				.id(dto.getId())
				.dni(dto.getDni()).build();
	}

}
