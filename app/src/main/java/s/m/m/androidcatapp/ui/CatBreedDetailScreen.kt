package s.m.m.androidcatapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import s.m.m.androidcatapp.model.CatBreed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun BreedDetailScreen(navController: NavController, breed: CatBreed, onFavoriteClick: (String) -> Unit) {
    var isFavorite by remember { mutableStateOf(breed.isFavorite) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = breed.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        IconButton(
                            onClick = {
                                isFavorite = !isFavorite
                                onFavoriteClick(breed.id)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,                                contentDescription = if (breed.isFavorite) "Unmark as favorite" else "Mark as favorite",
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = rememberImagePainter(breed.breedReferenceImageUrl),
                contentDescription = breed.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = breed.name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Lifespan: ${breed.minLifeSpan} - ${breed.maxLifeSpan} years",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Origin: ${breed.origin}",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Temperament: ${breed.temperament}",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Description",
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = breed.description + "\n\n",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
