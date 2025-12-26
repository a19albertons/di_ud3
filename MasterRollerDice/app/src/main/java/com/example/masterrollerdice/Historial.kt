package com.example.masterrollerdice

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Historial : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_historial, container, false)

        // Comprobamos si el csv esta vacio o no
        val historialData = leerCSV(requireContext())
        val vacio = view.findViewById<LinearLayout>(R.id.no_hay_contenido_historial)
        val contenido = view.findViewById<LinearLayout>(R.id.hay_contenido_historial)
        if (historialData.isEmpty()) {
            vacio.visibility = View.VISIBLE
            contenido.visibility = View.INVISIBLE
        } else {
            vacio.visibility = View.INVISIBLE
            contenido.visibility = View.VISIBLE
        }

        return view
    }
    // Dentro de tu Fragmento/Activity:
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Obtén la lista de datos del CSV
        val historialData = leerCSV(requireContext()).toMutableList()

        // 2. Obtén la referencia al RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.historial_recycler_view)

        // 3. Configura el RecyclerView
        // Un LayoutManager es necesario para posicionar los elementos (en este caso, vertical)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 4. Crea y asigna el Adapter
        recyclerView.adapter = HistorialAdapter(historialData)
    }

}

data class HistorialEntry(
    val lanzamiento: String,
    val tipoDado: String,
    val total: String
)

class HistorialAdapter(private var historialList: MutableList<HistorialEntry>) :
    RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    // 1. ViewHolder: Guarda las referencias a las vistas de cada fila
    class HistorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val historialLanzamientol: TextView = itemView.findViewById(R.id.historial_lanzamiento)
        val historialTipoDado: TextView = itemView.findViewById(R.id.historial_tipo_dado)
        val historialTotal: TextView = itemView.findViewById(R.id.historial_total)
    }

    // 2. Crea nuevos ViewHolders (infla el layout de la fila)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.historial_objetos, parent, false)
        return HistorialViewHolder(view)
    }

    // 3. Reemplaza el contenido de una vista (une los datos a la vista)
    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val currentItem = historialList[position]
        holder.historialLanzamientol.text = currentItem.lanzamiento
        holder.historialTipoDado.text = currentItem.tipoDado
        holder.historialTotal.text = currentItem.total
    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = historialList.size

    fun limpiarDatos() {
        historialList.clear() // Borra la lista en memoria
        notifyDataSetChanged() // Avisa al RecyclerView para que se repinte
    }
}

fun leerCSV(context: Context): List<HistorialEntry> {
    val historialList = mutableListOf<HistorialEntry>()
    try {
        // Leer desde almacenamiento interno, no desde assets
        context.openFileInput("historial.csv").bufferedReader().useLines { lines ->
            lines.forEach { line ->
                val columnas = line.split(',')
                if (columnas.size >= 3) {
                    val entry = HistorialEntry(
                        lanzamiento = columnas[0],
                        tipoDado = columnas[1],
                        total = columnas[2]
                    )
                    historialList.add(entry)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return historialList
}


fun borrarHistorial(context: Context, navController: NavController) {

    context.deleteFile("historial.csv")
    navController.navigate(R.id.inicio)


}

