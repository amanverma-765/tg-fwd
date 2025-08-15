package com.ark.tg.forwarder.presentation.features.forwardfrom.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ark.tg.forwarder.presentation.components.ChatBottomBar
import com.ark.tg.forwarder.presentation.components.ChatCard
import com.ark.tg.forwarder.presentation.components.ChatTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForwardFromScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var isSelected by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ChatTopAppBar(
                title = "Forward from chat",
                onBackClicked = onBackClicked,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            if (isSelected) {
                ChatBottomBar(
                    title = "Next",
                    onBtnClicked = onNextClicked
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(innerPadding)
        ) {
            items(10) {
                ChatCard(
                    onChatClicked = { isSelected = !isSelected }
                )
            }
        }
    }
}