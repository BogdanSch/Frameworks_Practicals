package com.example.practical3

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AppNavigationWrapper(
    currentActivity: Class<out Activity>,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(currentActivity, drawerState)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(currentActivity, drawerState)
            }
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}

@Composable
fun DrawerContent(currentActivity: Class<out Activity>, drawerState: DrawerState, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    NavigationDrawerItem(
        label = { Text(stringResource(R.string.nav_difference)) },
        selected = currentActivity == MainActivity::class.java,
        onClick = {
            scope.launch { drawerState.close() }
            if (currentActivity != MainActivity::class.java) {
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
    Spacer(modifier = Modifier.height(8.dp))
    NavigationDrawerItem(
        label = { Text(stringResource(R.string.nav_guess_game)) },
        selected = currentActivity == GuessGameActivity::class.java,
        onClick = {
            scope.launch { drawerState.close() }
            if (currentActivity != GuessGameActivity::class.java) {
                context.startActivity(Intent(context, GuessGameActivity::class.java))
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(currentActivity: Class<out Activity>, drawerState: DrawerState, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        title = {
            Text(
                if (currentActivity == MainActivity::class.java)
                    stringResource(R.string.nav_difference)
                else
                    stringResource(R.string.nav_guess_game)
            )
        },
        navigationIcon = {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier.padding(start = 16.dp, end = 12.dp).size(32.dp))
            }
        }
    )
}