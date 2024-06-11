package timurkhabibulin.wineeventsaggregator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import timurkhabibulin.wineeventsaggregator.R
import timurkhabibulin.wineeventsaggregator.domain.User

sealed class ProfileScreen {

    companion object {

        @Composable
        fun ProfileScreen(
            modifier: Modifier = Modifier,
            viewModel: ProfileScreenViewModel = hiltViewModel()
        ) {
            ProfileScreen(
                user = viewModel.user,
                onSaveData = {},
                modifier = modifier
            )
        }

        @Composable
        private fun ProfileScreen(
            user: User,
            onSaveData: (User) -> Unit,
            modifier: Modifier = Modifier
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFBA1B1B))
                        .padding(top = 80.dp, bottom = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${user.surName} ${user.name}",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Fields(
                    user = user,
                    onSaveData = onSaveData
                )
            }
        }

        @Composable
        private fun Fields(
            user: User,
            onSaveData: (User) -> Unit,
            modifier: Modifier = Modifier
        ) {
            var name by remember {
                mutableStateOf(user.name)
            }
            var surname by remember {
                mutableStateOf(user.surName)
            }
            var email by remember {
                mutableStateOf(user.email)
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.change_email),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
                InputField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = stringResource(R.string.current_email)
                )
                Text(
                    text = stringResource(R.string.change_name),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
                InputField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = stringResource(R.string.name)
                )
                Text(
                    text = stringResource(R.string.chnage_surname),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
                InputField(
                    value = surname,
                    onValueChange = {
                        surname = it
                    },
                    placeholder = stringResource(R.string.surname)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = { onSaveData(User.Default) },
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBA1B1B))
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        color = Color.White
                    )
                }
            }
        }

        @Composable
        private fun InputField(
            value: String,
            onValueChange: (String) -> Unit,
            placeholder: String,
            modifier: Modifier = Modifier
        ) {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = placeholder,
                        color = Color(0xFFBDBDBD),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF6F6F6),
                    focusedContainerColor = Color(0xFFF6F6F6)
                )
            )
        }
    }
}
