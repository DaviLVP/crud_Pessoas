package com.trabalho.demo.service;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

public class ResourceNotFoundException extends RuntimeJsonMappingException {
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
