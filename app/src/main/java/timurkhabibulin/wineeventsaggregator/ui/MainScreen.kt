package timurkhabibulin.wineeventsaggregator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import timurkhabibulin.wineeventsaggregator.R
import timurkhabibulin.wineeventsaggregator.domain.Event

class MainScreen {

    @Preview(
        showSystemUi = true
    )
    @Composable
    private fun MainScreenPreview() {
        MainScreen(
            items = listOf(),
            initialQuery = "",
            onStartSearch = {},
            onEventClick = {}
        )
    }

    companion object {

        @Composable
        fun MainScreen(
            modifier: Modifier = Modifier,
            viewModel: MainScreenViewModel = hiltViewModel()
        ) {
            LaunchedEffect(key1 = null) {
                viewModel.makeSearch("")
            }
            val initialQuery = viewModel.searchQuery.collectAsState()

            var dialogVisible by remember {
                mutableStateOf(false)
            }
            var eventForDetail by remember {
                mutableStateOf<Event?>(null)
            }

            MainScreen(
                modifier = modifier,
                items = viewModel.events.value,
                initialQuery = initialQuery.value,
                onStartSearch = viewModel::makeSearch,
                onEventClick = {
                    dialogVisible = true
                    eventForDetail = it
                }
            )

            if (dialogVisible) {
                EventDetailDialog(
                    event = eventForDetail!!,
                    signUpToEvent = viewModel::signUpToEvent,
                    onDismissRequest = {
                        dialogVisible = false
                    }
                )
            }
        }

        @Composable
        private fun MainScreen(
            modifier: Modifier = Modifier,
            items: List<Event>,
            initialQuery: String,
            onStartSearch: (String) -> Unit,
            onEventClick: (Event) -> Unit,
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SearchBar(
                    initialQuery = initialQuery,
                    onStartSearch = onStartSearch
                )
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items.size) { index ->
                        EventCard(
                            event = items[index],
                            onEventClick = onEventClick
                        )
                    }
                }
            }
        }

        @Composable
        private fun EventCard(
            modifier: Modifier = Modifier,
            event: Event,
            onEventClick: (Event) -> Unit,
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onEventClick(event)
                    },
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.img_placeholder),
                    contentDescription = null
                )
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Адрес: ${event.name}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Text(
                            text = "Время: ${event.address}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Text(
                            text = "Продолжительность: ${event.duration}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "Цена: ${event.price}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                    HorizontalDivider()
                }
            }
        }

        @Composable
        private fun SearchBar(
            modifier: Modifier = Modifier,
            initialQuery: String,
            onStartSearch: (String) -> Unit
        ) {
            var query by remember {
                mutableStateOf(initialQuery)
            }
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = query,
                onValueChange = {
                    query = it
                },
                placeholder = {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Поиск по названию",
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                shape = RoundedCornerShape(50.dp),
//                trailingIcon = {
//                    Icon(
//                        modifier = Modifier.clickable { onStartSearch(query) },
//                        painter = painterResource(id = R.drawable.search),
//                        contentDescription = ""
//                    )
//                },
                maxLines = 1,
                keyboardActions = KeyboardActions(onSearch = {
                    onStartSearch(query)
                }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
            )
        }

        @Composable
        private fun EventDetailDialog(
            modifier: Modifier = Modifier,
            event: Event,
            signUpToEvent: (Event) -> Unit,
            onDismissRequest: () -> Unit
        ) {
            Dialog(
                onDismissRequest = onDismissRequest
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    EventCard(
                        event = event,
                        onEventClick = {}
                    )
                    Text(
                        text = "Описание",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    HorizontalDivider()
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Button(
                        modifier = Modifier
                            .size(width = 336.dp, height = 50.dp),
                        onClick = { signUpToEvent(event) },
                        shape = RoundedCornerShape(100.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBA1B1B))
                    ) {
                        Text(
                            text = "Записаться",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
