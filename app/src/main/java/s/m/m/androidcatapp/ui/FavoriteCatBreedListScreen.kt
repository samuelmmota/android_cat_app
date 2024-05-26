package s.m.m.androidcatapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.viewmodel.BreedViewModel
import androidx.compose.runtime.getValue

@Composable
fun FavoriteCatBreedListScreen(viewModel: BreedViewModel) {
    val breeds by viewModel.favoriteBreeds.collectAsState()

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(breeds) { breed ->
                CatBreedItem(breed, viewModel::toggleFavorite)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteCatBreedListScreen() {
    val sampleBreeds = listOf(
        CatBreed("1", "Abyssinian", "0XYvRd7oD"),
        CatBreed("2", "Aegean", "ozEvzdVM-"),
        CatBreed(
            "3",
            "American Bobtail",
            "hBXicehMA",
        )
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(sampleBreeds) { breed ->
            CatBreedItem(breed = breed, onFavoriteClick = {})
        }
    }
}