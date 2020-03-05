package com.evangelidis.catsfacts.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangelidis.catsfacts.R
import com.evangelidis.catsfacts.viewmodel.ListViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    lateinit var viewModel : ListViewModel
    private val catFactsListAdapter = CatfactsListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        my_list_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catFactsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.catfacts.observe(this, Observer { catfacts ->
            catfacts?.let {
                my_list_view.visibility = View.VISIBLE
                it.data.shuffle()
                catFactsListAdapter.updateCatfacts(it.data)
            }
        })

        viewModel.catfactLoadError.observe(this, Observer { isError ->
            isError?.let {
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it){
                    my_list_view.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_surflle_data) {
            catFactsListAdapter.updateCatfacts(arrayListOf())
            observeViewModel()
            return true
        } else if (id == R.id.action_retreive_new_data) {
            catFactsListAdapter.updateCatfacts(arrayListOf())
            viewModel.refresh()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}