package com.example.eraldhaka.androidarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.eraldhaka.androidarchitecture.R
import com.example.eraldhaka.androidarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.data = viewModel

        binding.lifecycleOwner = this

        val adapter = MainAdapter(RepositoryClickListener {
            Toast.makeText(this, "clicked: " + it.name, Toast.LENGTH_SHORT).show()
        })

        binding.recyclerView.adapter = adapter

        viewModel.myData.observe(this) { repos ->
            repos?.apply {
                Log.d(tag, "onCreate:myData size " + repos.size)
                adapter.submitList(repos)
            }
        }
    }
}