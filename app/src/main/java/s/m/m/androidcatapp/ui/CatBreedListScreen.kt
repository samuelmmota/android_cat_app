package s.m.m.androidcatapp.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.viewmodel.BreedViewModel
import androidx.compose.runtime.getValue

@Composable
fun CatBreedListScreen() {
    val viewModel: BreedViewModel = viewModel()
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
                CatBreedItem(breed)
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

@Composable
fun CatBreedItem(breed: CatBreed) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Log.e("TESTE,", "test" + breed.breedReferenceImageUrl.toString())
        Image(
            painter = rememberImagePainter(breed.breedReferenceImageUrl),
            contentDescription = breed.name,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = breed.name,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCatBreedListScreen() {
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
            CatBreedItem(breed)
        }
    }
}