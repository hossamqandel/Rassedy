package dev.hossam.raseedy.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.hossam.raseedy.view.home.RaseedyActivity
import dev.hossam.raseedy.view.splash.components.Splash
import dev.hossam.raseedy.ui.theme.RaseedyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
//                // navigation bar
                systemUiController.isNavigationBarVisible = false

                // status bar
                systemUiController.isStatusBarVisible = false

            }
            RaseedyTheme {

                Surface(modifier = Modifier.fillMaxSize()) {
                    Splash()
                }
            }
        }

        lifecycleScope.launch {
                delay(1300)
                Intent(this@MainActivity, RaseedyActivity::class.java).also {
                    startActivity(it)
            }
        }
    }
}
