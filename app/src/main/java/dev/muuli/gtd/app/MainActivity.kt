package dev.muuli.gtd.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.muuli.gtd.app.app.databinding.ActivityMainBinding
import dev.muuli.gtd.library.kotlin.FactorialCalculator
import dev.muuli.gtd.library.android.ToastUtil
import dev.muuli.gtd.library.compose.ComposeActivity
import dev.muuli.gtd.app.app.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCompute.setOnClickListener {
            val message = if (binding.editTextFactorial.text.isNotEmpty()) {
                val input = binding.editTextFactorial.text.toString().toLong()
                val result = try {
                    FactorialCalculator.computeFactorial(input).toString()
                } catch (ex: IllegalStateException) {
                    "Error: ${ex.message}"
                }

                binding.textResult.text = result
                binding.textResult.visibility = View.VISIBLE
                getString(R.string.notification_title, result)
            } else {
                getString(R.string.please_enter_a_number)
            }
            ToastUtil.showToast(this, message)
        }

        binding.buttonAppcompose.setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }
    }
}
