package com.example.mygalleryapp


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mygalleryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GalleryAdapter.Listener {

    companion object {
        const val IMAGE_LIST = "image_list"
        const val IMAGE_POSITION = "image_position"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private val imagesViewModel by viewModels<ImagesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        galleryAdapter = GalleryAdapter(this)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.galleryRecycleView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3).apply { reverseLayout = false }
            adapter = galleryAdapter.apply {
                submitList(imagesViewModel.images)
            }
        }
    }

    override fun onImageClick(position: Int, model: ImageModel) {
        val detailsFragment = ImageDetailsFragment.getInstance(position)

        supportFragmentManager.beginTransaction().replace(R.id.container_main, detailsFragment, detailsFragment::class.java.simpleName)
            .addToBackStack(detailsFragment.javaClass.simpleName).commit()
    }
}


