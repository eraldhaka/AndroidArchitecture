package com.example.eraldhaka.androidarchitecture.ui.repositories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eraldhaka.androidarchitecture.databinding.FragmentRepositoriesBinding
import com.example.eraldhaka.androidarchitecture.ui.details.DetailFragmentArgs

class RepositoriesFragment : Fragment() {

    private var _binding : FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RepositoriesViewModel by lazy {
        ViewModelProvider(this, RepositoriesViewModelFactory(requireActivity().application))[RepositoriesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepositoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.data = viewModel

        binding.lifecycleOwner = this

        val adapter = RepositoriesAdapter(RepositoryClickListener {
           val action = RepositoriesFragmentDirections.actionRepositoriesFragmentToDetailFragment(
               name = it.name
           )
            findNavController().navigate(action)
        })

        binding.recyclerView.adapter = adapter

        viewModel.myData.observe(viewLifecycleOwner) { repos ->
            repos?.apply {
                Log.d(tag, "onCreate:myData size " + repos.size)
                adapter.submitList(repos)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}