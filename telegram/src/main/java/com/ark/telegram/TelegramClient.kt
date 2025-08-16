package com.ark.telegram

import android.content.Context
import co.touchlab.kermit.Logger
import com.ark.telegram.model.TelegramConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import java.io.File
import kotlin.coroutines.resume

class TelegramClient() {

    private val logger = Logger.withTag("TelegramClient")
    private var client: Client? = null

    suspend fun create(context: Context, config: TelegramConfig): Result<TdApi.Update> {
        return try {
            System.loadLibrary("tdjni")
            setupTdLibParameters(context, config)

            suspendCancellableCoroutine { continuation ->
                client = Client.create(
                    { obj ->
                        if (obj is TdApi.Update) {
                            if (continuation.isActive) {
                                continuation.resume(Result.success(obj))
                            }
                        } else {
                            // Not an update, ignore or handle differently
                        }
                    },
                    { error ->
                        logger.e(error) { "Error while updating client state" }
                        if (continuation.isActive) {
                            continuation.resume(Result.failure(Exception("Client update error: ${error.message}")))
                        }
                    },
                    { error ->
                        logger.e(error) { "Default error in telegram client" }
                        if (continuation.isActive) {
                            continuation.resume(Result.failure(Exception("Default client error: ${error.message}")))
                        }
                    }
                )
            }
        } catch (e: Exception) {
            logger.e(e) { "Failed to create TelegramClient" }
            Result.failure(Exception("Failed to create TelegramClient: ${e.message}", e))
        }
    }

    private suspend fun setupTdLibParameters(context: Context, config: TelegramConfig) {
        val dbDir = File(context.filesDir, "tdlib").apply { mkdirs() }
        val filesDir = File(context.filesDir, "tdlib_files").apply { mkdirs() }

        val parameters = TdApi.SetTdlibParameters().apply {
            databaseDirectory = dbDir.absolutePath
            filesDirectory = filesDir.absolutePath
            useTestDc = config.useTestDc
            useFileDatabase = config.useFileDatabase
            useChatInfoDatabase = config.useChatInfoDatabase
            useMessageDatabase = config.useMessageDatabase
            useSecretChats = config.useSecretChats
            apiId = config.apiId
            apiHash = config.apiHash
            systemLanguageCode = config.languageCode
            deviceModel = config.deviceModel
            systemVersion = config.systemVersion
            applicationVersion = config.appVersion
        }

        execute(parameters).getOrThrow()
    }

    suspend fun execute(function: TdApi.Function<*>): Result<TdApi.Object> {
        val currentClient = client ?: return Result.failure(
            IllegalStateException("Client not initialized")
        )
        return suspendCancellableCoroutine { continuation ->
            try {
                currentClient.send(function) { response ->
                    when (response) {
                        is TdApi.Error -> {
                            if (continuation.isActive) {
                                continuation.resume(Result.failure(TelegramException(response)))
                            }
                        }

                        else -> {
                            if (continuation.isActive) {
                                continuation.resume(Result.success(response))
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resume(Result.failure(e))
                }
            }
        }
    }

    suspend fun close() {
        execute(TdApi.Close())
        client = null
    }

}

class TelegramException(val error: TdApi.Error) : Exception(error.message)

