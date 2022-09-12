package com.groke.storeapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.groke.storeapp.databinding.MainFragmentBinding
import com.groke.storeapp.viewmodel.ApplicationState
import com.groke.storeapp.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<ApplicationState> {
            renderData(it)
        })
        viewModel.emulateRequest()
    }

    fun renderData(data: ApplicationState) {
        when (data) {
            is ApplicationState.Error -> binding.textview.setText(data.msg)
            is ApplicationState.Loading -> binding.textview.setText(data.msg)
            is ApplicationState.MainScreen -> binding.textview.setText(data.msg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}