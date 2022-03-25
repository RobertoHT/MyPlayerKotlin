package com.devexperto.kda.myplayerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.devexperto.kda.myplayerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MediaAdapter(MediaProvider.getItems()) { toast(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        adapter.items = MediaProvider.getItems().let { media ->
            when (item.itemId) {
                R.id.filter_all -> media.filter { it.type == Type.PHOTO }
                R.id.filter_photos -> media.filter { it.type == Type.VIDEO }
                R.id.filter_videos -> MediaProvider.getItems()
                else -> emptyList()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}