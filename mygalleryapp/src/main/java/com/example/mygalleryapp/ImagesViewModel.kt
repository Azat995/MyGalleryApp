package com.example.mygalleryapp

import androidx.lifecycle.ViewModel
import java.util.Calendar

class ImagesViewModel : ViewModel() {

    val images = arrayListOf<ImageModel>()

    init {
        createImages()
    }

    private fun createImages() {
        repeat(16) {
            images.add(
                ImageModel(
                    Calendar.getInstance().timeInMillis.toInt() + it,
                    "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg"
                )
            )
            images.add(
                ImageModel(
                    Calendar.getInstance().timeInMillis.toInt() + it,
                    "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
                )
            )
            images.add(
                ImageModel(
                    Calendar.getInstance().timeInMillis.toInt() + it,
                    "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
                )
            )
            images.add(
                ImageModel(
                    Calendar.getInstance().timeInMillis.toInt() + it,
                    "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
                )
            )
        }
        images.shuffle()
    }
}