package com.evangelidis.catsfacts.view

import android.graphics.drawable.ColorDrawable
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
import com.evangelidis.tantintoast.TanTinToast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    lateinit var viewModel : ListViewModel
    private val catFactsListAdapter = CatFactsListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.toolbar_color)))

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
                catFactsListAdapter.updateCatFacts(it.data)
            }
        })

        viewModel.catfactLoadError.observe(this, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    TanTinToast.Warning(this).text("Check please your internet connection").show()
                }
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    list_error.visibility = View.GONE
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
            catFactsListAdapter.updateCatFacts(arrayListOf())
            observeViewModel()
            return true
        } else if (id == R.id.action_retreive_new_data) {
            catFactsListAdapter.updateCatFacts(arrayListOf())
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