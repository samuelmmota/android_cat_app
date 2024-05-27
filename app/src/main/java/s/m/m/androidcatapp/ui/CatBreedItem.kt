package s.m.m.androidcatapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import s.m.m.androidcatapp.model.CatBreed

val sampleBreed = CatBreed(
    id = "1",
    name = "Abyssinian",
    breedReferenceId = "0XYvRd7oD",
    lifeSpan = "12 - 14",
    isFavorite = true
)

@Composable
fun CatBreedItem(breed: CatBreed, onFavoriteClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Box {
            Image(
                painter = rememberImagePainter(breed.breedReferenceImageUrl),
                contentDescription = breed.name,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { onFavoriteClick(breed.id) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = if (breed.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = if (breed.isFavorite) "Unmark as favorite" else "Mark as favorite",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = breed.name,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun FavoriteCatBreedItem(breed: CatBreed, onFavoriteClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Box {
            Image(
                painter = rememberImagePainter(breed.breedReferenceImageUrl),
                contentDescription = breed.name,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { onFavoriteClick(breed.id) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = if (breed.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = if (breed.isFavorite) "Unmark as favorite" else "Mark as favorite",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = breed.name,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "LifeSpan between ${breed.minLifeSpan} and ${breed.maxLifeSpan} years",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCatBreedItems() {
    CatBreedItem(breed = sampleBreed, onFavoriteClick = {})
    FavoriteCatBreedItem(breed = sampleBreed, onFavoriteClick = {})
}