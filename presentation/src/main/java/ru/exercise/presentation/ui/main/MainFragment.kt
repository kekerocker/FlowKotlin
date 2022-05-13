package ru.exercise.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.exercise.domain.common.Resource
import ru.exercise.presentation.databinding.FragmentMainBinding
import ru.exercise.presentation.ui.main.adapter.NewsAdapter

@AndroidEntryPoint
internal class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            viewModel.sendQuery(query)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val rvAdapter = NewsAdapter()
        binding.recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNews.collectLatest {
                    when (it) {
                        is Resource.Success -> {
                            hideProgressBar()
                            rvAdapter.setData(it.data)
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                        is Resource.Loading -> showProgressBar()
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressScreen.root.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressScreen.root.visibility = View.VISIBLE
    }

}