package ru.kggm.astontask1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object MessageGenerator {
    private const val DEFAULT_DELAY = 5000L
    private const val MIN_DELAY = 1000L
    private const val MAX_DELAY = 10000L
    private const val DELAY_MARGIN = 0.25

    private var delay = DEFAULT_DELAY

    fun setDelay(delay: Long) {
        this.delay = delay.coerceIn(MIN_DELAY, MAX_DELAY)
    }

    private fun getRandomDelay() = (delay + delay * Random.nextDouble(-DELAY_MARGIN, DELAY_MARGIN))
        .toLong()
    private fun getRandomText() = Random.nextInt(1_000_000).toString()
    private fun getRandomHeader() = Random.nextInt(100).toString()

    val messageFlow = flow {
        while (true) {
            val randomDelay = getRandomDelay()
            delay(randomDelay)
            getRandomMessage()
                .also {
                    println("Emitted message with $randomDelay ms delay")
                }
                .let {
                    emit(it)
                }
        }
    }

    private fun getRandomMessage() : Message {
        return when (Random.nextInt(0, 2)) {
            0 -> Message.JustText(text = getRandomText())
            else -> Message.WithHeader(
                header = getRandomHeader(),
                text = getRandomText()
            )
        }
    }
}