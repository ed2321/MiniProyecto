package com.example.contactosfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.contactosfinal.entities.Contacto


class AdaptadorCustomisado(var contexto:Context, items:ArrayList<Contacto>):BaseAdapter() {

    var items:ArrayList<Contacto>? = null
    var copiaitems:ArrayList<Contacto>? = null


    init {
        this.items = ArrayList(items)
        this.copiaitems = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder? = null
        var vista = convertView

        if(vista == null){
            vista = LayoutInflater.from(contexto).inflate(R.layout.template_contacto, null)
            viewHolder =  ViewHolder(vista)
            vista.tag = viewHolder

        }else{
            viewHolder = vista.tag as? ViewHolder
        }

        //se castea de la clase contacto para poder obtener sus propiedades
        val item = getItem(position) as Contacto
        // se asignan valores a elemento graficos
        viewHolder?.nombre?.text = item.nombre + " " + item.apellidos
        viewHolder?.empresa?.text = item.empresa
        viewHolder?.foto?.setImageResource(item.foto)

        return vista!!
    }

    override fun getItem(position: Int): Any {
        return this.items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        //regresar numero de elementos de mi lista
        return this.items?.count()!!
    }

    fun addItem(item: Contacto){
        copiaitems?.add(item)
        items = ArrayList(copiaitems)
        notifyDataSetChanged()

    }

    fun removeItem(index: Int){
        copiaitems?.removeAt(index)
        items = ArrayList(copiaitems)
        notifyDataSetChanged()
    }

    fun updateItem(index: Int, newItem: Contacto){
        copiaitems?.set(index,newItem)
        items = ArrayList(copiaitems)
        notifyDataSetChanged()

    }
    fun filtrar(str:String){
        items?.clear()

        if(str.isEmpty()){
            items = ArrayList(copiaitems)
            notifyDataSetChanged()
            return
        }

        var busqueda = str
        busqueda = busqueda.toLowerCase()
        for(item in copiaitems!!){
            val nombre = item.nombre.toLowerCase()

            if (nombre.contains(busqueda)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()
    }



    private class ViewHolder(vista:View){
        var nombre:TextView?  = null
        var empresa:TextView? = null
        var foto:ImageView?   = null

        init {
            nombre = vista.findViewById(R.id.tvNombre)
            empresa = vista.findViewById(R.id.tvEmpresa)
            foto = vista.findViewById(R.id.ivfoto)
        }
    }
}