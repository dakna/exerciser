package app.knapp.exerciser.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.knapp.exerciser.R
import app.knapp.exerciser.databinding.ItemExerciseBinding
import com.bumptech.glide.Glide

class ExerciseListAdapter(var exerciseList: List<ExerciseViewData>) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exerciseData : ExerciseViewData) {
            Glide.with(binding.coverImage.context).load(exerciseData.coverImageURL).into(binding.coverImage)
            binding.name.text = exerciseData.name
            // todo replace with a binding adapter accepting true/false
            val isFavoriteDrawableRes = when(exerciseData.isFavorite) {
                true -> R.drawable.ic_favorited
                false -> R.drawable.ic_unfavorited
            }
            binding.isFavorite.setImageResource(isFavoriteDrawableRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemExerciseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}