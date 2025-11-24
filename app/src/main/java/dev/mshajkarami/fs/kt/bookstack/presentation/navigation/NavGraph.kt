package dev.mshajkarami.fs.kt.bookstack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.mshajkarami.fs.kt.bookstack.presentation.AllBooksByCategoryScreen
import dev.mshajkarami.fs.kt.bookstack.presentation.HomeScreen
import dev.mshajkarami.fs.kt.bookstack.presentation.PdfViewerScreen


@Composable
fun NavGraph(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Routes.HomeScreen) {

        composable<Routes.HomeScreen>{
            HomeScreen(navHostController = navHostController)
        }

        composable<Routes.ShowPdfScreen> {
            backStackEntry ->
            val data : Routes.ShowPdfScreen = backStackEntry.toRoute()

            PdfViewerScreen(url = data.url)
        }

        composable<Routes.BooksByCategory> {
                backStackEntry ->
            val data2 : Routes.BooksByCategory = backStackEntry.toRoute()
            AllBooksByCategoryScreen(category = data2.category , navHostController = navHostController)
        }



    }

}