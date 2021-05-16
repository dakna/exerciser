package app.knapp.exerciser.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.knapp.exerciser.R
import app.knapp.exerciser.databinding.MainFragmentBinding
import app.knapp.exerciser.ui.workout.WorkoutArgument
import app.knapp.exerciser.ui.workout.WorkoutViewData
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()
    private val adapter = ExerciseListAdapter(emptyList())

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
        initView()
        initObservers()
    }

    private fun initView() {
        binding.exerciseList.layoutManager = LinearLayoutManager(requireContext())
        binding.exerciseList.adapter = adapter
        binding.startWorkout.setOnClickListener { _ ->
            viewModel.exerciseList.value?.let { exerciseList ->
                WorkoutArgument(
                    workoutList = exerciseList.map {
                                WorkoutViewData(
                                    exerciseId = it.id,
                                    exerciseName = it.name,
                                    videoUrl = it.videoURL,
                                    isCompleted = false
                                )
                    })
                .also { argument ->
                    val action = MainFragmentDirections.actionMainToWorkout(argument)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.exerciseList.observe(viewLifecycleOwner) {
            Log.d("onResume", "onResume: $it")
            adapter.exerciseList = it
            adapter.notifyDataSetChanged()

        }
    }
}