package s.m.m.androidcatapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.viewmodel.BreedViewModel
import androidx.compose.runtime.getValue
import java.util.Locale

@Composable
fun FavoriteCatBreedListScreen(viewModel: BreedViewModel) {
    val breeds by viewModel.favoriteBreeds.collectAsState()
    val averageFavoriteBreedsLifeSpan by viewModel.averageFavoriteBreedsLifeSpan.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = String.format(Locale.US, "Average Lifespan: %.2f years", averageFavoriteBreedsLifeSpan),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(breeds) { breed ->
                FavoriteCatBreedItem(breed, viewModel::toggleFavorite)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteCatBreedListScreen() {
    val sampleBreeds = listOf(
        CatBreed("1", "Abyssinian", "0XYvRd7oD", "9-11"),
        CatBreed("2", "Aegean", "ozEvzdVM-", "13-15"),
        CatBreed(
            "3",
            "American Bobtail",
            "hBXicehMA",
            "16-20"
        )
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(sampleBreeds) { breed ->
            FavoriteCatBreedItem(breed = breed, onFavoriteClick = {})
        }
    }
}