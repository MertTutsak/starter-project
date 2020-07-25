package com.merttutsak.starter.ui.common.components.playerview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.SeekBar
import butterknife.BindView
import com.merttutsak.starter.ui.common.components.button.BaseButton


class PlayerView : LinearLayout {

    @BindView(R.id.seekBar)
    lateinit var seekBar: SeekBar
    @BindView(R.id.backPlayer)
    lateinit var backPlayer: BaseButton
    @BindView(R.id.actionPlayer)
    lateinit var actionPlayer: BaseButton
    @BindView(R.id.nextPlayer)
    lateinit var nextPlayer: BaseButton

    private var mUserIsSeeking = false
    private var mPlayerAdapter: PlayerAdapter? = null

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs)
    }


    private fun init(context: Context?, attrs: AttributeSet?) {
        val view = View.inflate(context, R.layout.component_player_view, this)
        ButterKnife.bind(this, view)
        actionPlayer.setOnClickListener {
            if (mPlayerAdapter!!.isPlaying) {
                actionPlayer.text = "Start"
                mPlayerAdapter!!.pause()
            } else {
                actionPlayer.text = "Pause"
                mPlayerAdapter!!.play()

            }
        }
        initSeekBar()
        initializePlaybackController()

    }
  //https://www.soundhelix.com/examples/mp3/SoundHelix-Song-11.mp3
    fun startPlayer(url: String) {
        mPlayerAdapter?.loadMediaUrl(url)
    }

    private fun initSeekBar() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                internal var userSelectedPosition = 0

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    mUserIsSeeking = true
                }

                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        userSelectedPosition = progress
                    }
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    mUserIsSeeking = false
                    mPlayerAdapter?.seekTo(userSelectedPosition)
                }
            })
    }

    private fun initializePlaybackController() {
        val mMediaPlayerHolder = MediaPlayerHolder(context)
        mMediaPlayerHolder.setPlaybackInfoListener(PlaybackListener())
        mPlayerAdapter = mMediaPlayerHolder
    }


    inner class PlaybackListener : PlaybackInfoListener() {

        override fun onDurationChanged(duration: Int) {
            seekBar.setMax(duration)
        }

        override fun onPositionChanged(position: Int) {
            if (!mUserIsSeeking) {
                seekBar.progress = position
            }
        }

        override fun onStateChanged(@State state: Int) {
            val stateToString = PlaybackInfoListener.convertStateToString(state)
            onLogUpdated(String.format("onStateChanged(%s)", stateToString))
        }

        override fun onPlaybackCompleted() {}


    }
}