package com.matheuskeizo.applicativoscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.matheuskeizo.applicativoscreen.screens.LoginScreen
import com.matheuskeizo.applicativoscreen.screens.MenuScreen
import com.matheuskeizo.applicativoscreen.screens.PedidosScreen
import com.matheuskeizo.applicativoscreen.screens.PerfilScreen
import com.matheuskeizo.applicativoscreen.ui.theme.ApplicativoScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicativoScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "pedidos?cliente={cliente}", //novo parametro na rota, indicando que vai receber um cliente na navegação
                                    arguments = listOf(navArgument("cliente"){defaultValue= "Cliente generico"})) { // argumento esperado pela rota, existe um parametro chamado cliente, se não for usado colocar cliente generico
                            PedidosScreen(modifier = Modifier.padding(innerPadding), navController, it.arguments?.getString("cliente")) // pega o valor do parametro cliente, podendo ser null, e passsa para PedidosScreen
                        }
                        composable(route = "perfil/{nome}/{idade}", arguments = listOf( // Caminho de rota atualizado,
                            navArgument("nome"){type= NavType.StringType},
                            navArgument("idade"){type = NavType.StringType}
                        )) {// é um parametro para redirecionar a alguma rota
                            val nome: String? = it.arguments?.getString("nome","Usuário genérico") // serve para substituir o nome pelo devido nome e caso não tenha colcoar usuario generico como valor padrão
                            val idade: Int? = it.arguments?.getInt("idade", 0) // adcionou outro parametro (idade)
                            PerfilScreen(modifier = Modifier.padding(innerPadding), navController, nome!!, idade!!) // Passando o nome e a idade para o perfilScreen e o !! força o valor a não ser nulo
                        }
                    }
                }
            }
        }
    }
}
