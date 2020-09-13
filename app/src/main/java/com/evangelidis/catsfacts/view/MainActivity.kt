package com.evangelidis.catsfacts.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangelidis.catsfacts.R
import com.evangelidis.catsfacts.databinding.ActivityMainBinding
import com.evangelidis.catsfacts.extensions.gone
import com.evangelidis.catsfacts.extensions.show
import com.evangelidis.catsfacts.extensions.showIf
import com.evangelidis.catsfacts.viewmodel.ListViewModel
import com.evangelidis.tantintoast.TanTinToast

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var viewModel: ListViewModel
    private val catFactsListAdapter = CatFactsListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.toolbar_color)))

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.myListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catFactsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.catFacts.observe(this, { catFacts ->
            catFacts?.let {
                binding.myListView.show()
                it.data.shuffle()
                catFactsListAdapter.updateCatFacts(it.data)
            }
        })

        viewModel.catFactLoadError.observe(this, { isError ->
            isError?.let {
                binding.listError.showIf { isError }
                if (it) {
                    TanTinToast.Warning(this).text(getString(R.string.internet_error_text)).show()
                }
            }
        })

        viewModel.loading.observe(this, { isLoading ->
            isLoading?.let {
                binding.loadingView.showIf { isLoading }
                if (it) {
                    binding.listError.gone()
                    binding.myListView.gone()
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

}