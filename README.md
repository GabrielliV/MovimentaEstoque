Inicialmente, para criar o banco de dados, será necessário subir a aplicação, acessar a URL http://localhost:8081/h2 e informar o caminho do banco exatamente como está definido no application.properties. Caso queira criar em outra pasta, basta alterar o destino tanto na propertie spring.datasource.url quanto na configuração do banco de dados.

A seguir serão listados exemplos de como criar, ler, alterar e deletar dados através do Postman.

O tipo do produto poderá ser Eletrônico, Eletrodoméstico ou Móvel, representados pelos números 0, 1 e 2, respectivamente.

E o tipo de movimentação no estoque de um produto poderá ser entrada ou saída, representados pelos números 0 e 1, respectivamente.

Para criar um produto será necessário fazer uma requisição para a URL http://localhost:8081/produto do tipo POST, onde serão informados os dados em formato JSON na aba Body, conforme exemplo a seguir:

{

"descricao": "Bancada",

"tipo": 2,

"valor": "200.00",

"qtdeEstoque": 10

}

Lembrando que o código do produto é gerado automaticamente.

Para consultar os produtos cadastrados será necessário fazer uma requisição para a URL http://localhost:8081/produto do tipo GET.

Para consultar um produto específico será necessário fazer uma requisição para a URL http://localhost:8081/produto/ e informar o código na URL após a última barra (“/”). Exemplo: http://localhost:8081/produto/1. Será um GET.

Para alterar um produto basta informar, também, o código do produto no JSON, do tipo GET, como no exemplo a seguir:

{

"codigo": 51,

"descricao": "Bancada",

"tipo": 2,

"valor": "250.00",

"qtdeEstoque": 1

}

Para deletar um produto será necessário fazer uma requisição do tipo GET para a URL http://localhost:8081/produto/, informando o código do produto- após a última barra (“/”). Exemplo: http://localhost:8081/produto/12.

Para realizar uma movimentação de estoque em algum produto, será necessário fazer uma requisição do tipo GET para a URL http://localhost:8081/estoque, conforme exemplo a seguir:

{

"produto": "1",

"tipoMovimentacao": "1",

"valorVenda": "400.00",

"qtdeMovimentada": "5"

}

Para consultar os produtos cadastrados de um tipo específico (eletrônico, eletrodoméstico ou móvel) será necessário informar na URL http://localhost:8081/estoque/tipo/, o tipo de produto em letra maiúscula e sem acentos, ou seja, ELETRONICO, ELETRODOMESTICO ou MOVEL. Será um GET, como por exemplo http://localhost:8081/estoque/tipo/MOVEL.

Para consultar o lucro de um produto específico será necessário fazer uma requisição do tipo GET para a URL http://localhost:8081/estoque/lucro/, informando o número do produto após a barra, como por exemplo http://localhost:8081/estoque/lucro/1. O valor do lucro é o resultado do valor de venda - o valor do produto pelo fornecedor. Como a movimentação de estoque pode ser realizada com a quantidade maior que 1 (um), o lucro terá o seguinte cálculo: (valor de venda - valor do fornecedor) * quantidade de movimentação do tipo saída.
