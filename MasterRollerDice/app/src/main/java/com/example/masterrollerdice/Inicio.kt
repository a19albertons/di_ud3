package com.example.masterrollerdice

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class Inicio : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_inicio, container, false)

        // Por defecto
        val dadosPorDefecto = 1
        val tipoPorDefecto = "D4"
        val Total = view.findViewById<TextView>(R.id.total_text)
        Total.text = Total.text.toString()+" 0"

        // Otras variables y valores por defecto más usados
        val num_dados= view.findViewById<EditText>(R.id.num_dados)
        val tipo_dados = view.findViewById<EditText>(R.id.tipo_dados)
        tipo_dados.setText(tipoPorDefecto)
        val lanzador= view.findViewById<EditText>(R.id.lanzar_dados)
        lanzador.keyListener = null // Hacer que el EditText no sea editable
        num_dados.setText(dadosPorDefecto.toString())

        // imagenes de los distintos cubos
        val d4_img = view.findViewById<View>(R.id.d4)
        val d6_img = view.findViewById<View>(R.id.d6)
        val d8_img = view.findViewById<View>(R.id.d8)
        val d10_img = view.findViewById<View>(R.id.d10)
        val d12_img = view.findViewById<View>(R.id.d12)
        val d20_img = view.findViewById<View>(R.id.d20)
        val d100_img = view.findViewById<View>(R.id.d100)

        // Elección central y fotos
        val soloUnDado = view.findViewById<View>(R.id.solo_un_dado)
        val variosDados = view.findViewById<View>(R.id.dos_dado)

        val fotoCentral1= view.findViewById<ImageView>(R.id.foto_central1)
        val fotoCentral2= view.findViewById<ImageView>(R.id.foto_central2)
        val fotoCentral3= view.findViewById<ImageView>(R.id.foto_central3)




        // Listener de operacion (sin sistema de guardado)

        // Cortesia de la IA para cuando modificas un texto
        num_dados.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                try {
                    val value = s?.toString()?.toInt() ?: 0
                    when {
                        value == 0 -> {
                            soloUnDado.visibility = View.INVISIBLE
                            variosDados.visibility = View.INVISIBLE
                        }

                        value == 1 -> {
                            soloUnDado.visibility = View.VISIBLE
                            variosDados.visibility = View.INVISIBLE
                        }

                        else -> {
                            soloUnDado.visibility = View.INVISIBLE
                            variosDados.visibility = View.VISIBLE
                        }
                    }
                } catch (e: NumberFormatException) {
                    soloUnDado.visibility = View.INVISIBLE
                    variosDados.visibility = View.INVISIBLE
                }
            }
        })
        lanzador.setOnClickListener {
            try {
                num_dados.text.toString().toInt()
            }
            catch (e: NumberFormatException) {
                num_dados.setText(dadosPorDefecto.toString())
            }
            var total = 0
            when (tipo_dados.text.toString()) {
                "D4" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..4).random()
                    }
                    Total.text = "Total: $total"
                }

                "D6" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..6).random()
                    }
                    Total.text = "Total: $total"
                }

                "D8" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..8).random()
                    }
                    Total.text = "Total: $total"
                }
                "D10" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..10).random()
                    }
                    Total.text = "Total: $total"
                }
                "D12" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..12).random()
                    }
                    Total.text = "Total: $total"
                }
                "D20" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..20).random()
                    }
                    Total.text = "Total: $total"
                }
                "D100" -> {
                    for (i in 1..num_dados.text.toString().toInt()) {
                        total += (1..100).random()
                    }
                    Total.text = "Total: $total"
                }
                else -> {
                    Total.text = "Error"
                }
            }
            val filename = "historial.csv"
            val dataAEscribir = "num_lineas,${tipo_dados.text},$total\n"
            try {
                // Usa openFileOutput para escribir en el almacenamiento interno de la app.
                // MODE_APPEND es crucial: añade al final en lugar de sobrescribir.
                val outputStream: FileOutputStream = requireContext().openFileOutput(filename, Context.MODE_APPEND)

                // Escribe la cadena al archivo.
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(dataAEscribir)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                // Manejar el error de escritura (ej. almacenamiento lleno)
            }
        }

        // Listener de elegir dado
        d4_img.setOnClickListener {
            tipo_dados.setText("D4")
            fotoCentral1.setImageResource(R.drawable.d4_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d4_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d4_sin_fondo)
        }
        d6_img.setOnClickListener {
            tipo_dados.setText("D6")
            fotoCentral1.setImageResource(R.drawable.d6_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d6_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d6_sin_fondo)
        }
        d8_img.setOnClickListener {
            tipo_dados.setText("D8")
            fotoCentral1.setImageResource(R.drawable.d8_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d8_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d8_sin_fondo)
        }
        d10_img.setOnClickListener {
            tipo_dados.setText("D10")
            fotoCentral1.setImageResource(R.drawable.d10_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d10_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d10_sin_fondo)
        }
        d12_img.setOnClickListener {
            tipo_dados.setText("D12")
            fotoCentral1.setImageResource(R.drawable.d12_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d12_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d12_sin_fondo)
        }
        d20_img.setOnClickListener {
            tipo_dados.setText("D20")
            fotoCentral1.setImageResource(R.drawable.d20_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d20_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d20_sin_fondo)
        }
        d100_img.setOnClickListener {
            tipo_dados.setText("D100")
            fotoCentral1.setImageResource(R.drawable.d100_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d100_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d100_sin_fondo)
        }





        return view
    }

}