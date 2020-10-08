package com.intretech.customview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intretech.customview.R
import kotlinx.android.synthetic.main.activity_pict_txt.*

class PictTxtActivity : AppCompatActivity() {

    private var ischeck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pict_txt)

        setListener()
    }

    fun setListener() {

        btn_check.setOnClickListener {
            check_view.check()
        }

        btn_unCheck.setOnClickListener {
            check_view.unCheck()
        }
    }
}
