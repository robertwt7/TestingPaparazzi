import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMaterial(expanded: Boolean = true, onExpandedChange: (Boolean) -> Unit) {

  val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
  var selectedOptionText by remember { mutableStateOf(options[0]) }
// We want to react on tap/press on TextField to show menu
  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { onExpandedChange(it) },
  ) {
    TextField(
      readOnly = true,
      value = selectedOptionText,
      onValueChange = { },
      label = { Text("Label") },
      trailingIcon = {
        ExposedDropdownMenuDefaults.TrailingIcon(
          expanded = expanded
        )
      },
      colors = ExposedDropdownMenuDefaults.textFieldColors()
    )
    DropdownMenu(
      expanded = expanded,
      onDismissRequest = {
        onExpandedChange(false)
      }
    ) {
      options.forEach { selectionOption ->
        DropdownMenuItem(
          onClick = {
            selectedOptionText = selectionOption
            onExpandedChange(false)
          }
        ) {
          Text(text = selectionOption)
        }
      }
    }
  }
}