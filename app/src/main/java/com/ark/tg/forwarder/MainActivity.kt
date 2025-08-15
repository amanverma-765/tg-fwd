package com.ark.tg.forwarder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.ark.tg.forwarder.presentation.navigation.Destinations
import com.ark.tg.forwarder.presentation.navigation.RootNavHost
import com.ark.tg.forwarder.presentation.theme.TGForwarderTheme

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
