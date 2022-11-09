package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @PostMapping
    public ResponseEntity<Aluno> create(@Valid @RequestBody AlunoForm form) {
	Aluno aluno = service.create(form); 
	return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
	return service.getAllAvaliacaoFisicaId(id);
    }

    @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false)
    String dataDeNacimento){
	return service.getAll(dataDeNacimento);
    }
    
    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
	Map<String, String> errors = new HashMap<String, String>();
	
	ex.getBindingResult().getFieldErrors().forEach((error) -> {
	    String fieldName = error.getField();
	    String errorMessage = error.getDefaultMessage();
	    errors.put(fieldName, errorMessage);
	});
	
	return errors;
    }
	*/

}
