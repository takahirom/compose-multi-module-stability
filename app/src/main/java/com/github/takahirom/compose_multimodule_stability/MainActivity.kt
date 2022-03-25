package com.github.takahirom.compose_multimodule_stability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.Preview
import com.github.takahirom.compose_multimodule_stability.ui.theme.ComposemultimodulestabilityTheme
import com.github.takahirom.model.Article
import com.github.takahirom.model.Articles

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposemultimodulestabilityTheme {
        Composable1(
          Articles(
            listOf(
              Article("Compose"),
              Article("Stability"),
            )
          )
        )
        Composable2(
          UiModel(
            Articles(
              listOf(
                Article("Compose"),
                Article("Stability"),
              )
            )
          )
        )
      }
    }
  }
}

@Composable
fun Composable1(articles: Articles) {
  Text(text = "Hello $articles!")
}

@Stable
data class UiModel(val articles: Articles)

@Composable
fun Composable2(uiModel: UiModel) {
  Text(text = "Hello $uiModel!")
  uiModel.articles.articles.forEach {
    Composable3(it)
  }
}

@Composable
fun Composable3(article: Article) {
  Text(text = "Hello $article!")
}
