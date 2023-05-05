package ru.kggm.astontask1

sealed class Message {
    data class JustText(
        val text: String
    ): Message()

    data class WithHeader(
        val header: String,
        val text: String
    ): Message()
}