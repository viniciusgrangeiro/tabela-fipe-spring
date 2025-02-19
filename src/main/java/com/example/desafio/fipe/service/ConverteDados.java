package com.example.desafio.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> tClass) {
        try {
            // Desserealizando o Json
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Esse metodo criara uma lista<T> (qualquer tipo) a partir de um Json
    @Override
    public <T> List<T> obterLista(String json, Class<T> tClass) {
        // CollectionType é um tipo de colecao como o nome sugere
        CollectionType lista = mapper.getTypeFactory()
                // Construa uma colecao do tipo (Lista, e transforme em uma tClass)
                .constructCollectionType(List.class, tClass);

        try {
            return mapper.readValue(json, lista); //Transforma o Json recebido em uma Lista<T>
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
