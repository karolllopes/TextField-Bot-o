package com.example.exemplokotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exemplokotlin.ui.theme.DebugButtonColors
import com.example.exemplokotlin.ui.theme.ErrorButtonColors
import com.example.exemplokotlin.ui.theme.ExemplokotlinTheme
import com.example.exemplokotlin.ui.theme.InfoButtonColors
import com.example.exemplokotlin.ui.theme.WarningButtonColors

const val TAG = "TestAndroid"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExemplokotlinTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_etec),
                contentDescription = "Logo da Etec Zona Leste",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.onSecondaryContainer, CircleShape)
            )

            Greeting("Atividade de PAM II")

            var nome by remember { mutableStateOf("") }

            TextCampo(
                text = nome,
                onTextChanged = { novoNome -> nome = novoNome }
            )

            ActionButton(
                text = "Nota: I",
                buttonColors = ErrorButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.e(TAG, "Você apertou o botão da Nota I")
            }

            ActionButton(
                text = "Nota: R",
                buttonColors = WarningButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.w(TAG, "Você apertou o botão da Nota R")
            }

            ActionButton(
                text = "Nota: B",
                buttonColors = DebugButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                /* Ação no Botão */
                Log.d(TAG, "Você apertou o botão da Nota B")
            }

            ActionButton(
                text = "Nota: MB",
                buttonColors = InfoButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                /* Ação no Botão */
                Log.i(TAG, "Você apertou o botão da Nota MB, Parabéns")
            }


        }
    }
}


@Composable
fun ActionButton(
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    block: () -> Unit
) {
    ElevatedButton(
        onClick = block,
        shape = RoundedCornerShape(5.dp),
        colors = buttonColors,
        modifier = Modifier
    )
    {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextCampo(
    text: String,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = { newValue ->
            onTextChanged(newValue)
            Log.d("Nome", newValue)
        },
        label = { Text("Nome:") },
        placeholder = { Text("Digite seu Nome: ") }
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier){
    val nome = "PAM II"
    Text(
        text = "Atividade de $nome!",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 24.sp
    )
}

@Preview(showBackground = true, widthDp = 150, heightDp = 200)
@Composable
fun AppPreview(){
    ExemplokotlinTheme {
        App()
    }
}

@Preview
@Composable
fun ActionButtonPreview(){
    ActionButton(text = "Cadastrar"){

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExemplokotlinTheme {
        Greeting("JetPack")
    }
}