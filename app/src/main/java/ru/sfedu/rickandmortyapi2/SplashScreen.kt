package ru.sfedu.rickandmortyapi2

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sfedu.rickandmortyapi2.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            binding.splashProgressBar.max = 1000
            val value = 950
            ObjectAnimator.ofInt(binding.splashProgressBar, getString(R.string.progress), value).setDuration(2000).start()
            delay(2000)
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }

    }
}