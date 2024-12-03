package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handle: Handler
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.cardigan)
        binding.seekBar.max = mediaPlayer.duration

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.btnPlay.setOnClickListener {
            mediaPlayer.start()
        }
        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.btnRestart.setOnClickListener {
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        }
        seekBarChanged()
    }

    fun seekBarChanged() {
        handle = Handler(Looper.getMainLooper())
        handle.postDelayed(runnable, 500)
    }

    val runnable = object : Runnable {
        override fun run() {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handle.postDelayed(this, 500)
        }
    }
}