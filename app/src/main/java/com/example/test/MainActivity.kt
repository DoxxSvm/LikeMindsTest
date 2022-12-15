package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.adapters.WordAdapter
import com.example.test.utils.NetworkResponse
import com.example.test.viewmodels.BaseViewmodel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewmodel by viewModels<BaseViewmodel>()
    lateinit var wordAdapter: WordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpclickListeners()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewmodel.isLoading.observe(this){
            when(it){
                true ->{
                    pbar.visibility = View.VISIBLE
                }
                false ->{
                    pbar.visibility = View.GONE
                }
            }
        }

        viewmodel.meaningResponse.observe(this){
            when(it){
                is NetworkResponse.Success ->{
                    wordAdapter = WordAdapter(it.response!!.definitions)
                    setUpRv()
                }
                is NetworkResponse.Error ->{
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpRv() {
        word_rv.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setUpclickListeners() {
        button.setOnClickListener {
            val word = edittext.text.toString()
            if(word.isNotEmpty()){
                viewmodel.getMeaning(word)
            }
        }
    }
}