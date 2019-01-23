package com.lala.guidview.guidsample

import android.support.v4.app.Fragment
import android.widget.EditText
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.lala.guidview.guid.GuideManger
import com.lala.guidview.guid.model.GuidePage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*


class FragmentGuide : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main,container,false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bt = view.findViewById<Button>(R.id.fgbt)
        bt.setOnClickListener {
            val contrller = GuideManger.with(this)
                .setLabel("normal")
                .alwaysShow(true)
                .addGuidePage(
                    GuidePage.newInstance()
                        .addHighLight(bt))
            contrller.show()
        }
    }
}