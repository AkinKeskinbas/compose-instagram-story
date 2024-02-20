package com.keak.composeinstagramstory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.keak.composeinstagramstory.ui.theme.ComposeInstagramStoryTheme
import com.keak.instagramstory.InstagramStory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeInstagramStoryTheme {
                val imageList = listOf(
                    "https://i.pinimg.com/564x/42/72/18/42721820449647f3fee20e883ca96e97.jpg",
                    "https://i.pinimg.com/564x/77/92/9d/77929d5fda5ebebf6a6dc023c20e5367.jpg",
                    "https://i.pinimg.com/564x/11/ae/75/11ae75df1e4c015f6ce6548f40e9d8a2.jpg"
                )
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InstagramStory(
                        imageList = imageList,
                        modifier = Modifier.padding(paddingValues = innerPadding),
                        content = { page->
                            Box(modifier = Modifier) {
                                AsyncImage(
                                    model = imageList[page],
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    )
                }
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
    ComposeInstagramStoryTheme {
        Greeting("Android")
    }
}