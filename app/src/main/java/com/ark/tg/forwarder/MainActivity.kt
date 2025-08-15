package com.ark.tg.forwarder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.ark.tg.forwarder.presentation.features.home.screen.HomeScreen
import com.ark.tg.forwarder.presentation.navigation.Destinations
import com.ark.tg.forwarder.presentation.navigation.RootNavHost
import com.ark.tg.forwarder.ui.theme.TGForwarderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TGForwarderTheme {
                Surface {
                    RootNavHost(startDestination = Destinations.Home)
                }
            }
        }
    }
}
