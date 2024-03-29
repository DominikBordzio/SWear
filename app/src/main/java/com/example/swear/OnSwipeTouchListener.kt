package com.example.swear

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    companion object{
        private val SWIPE_DISTANCE_THRESHOLD = 10
        private val SWIPE_VELOCITY_THRESHOLD = 10
    }
    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    open fun onSwipeLeft(){}
    open fun onSwipeRight(){}

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean = true

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
