package com.example.virasatnammaguidetourismtravelling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.Coil
import coil.ImageLoader
import okhttp3.OkHttpClient
import com.example.virasatnammaguidetourismtravelling.ui.screens.LoginScreen
import com.example.virasatnammaguidetourismtravelling.ui.screens.MainScreen
import com.example.virasatnammaguidetourismtravelling.ui.screens.SignUpScreen
import com.example.virasatnammaguidetourismtravelling.ui.theme.VirasatNammaGuideTourismTravellingTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Coil to send a standard browser User-Agent so Wikimedia Commons and other servers don't block image requests
        val imageLoader = ImageLoader.Builder(this)
            .okHttpClient {
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 VirasatNammaGuide/1.0")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }
            .crossfade(true)
            .build()
        Coil.setImageLoader(imageLoader)

        setContent {
            VirasatNammaGuideTourismTravellingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VirasatApp()
                }
            }
        }
    }
}

@Composable
fun VirasatApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main")
                },
                onNavigateToSignUp = {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate("main")
                },
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }

        composable("main") {
            MainScreen()
        }
    }
}