package com.example.composede

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composede.ui.theme.ComposeDeTheme
import kotlin.random.Random.Default.nextInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    var random: Int = nextInt(1, 1000)
                    GuessNumber(random)
                }
            }
        }
    }


@Composable
fun Greeting(name: String) {
    Text(text = "NumberGuessingGame")
}

@Composable
fun GuessNumber(random: Int) {
    var display = remember {
        mutableStateOf(0)
    }
    var count = remember { mutableStateOf(0) }
    var randomNumber = remember { mutableStateOf(random)}
    var output = remember { mutableStateOf("")}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text (
            text = "Guess the number (1-1000)",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )


        var text by remember { mutableStateOf("") }
        TextField(
            value = text,

            onValueChange = { text = it },
            label = { Text("Insert the number ") },
            modifier = Modifier.height(80.dp).fillMaxHeight()

        )


        Text (
            text = ""
        )

            if (display.value == 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 118.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val focusManager3 = LocalFocusManager.current
                    Button(
                        onClick = {
                            focusManager3.clearFocus()
                            text = ""
                            var random = nextInt(1, 1000)
                            randomNumber.value = random
                            output.value = ""
                            count.value = 0
                            display.value = 0

                        }
                    ) {
                        Text(text = "Newgame", fontSize = 30.sp)
                    }
                }
            }
            else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {

                    val focusManager2 = LocalFocusManager.current
                Button(
                    onClick = {
                        focusManager2.clearFocus()
                        text = ""
                        var random = nextInt(1, 1000)
                        randomNumber.value = random
                        output.value = ""
                        count.value = 0

                    }
                ) {
                    Text(text = "Reset", fontSize = 30.sp)
                }
                    val focusManager = LocalFocusManager.current
                Button(

                    onClick = {

                        focusManager.clearFocus()
                        var input = 0

                        count.value = count.value + 1


                        try {
                            input = text.toInt()
                        } catch (text: NumberFormatException) {
                            output.value = "Please enter only number"

                        }

                        val checkAnswer = if (input > randomNumber.value) {
                            output.value = "The number < $input"
                        } else if (input < randomNumber.value) {
                            output.value = "The number > $input"
                        } else {
                            output.value = "Congratulations the number is = $input !"
                            display.value = 1
                        }



                    }
                ) {
                    Text(text = "Guess", fontSize = 30.sp)

                }
            }
        }

        Text (
            text = "${output.value}",
            fontSize = 18.sp
        )

        Text (
            text = "You have guessed ${count.value} times",
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDeTheme {
        Greeting("Android")
    }
}
}