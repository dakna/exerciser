package app.knapp.exerciser.ui.workout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutArgument(
    val workoutList: List<WorkoutViewData>
) : Parcelable