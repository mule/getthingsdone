package dev.muuli.gtd.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.muuli.gtd.library.compose.ComposeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Directly launch the ComposeActivity
        val intent = Intent(this, ComposeActivity::class.java)
        startActivity(intent)
        finish() // Optional: finish MainActivity so it's not in the back stack
    }
}
