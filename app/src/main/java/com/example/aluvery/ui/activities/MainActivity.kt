package com.example.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aluvery.model.Product
import com.example.aluvery.sampleData.sampleCandies
import com.example.aluvery.sampleData.sampleDrinks
import com.example.aluvery.ui.screens.HomeScreen
import com.example.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            })
        }
    }
}

@Composable
fun App(onFabClick: () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    HomeScreen(sectionalList)
                }
            }
        }
    }
}

val sectionalList: Map<String, List<Product>> = mapOf(
    "Promoções" to sampleCandies,
    "Salgados" to sampleDrinks,
    "Bebidas" to sampleCandies

)



