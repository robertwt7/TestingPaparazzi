// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.2.0" apply false
  id("org.jetbrains.kotlin.android") version "1.9.0" apply false
  id("com.android.library") version "8.2.0" apply false
  id("app.cash.paparazzi") version "1.3.2" apply false
}
subprojects {
  plugins.withId("app.cash.paparazzi") {
    // Defer until afterEvaluate so that testImplementation is created by Android plugin.
    afterEvaluate {
      dependencies.constraints {
        add("testImplementation", "com.google.guava:guava") {
          attributes {
            attribute(
              TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
              objects.named(TargetJvmEnvironment::class, TargetJvmEnvironment.STANDARD_JVM)
            )
          }
          because("LayoutLib and sdk-common depend on Guava's -jre published variant." +
            "See https://github.com/cashapp/paparazzi/issues/906.")
        }
      }
    }
  }
}