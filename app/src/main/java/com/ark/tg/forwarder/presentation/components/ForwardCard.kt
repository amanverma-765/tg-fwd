package com.ark.tg.forwarder.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ark.tg.forwarder.R


@Composable
fun ForwardCard(modifier: Modifier = Modifier) {
    Card(shape = RoundedCornerShape(24.dp)) {
        Column(modifier = modifier.padding(12.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
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

                Icon(
                    painter = painterResource(id = R.drawable.bidirectional_icon),
                    contentDescription = "Bidirectional Icon",
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(70.dp)
                )

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
            }
        }
    }
}