package com.devexperto.kda.myplayerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.devexperto.kda.myplayerkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val adapter = MediaAdapter { toast(it) }
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progress = binding.progress

        binding.recycler.adapter = adapter
        updateItems()
    }

    private fun updateItems(filter: Int = R.id.filter_all) {
        lifecycleScope.launch {
            progress.visibility = View.VISIBLE
            adapter.items = withContext(Dispatchers.IO) { getFilterItems(filter) }
            progress.visibility = View.GONE
        }
    }

    private fun getFilterItems(filter: Int) : List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when (filter) {
                R.id.filter_all -> media.filter { it.type == Type.PHOTO }
                R.id.filter_photos -> media.filter { it.type == Type.VIDEO }
                R.id.filter_videos -> MediaProvider.getItems()
                else -> emptyList()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        updateItems(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}