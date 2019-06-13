package com.example.swear

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.wearable.activity.WearableActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.View
import android.view.View.OnTouchListener




class MainActivity : WearableActivity(){

    companion object {

        private val SWIPE_DISTANCE_THRESHOLD = 10
        private val SWIPE_VELOCITY_THRESHOLD = 10
    }


    private val images = ArrayList<Int>()
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAmbientEnabled()


        images.add(R.drawable.cl4ptp)
        images.add(R.drawable.kaczka)
        images.add(R.drawable.fibonacci)
        position = images.size/2

        main_constraint.setBackgroundResource(images[position])
        val listener = OnSwipeTouchListener(this@MainActivity)
        swiper.setOnTouchListener(listener)
    }

    fun moveForward(){
        position ++
        if(position == images.size)
           position = 0
        main_constraint.setBackgroundResource(images[position])
    }

    fun moveBackward(){
        position --
        if(position < 0)
            position = images.size - 1
        main_constraint.setBackgroundResource(images[position])
    }


    inner class OnSwipeTouchListener(context: Context) : OnTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, GestureListener())
        }

        fun onSwipeLeft() {moveForward()}

        fun onSwipeRight() {moveBackward()}

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                val distanceX = e2.x - e1.x
                val distanceY = e2.y - e1.y
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(
                        velocityX
                    ) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (distanceX > 0)
                        onSwipeRight()
                    else
                        onSwipeLeft()
                    return true
                }
                return false
            }

        }
    }
}
