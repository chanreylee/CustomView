package com.intretech.customview.ui.bezier

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.intretech.customview.R
import kotlinx.android.synthetic.main.fragment_bezier3.*

class Bezier3Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bezier3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rg_bezier.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, i ->
            when(i) {
                R.id.rb1 -> {
                    bezier3.setMode(true)
                }
                R.id.rb2 -> {
                    bezier3.setMode(false)
                }
                else -> {
                    bezier3.setMode(true)
                }
            }
        })
    }
}
