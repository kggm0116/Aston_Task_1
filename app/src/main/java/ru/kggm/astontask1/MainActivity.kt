package ru.kggm.astontask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.asLiveData

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MESSAGE_DELAY = 5000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMessageSystem()
    }

    private fun setUpMessageSystem() {
        MessageGenerator.setDelay(MESSAGE_DELAY)
        MessageGenerator.messageFlow.asLiveData().observe(this) { displayMessage(it) }
    }

    private fun displayMessage(message: Message) = message.run {
        val toastText = when(this) {
            is Message.JustText -> """
                        Message:
                        Text: $text
                    """.trimIndent()
            is Message.WithHeader -> """
                        Message:
                        Header: $header
                        Text: $text
                    """.trimIndent()
        }
        Toast
            .makeText(applicationContext, toastText, Toast.LENGTH_LONG)
            .apply {
                setGravity(
                    Gravity.CENTER,
                    0, 0)
                show()
            }
    }
}