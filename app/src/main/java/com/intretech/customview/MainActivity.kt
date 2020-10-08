package com.intretech.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intretech.customview.canvas.ui.*
import com.intretech.customview.ui.CanvasActivity
import com.intretech.customview.ui.GraphActivity
import com.intretech.customview.ui.PictTxtActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }

    private fun setListener() {

        btn_draw_graph.setOnClickListener {
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }

        btn_canvas_operate.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            startActivity(intent)
        }

        btn_draw_pict_txt.setOnClickListener {
            val intent = Intent(this, PictTxtActivity::class.java)
            startActivity(intent)
        }
    }
}
