package com.ark.tg.forwarder.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed interface Destinations {

    @Serializable
    data object Home : Destinations

    @Serializable
    data object ForwardTo : Destinations

    @Serializable
    data object ForwardFrom : Destinations
}