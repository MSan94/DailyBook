package com.prj.dailybook.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.contract.HomeContract
import com.prj.dailybook.databinding.FragmentHomeBinding
import com.prj.dailybook.presenter.BestSellerPresenter
import com.prj.dailybook.presenter.HomePresenter
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.adapter.ViewPagerAdapter
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.Music
import com.prj.dailybook.view.BestSellerActivity
import com.prj.dailybook.view.BookActivity
import com.prj.dailybook.view.dialog.CloseDialogFragment

class HomeFragment : Fragment() , HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    val adapter = ViewPagerAdapter()

    //뷰페이저 애니메이션
    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        textReSize()
        return binding.root
    }

    override fun init() {
        binding.viewPagerBook.adapter = adapter
        binding.viewPagerBook.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerBook.setPageTransformer(ZoomOutPageTransformer())
        presenter = HomePresenter().apply {
            view = this@HomeFragment
            adapterView = adapter
            adapterModel = adapter
            book = BookListData
        }

        context?.let { presenter.getData(it,false) }
        presenter.getMusic()
        presenter.getForeBook()
        presenter.getHealthBook()

    }

    override fun setMusic(musicList: List<Music>) {
        if(musicList.size > 1){
            for(i in 0..1){
                Log.d("TextTestTitle",musicList[i].title.toString())
                if(i == 0){
                    Glide
                        .with(binding.imageViewBottomLeft.context)
                        .load(musicList[i].coverLargeUrl)
                        .into(binding.imageViewBottomLeft)
                    binding.textViewBottomLeft.text = musicList[i].title
                }else{
                    Glide
                        .with(binding.imageViewBottomRight.context)
                        .load(musicList[i].coverLargeUrl)
                        .into(binding.imageViewBottomRight)
                    binding.textViewBottomRight.text = musicList[i].title
                }
            }
        }
    }

    override fun setForeBook(foreBook: Book) {
        Glide
            .with(binding.imageViewMidRightCover.context)
            .load(foreBook.coverLargeUrl)
            .into(binding.imageViewMidRightCover)
    }

    override fun setHealthBook(healthBook: Book) {
        Glide
            .with(binding.imageViewMidLeftCover.context)
            .load(healthBook.coverLargeUrl)
            .into(binding.imageViewMidLeftCover)
    }

    override fun textReSize() {
        val content = binding.textViewTopTitle.text.toString()
        val sb : SpannableString = SpannableString(content)
        val word = "TOP5"
        val start = content.indexOf(word)
        val end = start + word.length
        sb.setSpan(ForegroundColorSpan(Color.parseColor("#C2185B")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        sb.setSpan(RelativeSizeSpan(1.3f),start,end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textViewTopTitle.text = sb
    }


    companion object{
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }

}