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
        val EleccionDadosPorDefecto = 1
        val tipoPorDefecto = "D4"
        val Total = view.findViewById<TextView>(R.id.total_text)

        // valores en origen
        var num_dados = EleccionDadosPorDefecto
        var tipo_dados = tipoPorDefecto

        // El tipo de dado ahora se guarda como un String
        val lanzador= view.findViewById<EditText>(R.id.lanzar_dados)
        lanzador.keyListener = null // Hacer que el EditText no sea editable
        val un_dado = view.findViewById<EditText>(R.id.num_dados_elegidos1)
        un_dado.keyListener = null // Hacer que el EditText no sea editable
        val dos_dado = view.findViewById<EditText>(R.id.num_dados_elegidos2)
        dos_dado.keyListener = null // Hacer que el EditText no sea editable




        // imagenes de los distintos cubos
        val d4_img = view.findViewById<View>(R.id.d4)
        val d6_img = view.findViewById<View>(R.id.d6)
        val d8_img = view.findViewById<View>(R.id.d8)
        val d10_img = view.findViewById<View>(R.id.d10)
        val d12_img = view.findViewById<View>(R.id.d12)
        val d20_img = view.findViewById<View>(R.id.d20)
        val d100_img = view.findViewById<View>(R.id.d100)

        // Mostrar texto sobre cuadro
        val mostar1 = view.findViewById<TextView>(R.id.mostrar1)
        val mostar2 = view.findViewById<TextView>(R.id.mostrar2)
        val mostar3 = view.findViewById<TextView>(R.id.mostrar3)



        // Elección central y fotos
        val soloUnDado = view.findViewById<View>(R.id.solo_un_dado)
        val variosDados = view.findViewById<View>(R.id.dos_dado)

        val fotoCentral1= view.findViewById<ImageView>(R.id.foto_central1)
        val fotoCentral2= view.findViewById<ImageView>(R.id.foto_central2)
        val fotoCentral3= view.findViewById<ImageView>(R.id.foto_central3)

        // Listener de uno o dos dados
        un_dado.setOnClickListener {
            num_dados = 1
            soloUnDado.visibility = View.VISIBLE
            variosDados.visibility = View.INVISIBLE
            mostar1.text = "0"
            mostar2.text = "0"
            mostar3.text = "0"
            Total.text = "Total: 0"
        }

        dos_dado.setOnClickListener {
            num_dados = 2
            soloUnDado.visibility = View.INVISIBLE
            variosDados.visibility = View.VISIBLE
            mostar1.text = "0"
            mostar2.text = "0"
            mostar3.text = "0"
            Total.text = "Total: 0"
        }


        // Listener de operacion (sin sistema de guardado)

        lanzador.setOnClickListener {
            var total = 0
            mostar1.text = "0"
            mostar2.text = "0"
            mostar3.text = "0"

            when (tipo_dados) {
                "D4" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..4).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }

                "D6" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..6).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }

                "D8" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..8).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }
                "D10" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..10).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }
                "D12" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..12).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }
                "D20" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..20).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }
                "D100" -> {
                    val listaResultados = mutableListOf<Int>()
                    for (i in 1..num_dados) {
                        listaResultados.add((1..100).random())
                    }
                    if (num_dados == 1) {
                        mostar1.text = listaResultados[0].toString()
                    } else if (num_dados == 2) {
                        mostar2.text = listaResultados[0].toString()
                        mostar3.text = listaResultados[1].toString()
                    }
                    Total.text = "Total: ${listaResultados.sum()}"
                }
                else -> {
                    Total.text = "Error"
                }
            }
            val filename = "historial.csv"
            // Leer el historial actual para contar lanzamientos
            val historialList = leerCSV(requireContext())
            val numLanzamientos = historialList.size + 1 // Siguiente número de lanzamiento
            val dataAEscribir = "$numLanzamientos,${num_dados}${tipo_dados},$total\n"
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
            tipo_dados = "D4"
            fotoCentral1.setImageResource(R.drawable.d4_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d4_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d4_sin_fondo)
        }
        d6_img.setOnClickListener {
            tipo_dados = "D6"
            fotoCentral1.setImageResource(R.drawable.d6_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d6_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d6_sin_fondo)
        }
        d8_img.setOnClickListener {
            tipo_dados = "D8"
            fotoCentral1.setImageResource(R.drawable.d8_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d8_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d8_sin_fondo)
        }
        d10_img.setOnClickListener {
            tipo_dados = "D10"
            fotoCentral1.setImageResource(R.drawable.d10_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d10_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d10_sin_fondo)
        }
        d12_img.setOnClickListener {
            tipo_dados = "D12"
            fotoCentral1.setImageResource(R.drawable.d12_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d12_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d12_sin_fondo)
        }
        d20_img.setOnClickListener {
            tipo_dados = "D20"
            fotoCentral1.setImageResource(R.drawable.d20_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d20_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d20_sin_fondo)
        }
        d100_img.setOnClickListener {
            tipo_dados = "D100"
            fotoCentral1.setImageResource(R.drawable.d100_sin_fondo)
            fotoCentral2.setImageResource(R.drawable.d100_sin_fondo)
            fotoCentral3.setImageResource(R.drawable.d100_sin_fondo)
        }





        return view
    }

}