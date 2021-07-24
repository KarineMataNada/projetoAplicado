package com.serratec.trabalhoAplicado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.trabalhoAplicado.service.CepService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/endereco")
public class EnderecoController {

    @Autowired
    private CepService _servicoCep;

    @GetMapping("/enderecos/{cep}") //Aqui o cliente pode consultar se o Cep dele está correto antes de se cadastrar
    public ResponseEntity<?> obterEnderecoPorCep(@PathVariable("cep") String cep){

        var endereco = _servicoCep.obterEnderecoPorCep(cep);

        return ResponseEntity.ok(endereco);
    }
}