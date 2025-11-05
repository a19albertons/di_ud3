package com.example.masterrollerdice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Inicio : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_inicio, container, false)

        val texto = view.findViewById<TextView>(R.id.total_text)
        texto.text = texto.text.toString()+" 0"
        return view
    }

}