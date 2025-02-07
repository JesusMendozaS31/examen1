package com.mendoza.infracciones.controller;

	import java.util.List;

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
	
	
	
	import com.mendoza.infracciones.entity.Categoria;
	import com.mendoza.infracciones.service.CategoriaService;
	import com.mendoza.infracciones.util.WrapperResponse;
	import com.mendoza.infracciones.converter.CategoriaConverter;
	import com.mendoza.infracciones.dto.CategoriaDto;

	@RestController
	@RequestMapping("/v1/categorias")
	//https://localhost:8091/v1/categorias
	
	public class InfraccionesController {
		@Autowired
		private CategoriaService service;

		@Autowired
		private CategoriaConverter converter;

		@GetMapping
		public ResponseEntity<List<CategoriaDto>> getAll() {
			List<CategoriaDto> registros = converter.fromEntity(service.findAll());
			return new WrapperResponse(true, "success", registros).createResponse();
		}

		@GetMapping("/{id}")
		public ResponseEntity<CategoriaDto> getById(@PathVariable("id") int id) {
			CategoriaDto registro = converter.fromEntity(service.findById(id));
			if (registro == null) {
				return ResponseEntity.notFound().build();
			}
			return new WrapperResponse(true, "success", registro).createResponse();
		}

		@PostMapping
		public ResponseEntity<CategoriaDto> create(@RequestBody CategoriaDto dto) {
			Categoria registro = service.save(converter.fromDTO(dto));		
			return new WrapperResponse(true, "success", converter.fromEntity(registro)).
					createResponse(HttpStatus.CREATED);
		}

		@PutMapping("/{id}")
		public ResponseEntity<CategoriaDto> update(@PathVariable("id") int id, @RequestBody CategoriaDto categoria) {
			Categoria categoriaEntity = converter.fromDTO(categoria);
			CategoriaDto registro = converter.fromEntity(service.save(categoriaEntity));
			return new WrapperResponse(true, "success", registro).createResponse();
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<CategoriaDto> delete(@PathVariable("id") int id) {
			service.delete(id);
			return new WrapperResponse(true, "success", null).createResponse();
		}
	}
	
	
	
	
	

		
		
		

		
		
}
