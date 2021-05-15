package app.knapp.exerciser.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import app.knapp.exerciser.R
import app.knapp.exerciser.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<MainFragmentBinding>(inflater, R.layout.main_fragment, container, false)
        .also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
            it.vm = viewModel
        }
        .root

    override fun onResume() {
        super.onResume()
        viewModel.exerciseList.observe(viewLifecycleOwner) {
            Log.d("onResume", "onResume: $it")
        }
    }
}