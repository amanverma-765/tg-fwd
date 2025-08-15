package com.ark.tg.forwarder.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ChatCard(
    modifier: Modifier = Modifier,
    onChatClicked: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = "Aman Verma",
                style = MaterialTheme.typography.titleLarge
            )
        },
        supportingContent = {
            Text(
                text = "@amanverma765",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Light
            )
        },
        leadingContent = {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                modifier = Modifier
                    .height(70.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
            ) {
                // Profile image
            }
        },
        trailingContent = {
            RadioButton(
                onClick = onChatClicked,
                selected = true
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = CardDefaults.cardColors().containerColor
        ),
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onChatClicked() }
    )
}