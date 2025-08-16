package com.ark.telegram.model.mapper

import com.ark.telegram.model.AuthState
import org.drinkless.tdlib.TdApi

object AuthStateMapper {
    private fun TdApi.AuthorizationState.toAuthState(): AuthState = when (this) {
        is TdApi.AuthorizationStateWaitPhoneNumber -> AuthState.WaitPhoneNumber
        is TdApi.AuthorizationStateWaitCode -> AuthState.WaitCode
        is TdApi.AuthorizationStateWaitPassword -> AuthState.WaitPassword
        is TdApi.AuthorizationStateReady -> AuthState.Ready
        is TdApi.AuthorizationStateLoggingOut -> AuthState.LoggingOut
        is TdApi.AuthorizationStateClosed -> AuthState.Closed
        is TdApi.AuthorizationStateWaitOtherDeviceConfirmation -> AuthState.WaitOtherDeviceConfirmation
        else -> AuthState.Unknown(this)
    }
}