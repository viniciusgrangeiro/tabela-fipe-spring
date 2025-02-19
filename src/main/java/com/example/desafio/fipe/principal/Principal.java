package com.example.desafio.fipe.principal;

import com.example.desafio.fipe.modelos.Dados;
import com.example.desafio.fipe.modelos.Modelos;
import com.example.desafio.fipe.modelos.Veiculos;
import com.example.desafio.fipe.service.ConsumoApi;
import com.example.desafio.fipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        var menu = """
                *** OPCOES ***
                Motos
                Carros
                Caminhões           
                
                Digite uma das opcoes para consulta:     
                """;
        System.out.println(menu);
        var opcao = leitura.nextLine().toLowerCase();

        String endereco;

        // Verificando o que o usuario digitou como opcao, e encaminhando para a respectiva URL
        if (opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        // TRABALHANDO COM AS MARCAS
        // Recebendo a resposta Json
        var json = consumoApi.obterDados(endereco);

        // Imprimindo o Json
        System.out.println(json);

        //Criando uma Colecao
        var marcas = conversor.obterLista(json, Dados.class);
        // Imprimindo por ordem crescente o codigo
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        // TRABALHANDO COM OS MODELOS
        System.out.println("\nInforme o codigo da marca");
        var codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";

        // Obtendo dados do modelos
        json = consumoApi.obterDados(endereco);

        // Criando um objeto do tipo Lista a partir do json
        // Aqui usamos o .obterDados ao inves do .obterLista pois o modeloLista,
        // sera um objeto da classe modelo, que por sua vez, ja é uma lista
        var modelosLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca");
        // Mais uma vez usando stream, no atributo modelos() que é uma lista, para ordenar a saida
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        // TARBALHANDO COM OS MODELOS DOS CARROS DESEJADOS
        System.out.println("\nDigite o nome do carro a ser buscado");
        var nomeVeiculo = leitura.nextLine();

        // Criando uma lista que ira conter todos os modelos do carro desejado
        List<Dados> modelosFiltrados = modelosLista.modelos().stream()
                // Agora iremos buscar na lista modelosLista, os modelos de carro que o usuario ecolheu
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                    .toList(); // Depois de filtrar, adiciona esses dados a lista modelosFiltrados

        System.out.println("\nModelos Filtrados");
        modelosFiltrados.forEach(System.out::println);

        // TRABALHANDO COM OS VALORES DE TODOS OS ANOS DO CARRO DESEJADO
        System.out.println("\nInforme o codigo do modelo do carro para a busca de avaliacao e valores");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(endereco);

        // Criando uma lista que ira conter os dados dos Anos do json
        List<Dados> anos = conversor.obterLista(json, Dados.class);

        // Precisamos passar por toda a lista de anos, obter as informações dos carros em seus anos,
        // e colocar tudo em uma nova lista
        List<Veiculos> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            // Essa variavel ira receber a lista de ano[i] o valor de codigo, que sera o ano desse indice [i]
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            // Consumindo dados do veiculo no ano "anos.get(i).codigo()"
            json = consumoApi.obterDados(enderecoAnos);

            // Desserealizando os dados e transformando num objeto veiculo
            Veiculos veiculo = conversor.obterDados(json, Veiculos.class);

            // Adicionando veiculo a lista Veiculos
            veiculos.add(veiculo);
        }

        System.out.println("\nExibindo todos os valores e o ano do veiculo escolhido");
        veiculos.forEach(System.out::println);

    }
}
