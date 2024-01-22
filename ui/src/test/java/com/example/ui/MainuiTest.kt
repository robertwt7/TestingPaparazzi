package com.example.ui

import DropdownMaterial
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.InstantAnimationsRule
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode.FULL_EXPAND
import org.junit.Test

import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainuiTest {
  @get:Rule
  val paparazzi = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_5,
    theme = "android:Theme.Material.Light.NoActionBar",
    renderingMode = FULL_EXPAND
  )
  @get:Rule
  val instantAnimationsRule = InstantAnimationsRule()

  @Test
  fun exposedDropdownBox() {
    paparazzi.snapshot {
      ExposedDropdownBox(expanded = true, {})
    }
  }

  @Test
  fun dropdownBox() {
    paparazzi.snapshot {
      DropdownBox(expanded = true, {})
    }
  }


  @Test
  fun dropdownMaterial() {
    paparazzi.snapshot {
      DropdownMaterial(expanded = true, {})
    }
  }
}