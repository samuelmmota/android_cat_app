package s.m.m.androidcatapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.viewmodel.BreedViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun CatBreedListScreen(viewModel: BreedViewModel, navController: NavController) {
    val breeds by viewModel.filteredBreeds.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column {
        SearchBar(
            query = searchQuery,
            onQueryChanged = viewModel::onSearchQueryChanged
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(breeds) { breed ->
                CatBreedItem(breed, onClick = { breedId ->
                    navController.navigate("breedDetail/$breedId")
                }, viewModel::toggleFavorite)
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        label = { Text("Search Breeds") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCatBreedListScreen() {
    val sampleBreeds = listOf(
        CatBreed(
            "1", "Abyssinian", "0XYvRd7oD", "9-11", "United States",
            "Lively, Social, Fun-loving, Relaxed, Affectionate",
            "Arabian Mau cats are social and energetic...."
        ),
        CatBreed(
            "2", "Aegean", "ozEvzdVM-", "13-15", "United States",
            "Lively, Social, Fun-loving, Relaxed, Affectionate",
            "Arabian Mau cats are social and energetic...."
        ),
        CatBreed(
            "3",
            "American Bobtail",
            "hBXicehMA",
            "16-20", "United States",
            "Lively, Social, Fun-loving, Relaxed, Affectionate",
            "Arabian Mau cats are social and energetic...."
        )
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(sampleBreeds) { breed ->
            CatBreedItem(breed = breed, onClick = {}, onFavoriteClick = {})
        }
    }
}