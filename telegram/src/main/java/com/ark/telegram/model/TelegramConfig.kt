package com.ark.telegram.model

    data class TelegramConfig(
    val apiId: Int,
    val apiHash: String,
    val useTestDc: Boolean = false,
    val deviceModel: String = "Android",
    val systemVersion: String = android.os.Build.VERSION.RELEASE,
    val appVersion: String = "1.0",
    val languageCode: String = "en",
    val useFileDatabase: Boolean = true,
    val useChatInfoDatabase: Boolean = true,
    val useMessageDatabase: Boolean = true,
    val useSecretChats: Boolean = true
)