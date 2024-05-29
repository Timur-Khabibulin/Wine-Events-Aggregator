package timurkhabibulin.wineeventsaggregator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timurkhabibulin.wineeventsaggregator.ui.MainScreen
import timurkhabibulin.wineeventsaggregator.ui.NavigationItem
import timurkhabibulin.wineeventsaggregator.ui.theme.WineEventsAggregatorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WineEventsAggregatorTheme {
                Menu()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showSystemUi = true
    )
    @Composable
    private fun Menu() {
        val items = listOf(
            NavigationItem(
                title = stringResource(R.string.main),
                icon = Icons.Default.Home
            ),
            NavigationItem(
                title = stringResource(R.string.archive),
                icon = Icons.Default.DateRange
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                icon = Icons.Default.AccountCircle
            )
        )
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(50.dp))
                    items.forEachIndexed { index, navigationItem ->
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = navigationItem.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Black
                                )
                            },
                            selected = false,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = navigationItem.title
                                )
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = items[selectedItemIndex].title,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Black
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                when (selectedItemIndex) {
                    0 -> {
                        MainScreen.MainScreen(
                            modifier = Modifier.padding(paddingValues)
                        )
                    }

                    1 -> {
                        MainScreen.MainScreen(
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}
