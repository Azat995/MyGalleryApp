package com.example.mygalleryapp

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mygalleryapp.databinding.FragmentImageDetailsBinding

class ImageDetailsFragment : Fragment() {

    companion object {
        private const val IMAGE_POSITION = "Image position"

        fun getInstance(position: Int): ImageDetailsFragment = ImageDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(IMAGE_POSITION, position)
            }
        }
    }

    private var _binding: FragmentImageDetailsBinding? = null
    private val binding: FragmentImageDetailsBinding get() = _binding!!
    private var imageViewPagerAdapter: ImagesAdapter? = null
    private val imagesViewModel by activityViewModels<ImagesViewModel>()

    private val position: Int get() = arguments?.getInt(IMAGE_POSITION) ?: 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewPagerAdapter = ImagesAdapter(imagesViewModel.images.map { it.url })
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = imageViewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 3
            addCarouselEffect()
            setCurrentItem(position, false)
        }
    }

    private fun ViewPager2.addCarouselEffect(enableZoom: Boolean = true) {
        clipChildren = false    // No clipping the left and right items
        clipToPadding = false   // Show the viewpager in full width without clipping the padding
        offscreenPageLimit = 3  // Render the left and right items
        (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * Resources.getSystem().displayMetrics.density).toInt()))
        if (enableZoom) {
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = (0.80f + r * 0.20f)
            }
        }
        setPageTransformer(compositePageTransformer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        imageViewPagerAdapter = null
    }

}