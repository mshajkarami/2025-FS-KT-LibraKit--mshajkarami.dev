package dev.mshajkarami.fs.kt.bookstack.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.mshajkarami.fs.kt.bookstack.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {

    val drawableState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineLineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val urlHandler = LocalUriHandler.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ModalNavigationDrawer(
        drawerState = drawableState,
        gesturesEnabled = true,
        drawerContent = {

            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(250.dp)
                        .padding(16.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "App Logo ",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = true,
                        icon = { Icon(Icons.Filled.Info, "Home") },
                        onClick =
                            {
                                coroutineLineScope.launch {
                                    drawableState.close()
                                }
                            })
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Version : 1.0") },
                        selected = false,
                        icon = { Icon(Icons.Filled.Info, "Version") },
                        onClick =
                            {
                                coroutineLineScope.launch {
                                    drawableState.close()
                                }

                                Toast.makeText(context, "Version 1.0", Toast.LENGTH_SHORT).show()
                            })

                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Contact Me") },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.github),
                                contentDescription = "git hub",
                                modifier = Modifier.size(25.dp)
                            )
                        },
                        onClick =
                            {
                                urlHandler.openUri("https://github.com/mshajkarami")
                            })

                    Divider()
                    NavigationDrawerItem(
                        label = { Text("source code report") },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.bug),
                                contentDescription = "bug report",
                                modifier = Modifier.size(25.dp)
                            )
                        },
                        onClick =
                            {
                                urlHandler.openUri("https://github.com/mshajkarami")
                            })
                }
            }
        }
    ) {

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(title = {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Bool Library",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
                ,
                   navigationIcon = {
                       IconButton(onClick = {coroutineLineScope.launch{drawableState.open()}}) {

                           Icon(imageVector = Icons.Filled.Menu, contentDescription = "open drawer")
                       }
                   },
                    scrollBehavior = scrollBehavior
                )
            }
        ) {
            innerPadding ->
            Column (
                Modifier.padding(innerPadding).fillMaxSize()
            ) {

                TabScreen(navHostController = navHostController)

            }
        }


    }
}