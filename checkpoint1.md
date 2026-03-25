Descrição do projeto:
"Aplicativo" criado utilizando Jetpack Compose, com foco na navegação entre telas. A aplicação possui diferentes telas, como menu, perfil e pedidos, permitindo a transição entre elas por meio do NavController.

Objetivo da prova:
Avaliar a evolução do aluno de um projeto ja iniciado, aplicando conceitos de navegação entre telas utilizando o Jetpack Compose, com foco em passagem de parametros, com o aluno tendo que fazer uma explicação e mostrar conhecimento sobre o codigo e alterações que foram feitas. 

Alterações:
composable(route = "pedidos?cliente={cliente}", 
EXPLICAÇÃO: novo parametro na rota, indicando que vai receber um cliente na navegação

arguments = listOf(navArgument("cliente"){defaultValue= "Cliente generico"})) { 
EXPLICAÇÃO: É o argumento esperado pela rota, existe um parametro chamado cliente, e se não for usado colocar "cliente generico"

PedidosScreen(modifier = Modifier.padding(innerPadding), navController, it.arguments?.getString("cliente"))
EXPLICAÇÃO: Pega o valor do parametro cliente, podendo ser null, e passsa para a outra tela PedidosScreen

composable(route = "perfil/{nome}/{idade}", arguments = listOf(
EXPLICAÇÃO: Caminho de rota atualizado, com as variaveis nome e idade

val nome: String? = it.arguments?.getString("nome","Usuário genérico")
EXPLICAÇÃO: Serve para substituir o nome pelo devido nome e caso não tenha colcoar usuario generico como valor padrão

val idade: Int? = it.arguments?.getInt("idade", 0)
EXPLICAÇÃO: Adinção de outro parametro (idade)

PerfilScreen(modifier = Modifier.padding(innerPadding), navController, nome!!, idade!!)
EXPLICAÇÃO:  Passando o nome e a idade para o perfilScreen e o !! força o valor a não ser nulo

fun PerfilScreen(modifier: Modifier, navController: NavController, nome: String, idade: Int){
EXPLICAÇÃO: Adição do parametro nome e idade no PerfilScreen

text = "PERFIL - $nome tem $idade anos",
EXPLICAÇÃO: Exibe as variaveis nome e a idade recebido no parametro

fun PedidosScreen(modifier: Modifier = Modifier, navController: NavController, cliente: String?) { 
EXPLICAÇÃO: Adiciona o parametro cliente no PedidosScreen podendo ser opcional

text = "PEDIDOS - $cliente",
EXPLICAÇÃO: Exibe o nome cliente recebido no parametro

onClick = {navController.navigate("perfil/Fulano de tal/27")}, 
EXPLICAÇÃO: Esta enviando um valor junto com a navegação, sendo o parametro do nome o "Fulano de tal" na rota, e o "27" sendo a idade

onClick = { navController.navigate("pedidos?cliente=Cliente XPTO")}, 
EXPLICAÇÃO: Pedidos é a rota de tela, ?cliente é o parametro e o Cliente XPTO é o valor passado ao parametro, sendo recebidos no PedidosScreen e pode ser usado em outras telas
