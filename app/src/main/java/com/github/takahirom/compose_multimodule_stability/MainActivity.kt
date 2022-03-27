package com.github.takahirom.compose_multimodule_stability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.github.takahirom.compose_multimodule_stability.ui.theme.ComposemultimodulestabilityTheme
import com.github.takahirom.model.Article
import com.github.takahirom.model.Articles
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val mutableState = remember { mutableStateOf<Int>(0) }
      LaunchedEffect(Unit) {
        while (true) {
          delay(1000)
          mutableState.value = mutableState.value + 1
        }
      }
      val articles = remember {
        Articles(
          listOf(
            Article("Compose"),
            Article("Stability"),
          )
        )
      }
      ComposemultimodulestabilityTheme {
        mutableState.value
        Composable1(
          articles
        )
        Composable2(
          UiModel(
            articles
          )
        )
      }
    }
  }
}

@Composable
fun Composable1(articles: Articles) {
  println("Composable1")
  Text(text = "Hello $articles!")
}

@Stable
data class UiModel(val articles: Articles)

@Composable
fun Composable2(uiModel: UiModel) {
  println("Composable2")
  Text(text = "Hello $uiModel!")
  uiModel.articles.articles.forEach {
    Composable3(it)
  }
}

@Composable
fun Composable3(article: Article) {
  println("Composable3")
  Text(text = "Hello $article!")
}
