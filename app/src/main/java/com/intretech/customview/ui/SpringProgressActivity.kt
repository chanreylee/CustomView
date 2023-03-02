package com.intretech.customview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.intretech.customview.R
import com.intretech.customview.view.seekbar.BrightnessSeekBarBase
import com.intretech.customview.view.seekbar.ColorTemperatureSeekBarBase
import kotlinx.android.synthetic.main.activity_spring_proress.*

/**
 *
 * @PackageName:com.intretech.customview.ui
 * @author:  YQ16685 Chanrey Lee
 * @date 2022/4/18 - 11:06
 **/
class SpringProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_proress)

        light_seek_bar.progress = 0
        light_seek_bar.setOnProgressListener(object : BrightnessSeekBarBase.OnProgressListener {
            override fun onProgress(progress: Int, currentEvent: Int) {
                light_seek_bar.progress = progress
            }

            override fun enableClick(): Boolean {
                return true
            }
        })

        colorTemperatureSeekBar.setOnProgressListener(object : ColorTemperatureSeekBarBase.OnColorTemperatureListener {
            override fun onColorTemperature(colorTemperature: Int, currentEventAction: Int) {
                colorTemperatureSeekBar.setColorTemperature(colorTemperature)
                if (colorTemperature > 2700) {
                    colorTemperatureSeekBar.setBackgroundResource(R.drawable.light_color_temperature_seekbar_changed_bg)
                } else {
                    colorTemperatureSeekBar.setBackgroundResource(R.drawable.light_color_temperature_seekbar_gray_bg)
                }
            }

            override fun enableClick(): Boolean  = true
        })
    }
}
