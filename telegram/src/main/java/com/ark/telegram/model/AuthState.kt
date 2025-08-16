package com.ark.telegram.model

import org.drinkless.tdlib.TdApi

sealed class AuthState {
    object WaitPhoneNumber : AuthState()
    object WaitCode : AuthState()
    object WaitPassword : AuthState()
    object WaitOtherDeviceConfirmation : AuthState()
    object Ready : AuthState()
    object LoggingOut : AuthState()
    object Closed : AuthState()
    data class Unknown(val state: TdApi.AuthorizationState) : AuthState()
}