package com.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun Mainui() {
  var expanded by remember { mutableStateOf(false) }

  DropdownBox(expanded = expanded) { expanded = it }
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

