package com.example.testingpaparazzi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testingpaparazzi.ui.theme.TestingPaparazziTheme
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      var expanded by remember { mutableStateOf(false) }
      TestingPaparazziTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Column (modifier = Modifier.padding(16.dp)){
            Greeting("Android")
            Box {
              DropdownBox(
                expanded = expanded,
                onExpandedChange = { bool -> expanded = bool }
              )
            }
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownBox(expanded: Boolean = true, onExpandedChange: (Boolean) -> Unit) {
  val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
  var selectedOptionText by remember { mutableStateOf(options[0]) }
// We want to react on tap/press on TextField to show menu
  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { onExpandedChange(it) },
  ) {
    TextField(
      // The `menuAnchor` modifier must be passed to the text field for correctness.
      modifier = Modifier.menuAnchor(),
      readOnly = true,
      value = selectedOptionText,
      onValueChange = {},
      label = { Text("Label") },
      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
      colors = ExposedDropdownMenuDefaults.textFieldColors(),
    )
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { onExpandedChange(false) },
    ) {
      options.forEach { selectionOption ->
        DropdownMenuItem(
          text = { Text(selectionOption) },
          onClick = {
            selectedOptionText = selectionOption
            onExpandedChange(false)
          },
          contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
        )
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TestingPaparazziTheme {
    Greeting("Android")
  }
}