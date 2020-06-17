package com.dom_project.tabata_timer.ui.timer

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.ActivityTimerBinding
import com.dom_project.tabata_timer.ui.BindingActivity
import com.dom_project.tabata_timer.viewmodel.TimerViewModel
import kotlinx.android.synthetic.main.activity_timer.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class TimerActivity : BindingActivity<ActivityTimerBinding>() {

    val viewModel : TimerViewModel by viewModel()
    var remainSecNow = 0
    var totalTask = 1
    var currentTaskOrder = 0
    private val A_SECOND = 1000L
    private lateinit var timer: CircuitTimer
    private var isTimerOn = false
    private var initialized = false
    private val soundPool = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()).build()
    private var countdownId = 0
    private var beepId = 0
    private var runnable = Runnable {}

    override fun getLayoutResId(): Int = R.layout.activity_timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countdownId = soundPool.load(this, R.raw.countdown, 1)
        beepId = soundPool.load(this, R.raw.censored_beep, 1)

        timer = CircuitTimer(viewModel.circuitProgramSelected.value?.circuit!!.restTime * A_SECOND, A_SECOND)

        viewModel.circuitProgramSelected.observe(this, Observer {
            Log.d("Timer", "detect circuitProgram changed!! : circuit name = ${it.circuit.name} / workout list size = ${it.workouts.size}")
            if (initialized) {
                binding.circuit = it.circuit
                binding.workouts = it.workouts
                totalTask = it.circuit.repeatCount * it.workouts.size * 2
                binding.indexWorkout = 0
                binding.indexSet = 1
                if (!isTimerOn) {
                    Log.d("Timer", "Timer START!!! : workout index = ${binding.indexWorkout} / set index = ${binding.indexSet} / total order = $totalTask")
                    isTimerOn = true
                    timer.start()
                }
            } else {
                initialized = !initialized
            }
        })
        viewModel.loadWorkoutListData(viewModel.circuitProgramSelected.value?.circuit!!.id)

    }

    private fun taskDone() {
        if (++currentTaskOrder < totalTask) {

            runnable = Runnable {
                if (currentTaskOrder < binding.workouts?.size!!*2) {
                    binding.indexWorkout = currentTaskOrder
                } else {
                    binding.indexWorkout = currentTaskOrder%(binding.workouts?.size!!*2)
                }
                binding.indexSet = currentTaskOrder / (binding.workouts?.size!!*2) +1
                timer = CircuitTimer((if (currentTaskOrder%2 > 0) binding.circuit?.workoutTime!! else binding.circuit?.restTime!!) * A_SECOND, A_SECOND)
                if (!isTimerOn) {
                    isTimerOn = true
                    timer.start()
                }
            }
            Handler().postDelayed(runnable, 500)
        } else {
            digit_unit.sync()
            button_stop.text = getString(R.string.complete)
            button_stop.setTextColor(resources.getColor(R.color.colorPrimary, null))
            TimerCompleteDialog(this, viewModel).show()
        }
    }

    inner class CircuitTimer(millisInFuture: Long,
                       countDownInterval: Long
    ) : CountDownTimer(millisInFuture, countDownInterval) {

        init {
            remainSecNow = (millisInFuture.toFloat() / A_SECOND).roundToInt()
            binding.maxTensDigit = remainSecNow/10
            binding.startUnitDigit = remainSecNow%10
        }

        override fun onFinish() {
            digit_tens.sync()
            digit_unit.sync()
            isTimerOn = false
            taskDone()
        }

        override fun onTick(millisUntilFinished: Long) {
            remainSecNow = (millisUntilFinished.toFloat() / A_SECOND.toFloat()).roundToInt()
            Log.d("Timer", "onTick - millisUntilFinished : $millisUntilFinished / remain sec : $remainSecNow")
            if (remainSecNow%10 == 0) {
                digit_tens.start()
            }
            digit_unit.start()
            if (remainSecNow == 6) {
                soundPool.play(countdownId, 1f, 1f, 0, 0, 1f)
                digit_tens.textColor = getColor(R.color.colorAccent)
                digit_unit.textColor = getColor(R.color.colorAccent)
                digit_tens.sync()
            } else if (remainSecNow == 1) {
                soundPool.play(beepId, 1f, 1f, 0, 0, 1f)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (isTimerOn) {
            timer.cancel()
            if (remainSecNow == 0) soundPool.stop(beepId)
            else if (remainSecNow <= 5) soundPool.stop(countdownId)
        }
        Handler().removeCallbacks(runnable)
    }
}
