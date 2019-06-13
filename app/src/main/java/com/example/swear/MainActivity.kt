package com.example.swear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity(){

    private val images = ArrayList<Int>()
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAmbientEnabled()
        initialiseImages()

        val listener = object: OnSwipeTouchListener(this@MainActivity){
            override fun onSwipeLeft() = moveForward()
            override fun onSwipeRight() = moveBackward()
        }
        swiper.setOnTouchListener(listener)
    }

    fun initialiseImages(){
        images.add(R.drawable.cl4ptp)
        images.add(R.drawable.kaczka)
        images.add(R.drawable.fibonacci)
        position = images.size/2
        main_constraint.setBackgroundResource(images[position])
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

}



