# Consulta de valores de veículos pela tabela FIPE

Aplicação Java que utiliza o Spring Framework para consultar a tabela FIPE e exibir informações detalhadas sobre veículos, incluindo ano, valor, marca, modelo e tipo de combustível.

---

## Funcionalidades

- Seleção do tipo de veículo (carro, moto, caminhão).
- Listagem de marcas com códigos para seleção.
- Busca de modelos por trecho do nome.
- Exibição de valores históricos do veículo selecionado, organizados por ano.

---

## Pré-requisitos

- Java JDK 8+
- Maven
- Conexão com a internet (para consumo da API FIPE)

---
<!--
## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/viniciusgrangeiro/tabela-fipe-spring
2. Compile o projeto com Maven:
    ```bash
    mvn clean install
3. Inicie a aplicação:
    ```bash
    mvn spring-boot:run
-->
## Uso
1. **Selecione o tipo de veículo:**

    Digite `carros`, `motos` ou `caminhoes`.


2. **Escolha a marca:**

    O sistema listará marcas disponíveis (ex:`Fiat: 22`, `Volkswagen: 21`).

    Insira o **código** da marca desejada.

3. **Busque o modelo:**

    Digite um trecho do nome do modelo (ex: `Palio`).

    O sistema mostrará modelos correspondentes com seus códigos.

4. **Selecione o modelo:**

    Insira o **código** do modelo para visualizar todos os anos e valores registrados.

## Exemplo de Saída
```
Exibindo todos os valores e o ano do veículo escolhido:
Veiculo[valor=R$ 15.287,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2003, combustivel=Gasolina]
Veiculo[valor=R$ 14.914,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2002, combustivel=Gasolina]
...
```

## Tecnologias Utilizadas

- Spring Boot

- Java

- Maven

- API FIPE

---
**Nota:** Os dados são fornecidos pela API FIPE e podem variar conforme atualizações da tabela oficial. [API Link](https://deividfortuna.github.io/fipe/?ref=public_apis&utm_medium=website)
