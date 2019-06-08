package com.example.contactosfinal.view.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.contactosfinal.*
import com.example.contactosfinal.entities.Contacto
import com.example.contactosfinal.model.Nuevo

class MainActivity : AppCompatActivity() {
    var lista:ListView? = null
    var grid:GridView? = null
    var viewswitcher:ViewSwitcher? = null

    companion object{
        var contactos:ArrayList<Contacto>? = null
        var adaptador: AdaptadorCustomisado? = null
        var adaptadorGrid: AdaptadorCustomisadoGrid? = null

        fun agregarContacto(contacto: Contacto){
            adaptador?.addItem(contacto)
        }

        fun obtenerContacto(index:Int): Contacto {
            return  adaptador?.getItem(index) as Contacto
        }
        fun eliminarContacto(index: Int){
            adaptador?.getItem(index)
        }
        fun actualizarContacto(index: Int,nuevoContacto: Contacto){
            adaptador?.updateItem(index, nuevoContacto)

        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)



        agregarContactosDefauld()




        lista = findViewById<ListView>(R.id.listaM)
        grid = findViewById<GridView>(R.id.grid)
        adaptador =
            AdaptadorCustomisado(
                this,
                contactos!!
            )
        adaptadorGrid =
            AdaptadorCustomisadoGrid(
                this,
                contactos!!
            )

        viewswitcher = findViewById(R.id.ViewSwitcher)


        lista?.adapter = adaptador
        grid?.adapter = adaptadorGrid

        lista?.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, Detalle::class.java)
            intent.putExtra("ID",position.toString())
            startActivity(intent)
        }

    }
    fun agregarContactosDefauld(){
        contactos = ArrayList()
        contactos?.add(
            Contacto(
                "Edinson",
                "Duran",
                "wiedii",
                18,
                60.5F,
                "Calle 16 # 6-36",
                "3152478542",
                "edinson@email.com",
                R.drawable.foto_01
            )
        )
        contactos?.add(
            Contacto(
                "thania",
                "Duran",
                "wiedii",
                18,
                60.5F,
                "Calle 16 # 6-36",
                "3152478542",
                "edinson@email.com",
                R.drawable.foto_02
            )
        )
        contactos?.add(
            Contacto(
                "emma",
                "Duran",
                "wiedii",
                18,
                60.5F,
                "Calle 16 # 6-36",
                "3152478542",
                "edinson@email.com",
                R.drawable.foto_03
            )
        )
        contactos?.add(
            Contacto(
                "sdsd",
                "Duran",
                "wiedii",
                18,
                60.5F,
                "Calle 16 # 6-36",
                "3152478542",
                "edinson@email.com",
                R.drawable.foto_04
            )
        )
        contactos?.add(
            Contacto(
                "aerfc",
                "Duran",
                "wiedii",
                18,
                60.5F,
                "Calle 16 # 6-36",
                "3152478542",
                "edinson@email.com",
                R.drawable.foto_05
            )
        )
        contactos?.add(
            Contacto(
                "ddasas",
                "Duran",
                "wiedii",
                18,
                60.5F,
                "Calle 16 # 6-36",
                "3152478542",
                "edinson@email.com",
                R.drawable.foto_06
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.searchView)
        val searchView = itemBusqueda?.actionView as android.support.v7.widget.SearchView

        val itemSwitch = menu?.findItem(R.id.switchView)
        itemSwitch.setActionView(R.layout.switch_item)

        val swithView = itemSwitch.actionView.findViewById<Switch>(R.id.sCambiaVista)


        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar contacto..."

        searchView.setOnQueryTextFocusChangeListener { _, _ -> }
        searchView.setOnQueryTextListener(object: android.support.v7.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //filtrar
                adaptador?.filtrar(newText!!)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
        })

        swithView?.setOnCheckedChangeListener { buttonView, isChecked ->
        viewswitcher?.showNext()

        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

            R.id.iNuevo -> {
                val intent = Intent(this, Nuevo::class.java)
                startActivity(intent)
                return true
            }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }

    override fun onResume() {
        super.onResume()
        adaptador?.notifyDataSetChanged()
    }

}
