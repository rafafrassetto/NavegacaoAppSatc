package com.example.navegacaoappsatc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacaoappsatc.ui.theme.NavegacaoAppSatcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoAppSatcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var tempoInicio by remember { mutableLongStateOf(0L) }

    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = { slideInHorizontally { it } + fadeIn() },
        exitTransition = { slideOutHorizontally { -it } + fadeOut() },
        popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
        popExitTransition = { slideOutHorizontally { it } + fadeOut() }
    ) {
        composable("home") {
            HomeScreen(onStartClick = {
                tempoInicio = System.currentTimeMillis()
                navController.navigate("pista1")
            })
        }

        composable("pista1") {
            PistaScreen(
                pista = "O que é, o que é: cai em pé e corre deitado?",
                respostaCorreta = "chuva",
                onNext = { navController.navigate("pista2") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("pista2") {
            PistaScreen(
                pista = "Tem cabeça, tem dente, tem barba, não é bicho e não é gente.",
                respostaCorreta = "alho",
                onNext = { navController.navigate("pista3") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("pista3") {
            PistaScreen(
                pista = "Nasce grande e morre pequeno.",
                respostaCorreta = "lapis",
                onNext = { navController.navigate("tesouro") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("tesouro") {
            val tempoDecorrido = (System.currentTimeMillis() - tempoInicio) / 1000
            TreasureScreen(
                tempoSegundos = tempoDecorrido,
                onRestart = {
                    navController.popBackStack("home", inclusive = false)
                }
            )
        }
    }
}

@Composable
fun HomeScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo à Caça ao Tesouro!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onStartClick) {
            Text("Iniciar caça ao tesouro")
        }
    }
}

@Composable
fun PistaScreen(
    pista: String,
    respostaCorreta: String,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    var resposta by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pista:", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = pista,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = resposta,
            onValueChange = {
                resposta = it
                erro = false
            },
            label = { Text("Sua resposta") },
            singleLine = true
        )

        if (erro) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Resposta incorreta. Tente novamente!",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onBack) {
                Text("Voltar")
            }
            Button(onClick = {
                val respostaFormatada = resposta.trim().replace("á", "a").lowercase()
                if (respostaFormatada == respostaCorreta.lowercase()) {
                    onNext()
                } else {
                    erro = true
                }
            }) {
                Text("Próxima Pista")
            }
        }
    }
}

@Composable
fun TreasureScreen(tempoSegundos: Long, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Parabéns! Você encontrou o tesouro!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tempo total: $tempoSegundos segundos",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onRestart) {
            Text("Recomeçar")
        }
    }
}