package dev.flyingpigs.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.flyingpigs.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("") }
    var outputUnit by remember { mutableStateOf("") }
    var fromCurrencyIsOpen by remember { mutableStateOf(false) }
    var toCurrencyIsOpen by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Red
    )

    //    fun converter(fromValue: Int, toValue: Int): Int {
    fun converter() {
        print("Converting the value")
        // ?: elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text("Unit converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Converting from $inputUnit to $outputUnit")
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                // Here goes what should happen when text is changed...
                inputValue = it
                println("Test is changed $it")
            },
            label = { Text("Enter Some value") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = {
                    fromCurrencyIsOpen = true
                }) {
                    Text(inputUnit.ifEmpty { "Select unit from" });
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = fromCurrencyIsOpen, onDismissRequest = {
                    fromCurrencyIsOpen = false
                }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            fromCurrencyIsOpen = false
                            inputUnit = "Centimeters"
                            iConversionFactor.value = 0.01
                            converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            fromCurrencyIsOpen = false
                            inputUnit = "Meters"
                            iConversionFactor.value = 1.0
                            converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            fromCurrencyIsOpen = false
                            inputUnit = "Feet"
                            iConversionFactor.value = 0.3048
                            converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            fromCurrencyIsOpen = false
                            inputUnit = "Milimeters"
                            iConversionFactor.value = 0.001
                            converter()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { toCurrencyIsOpen = true }) {
                    Text(outputUnit.ifEmpty { "to Unit" });
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(
                    expanded = toCurrencyIsOpen,
                    onDismissRequest = { toCurrencyIsOpen = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            toCurrencyIsOpen = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            toCurrencyIsOpen = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            toCurrencyIsOpen = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            toCurrencyIsOpen = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            converter()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue", style = MaterialTheme.typography.headlineMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}