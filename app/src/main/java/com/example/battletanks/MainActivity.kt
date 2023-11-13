package com.example.battletanks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.widget.FrameLayout
import com.example.battletanks.Direction.*
import com.example.battletanks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_DPAD_UP -> move(UP)
            KEYCODE_DPAD_DOWN -> move(DOWN)
            KEYCODE_DPAD_LEFT -> move(LEFT)
            KEYCODE_DPAD_RIGHT -> move(RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun move(direction: Direction) {
        val step = 50
        when(direction) {
            UP -> {
                binding.myTank.rotation = 0f
                if (((binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin) > 0)
                        (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin -= step
            }
            DOWN -> {
                binding.myTank.rotation = 180f
                if (((binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin) < binding.container.height - binding.myTank.height - step)
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += step
            }
            LEFT -> {
                binding.myTank.rotation = 270f
                if (((binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin) > 0)
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin -= step
            }
            RIGHT -> {
                binding.myTank.rotation = 90f
                if (((binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin) < binding.container.width - binding.myTank.width - step)
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin += step
            }
        }
        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}