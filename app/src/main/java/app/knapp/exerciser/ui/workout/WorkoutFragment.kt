package app.knapp.exerciser.ui.workout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import app.knapp.exerciser.R
import app.knapp.exerciser.databinding.WorkoutFragmentBinding
import com.google.android.exoplayer2.ControlDispatcher
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.*
import com.google.android.exoplayer2.SimpleExoPlayer
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkoutFragment : Fragment() {

    private lateinit var binding: WorkoutFragmentBinding
    private val viewModel: WorkoutViewModel by viewModel()
    private var player: SimpleExoPlayer? = null
    private val args: WorkoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<WorkoutFragmentBinding>(inflater, R.layout.workout_fragment, container, false)
        .also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
            it.vm = viewModel
        }
        .root

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume(): list ${args.workoutArgument.workoutList}")
        initView()
        initObservers()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initPlayer() {

        // todo make sure playlist is not rest due to lifecycle on background/rotation

        // todo implement custom controller to

        player = SimpleExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player
        player?.let { player ->
            Log.d("TAG", "initPlayer: list ${args.workoutArgument.workoutList}")
            val mediaItems = args.workoutArgument.workoutList.map { workoutViewData ->
                Log.d("TAG", "initPlayer: video URI is ${workoutViewData.videoUrl}, media ID will be ${workoutViewData.exerciseId}")
                MediaItem.Builder()
                    .setUri(workoutViewData.videoUrl)
                    .setMediaId(workoutViewData.exerciseId.toString())
                    .build()
            }
            player.addMediaItems(mediaItems)
            Log.d("TAG", "initPlayer: mediaItems: $mediaItems")
            player.playWhenReady = true
            player.seekTo(0,0)
            player.prepare()
            player.addListener(object : Player.Listener {

                override fun onPlaybackStateChanged(state: Int) {
                    Log.d("TAG", "onPlaybackStateChanged: $state")
                    super.onPlaybackStateChanged(state)
                    when (state) {
                        STATE_ENDED -> {
                            Log.d("TAG", "onPlaybackStateChanged: STATE ENDED")

                        }
                    }
                }

                override fun onPositionDiscontinuity(
                    oldPosition: Player.PositionInfo,
                    newPosition: Player.PositionInfo,
                    reason: Int
                ) {
                    Log.d("TAG", "onPositionDiscontinuity: reason $reason")
                    if (reason == DISCONTINUITY_REASON_SKIP) {
                        // skip is not called on next button
                        player.currentMediaItem?.mediaId?.let { mediaId ->
                            viewModel.markWorkoutAsSkipped(mediaId)
                        }
                    }

                }


                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)

                    Log.d("TAG", "onMediaItemTransition: mediaItem: ${mediaItem?.mediaId}, reason $reason ")
                    mediaItem?.let {
                        if (reason == MEDIA_ITEM_TRANSITION_REASON_SEEK ) {
                            player.currentWindowIndex

                        }
//                        if (reason == MEDIA_ITEM_TRANSITION_REASON_AUTO ) {
//                            //mediaitem is the previous one, this listener is called for the transition TO the item
//                            viewModel.markWorkoutAsCompleted(mediaItem.mediaId)
//
//                        }

                    }
                }

            })
        }
    }

    private fun initView() {
//        binding.playerView.findViewById<ImageButton>(com.google.android.exoplayer2.R.id.exo_next).setOnClickListener {
//            player?.currentMediaItem?.mediaId?.let { mediaId ->
//                Log.d("TAG", "initView: clicked next of $mediaId")
//                viewModel.markWorkoutAsSkipped(mediaId)
//            }
//
//        }
        binding.playerView.setShowPreviousButton(false)
        binding.playerView.setShowRewindButton(false)
        binding.playerView.setShowFastForwardButton(false)
    }

    private fun initObservers() {
        viewModel.workoutProgress.observe(viewLifecycleOwner) {
            Log.d("TAG", "initObservers: $it")
        }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }
}