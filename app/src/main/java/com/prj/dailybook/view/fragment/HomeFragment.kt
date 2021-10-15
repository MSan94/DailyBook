package com.prj.dailybook.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.prj.dailybook.contract.HomeContract
import com.prj.dailybook.databinding.FragmentHomeBinding
import com.prj.dailybook.presenter.HomePresenter
import com.prj.dailybook.util.adapter.ViewPagerAdapter
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.Music
import java.lang.Math.round

/**
 * @author 안명성
 */

class HomeFragment : Fragment() , HomeContract.View{

    override lateinit var presenter: HomeContract.Presenter
    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    val adapter = ViewPagerAdapter()
    lateinit var mGlideRequestManager : RequestManager


    /** 액티비티 관련 리스너 **/
    interface OnCloseListener {
        fun onCloseMenu()
        fun goActivity(type : String)
    }
    private lateinit var closeListener : OnCloseListener

    /** 뷰페이저 애니메이션 **/
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(activity != null && activity is OnCloseListener){
            closeListener = activity as OnCloseListener
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        textReSize()
        return binding.root
    }

    /** 초기화 **/
    @SuppressLint("ClickableViewAccessibility")
    override fun init() {

        mGlideRequestManager = Glide.with(this)
        binding.viewPagerBook.adapter = adapter
        binding.viewPagerBook.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerBook.setPageTransformer(ZoomOutPageTransformer())
        presenter = HomePresenter().apply {
            view = this@HomeFragment
            adapterView = adapter
            adapterModel = adapter
            book = BookListData
        }

        context?.let { presenter.getData(it, false) }
        presenter.getMusic()
        presenter.getForeBook()
        presenter.getHealthBook()
        context?.let { presenter.getBookRatio(it) }

        binding.homeLayout.setOnClickListener {
            closeListener.onCloseMenu()
        }
        binding.scrollViewContents.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event?.action == MotionEvent.ACTION_DOWN){
                    closeListener.onCloseMenu()
                }
                return false
            }
        })

        binding.imgBtnMenu1.setOnClickListener {
//            closeListener.goActivity("1")
            closeListener.onCloseMenu()
        }
        binding.imgBtnMenu2.setOnClickListener {
            closeListener.goActivity("2")
            closeListener.onCloseMenu()
        }
        binding.imgBtnMenu3.setOnClickListener {
            closeListener.goActivity("3")
            closeListener.onCloseMenu()
        }

    }


    /** 메인 음반 정보 SET **/
    override fun setMusic(musicList: List<Music>) {
        if(musicList.size > 1){
            for(i in 0..1){
                Log.d("TextTestTitle", musicList[i].title.toString())
                if(i == 0){
                    activity?.let {
                        Glide
                            .with(it)
                            .load(musicList[i].coverLargeUrl)
                            .into(binding.imageViewBottomLeft)
                    }
                    binding.textViewBottomLeft.text = musicList[i].title
                }else{
                    activity?.let {
                        Glide
                            .with(it)
                            .load(musicList[i].coverLargeUrl)
                            .into(binding.imageViewBottomRight)
                    }
                    binding.textViewBottomRight.text = musicList[i].title
                }
            }
        }
    }

    /** 메인 외국1위 도서 SET **/
    override fun setForeBook(foreBook: Book) {
        activity?.let {
            Glide
                .with(it)
                .load(foreBook.coverLargeUrl)
                .into(binding.imageViewMidRightCover)
        }
    }

    /** 메인 건강도서 SET **/
    override fun setHealthBook(healthBook: Book) {
        activity?.let {
            Glide
                .with(it)
                .load(healthBook.coverLargeUrl)
                .into(binding.imageViewMidLeftCover)
        }
    }

    /** 메인 글꼴 관련 재조정 **/
    override fun textReSize() {
        val content = binding.textViewTopTitle.text.toString()
        val sb : SpannableString = SpannableString(content)
        val word = "TOP5"
        val start = content.indexOf(word)
        val end = start + word.length
        sb.setSpan(
            ForegroundColorSpan(Color.parseColor("#C2185B")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        sb.setSpan(RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textViewTopTitle.text = sb
    }

    /** 프로그래스 설정 **/
    override fun reFreshRate(totalCount: Int, yCount: Int) {
        val handler = Handler(Looper.getMainLooper())
        val rate = ((yCount.toFloat() / totalCount.toFloat())*100)
        handler.postDelayed(Runnable {
            binding.textViewCnt.text = "$totalCount / $yCount"
            binding.textViewRate.text = "${round(rate)}%"

            binding.progressbar.max = 100
            binding.progressbar.progress = round(rate)
        },0)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object{
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }


}