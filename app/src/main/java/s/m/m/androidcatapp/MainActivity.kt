package s.m.m.androidcatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import s.m.m.androidcatapp.database.ApiDatabase
import s.m.m.androidcatapp.ui.BreedDetailScreen
import s.m.m.androidcatapp.ui.CatBreedListScreen
import s.m.m.androidcatapp.ui.FavoriteCatBreedListScreen
import s.m.m.androidcatapp.viewmodel.BreedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ApiDatabase.init(this)
        ApiDatabase.init(baseContext)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Navigation(navController, Modifier.padding(innerPadding))
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        val items = listOf(
            BottomNavItem(
                "cat_breeds",
                "Breeds",
                painterResource(id = R.drawable.ic_baseline_pets_24)
            ),
            BottomNavItem(
                "favorite_cat_breeds",
                "Favorites",
                painterResource(id = R.drawable.ic_baseline_star_24)
            )
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {
    val viewModel: BreedViewModel = viewModel()
    NavHost(navController, startDestination = "cat_breeds") {
        composable("cat_breeds") { CatBreedListScreen(viewModel,navController) }
        composable("favorite_cat_breeds") { FavoriteCatBreedListScreen(viewModel,navController) }
        composable("breedDetail/{breedId}") { backStackEntry ->
                val breedId = backStackEntry.arguments?.getString("breedId")
                breedId?.let {
                    val breed = viewModel.getCatBreed(it)
                    breed?.let { BreedDetailScreen(navController,it, onFavoriteClick = viewModel::toggleFavorite) }
                }
        }
    }
}

data class BottomNavItem(val route: String, val title: String, val icon: Painter)
