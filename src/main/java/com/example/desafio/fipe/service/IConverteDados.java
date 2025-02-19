package com.example.desafio.fipe.service;

import java.util.List;

public interface IConverteDados {
    // ex: public integer obtemResultado(Integer a, Integer b);
    <T> T obterDados(String json, Class<T> tClass);

    // Esse metodo criara uma lista apartir de um Json
    <T> List<T> obterLista(String json, Class<T> tClass);
}
