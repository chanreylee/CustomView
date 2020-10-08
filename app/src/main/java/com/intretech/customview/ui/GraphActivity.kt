package com.intretech.customview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intretech.customview.R
import com.intretech.customview.view.pie.DrawPieView
import com.intretech.customview.view.pie.PieData
import kotlinx.android.synthetic.main.activity_graph.*
import java.sql.RowId

class GraphActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

//
        val mData = ArrayList<PieData>().apply {
            add(PieData("A", 100f))
            add(PieData("B", 200f))
            add(PieData("C", 400f))
            add(PieData("D", 200f))
            add(PieData("E", 100f))
        }
        drawPieView.setData(mData)   //对象已存在，不需要在创建新的对象.
        drawPieView.setStartAngle(0)

    }
}
