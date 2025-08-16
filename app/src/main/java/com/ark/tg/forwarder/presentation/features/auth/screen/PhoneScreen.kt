package com.ark.tg.forwarder.presentation.features.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ark.tg.forwarder.presentation.components.BottomBarWithButton


@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    showSystemUi = true,
    showBackground = true
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneScreen(
    modifier: Modifier = Modifier,
    onContinueClick: (String) -> Unit = {}
) {
    var phoneNumber by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("91") }

    Scaffold(
        bottomBar = {
            BottomBarWithButton(
                modifier = Modifier.imePadding(),
                title = "Continue",
                enabled = phoneNumber.length == 10 && countryCode.isNotEmpty(),
            ) { }
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Enter your phone number",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "We will send you a verification code",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = countryCode,
                    onValueChange = {
                        if (it.length <= 4) countryCode = it.filter { char -> char.isDigit() }
                    },
                    label = { Text("Code") },
                    prefix = { Text("+") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(90.dp)
                )

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        if (it.length <= 10) phoneNumber = it.filter { char -> char.isDigit() }
                    },
                    modifier = Modifier.weight(1f),
                    label = { Text("Phone number") },
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )
            }
        }
    }
}