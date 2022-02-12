package com.example.eraldhaka.androidarchitecture.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eraldhaka.androidarchitecture.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object{
        const val NAME = "name"
    }

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailID.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}