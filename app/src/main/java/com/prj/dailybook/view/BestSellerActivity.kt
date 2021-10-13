package com.prj.dailybook.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.R
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.databinding.ActivityBestsellerBinding
import com.prj.dailybook.presenter.BestSellerPresenter
import com.prj.dailybook.util.listener.BookInterface
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.view.dialog.BookDetailFragment

/**
 * @author 안명성
 */
class BestSellerActivity() : AppCompatActivity(), BestSellerContract.View, BookInterface {

    private val binding by lazy { ActivityBestsellerBinding.inflate(layoutInflater) }
    override lateinit var presenter: BestSellerContract.Presenter

    val adapter = BookAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    /** 초기화 **/
    override fun init() {
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
        binding.bookRecyclerView.scrollToPosition(0)

        presenter = BestSellerPresenter().apply {
            view = this@BestSellerActivity
            adapterView = adapter
            adapterModel = adapter
            book = BookListData
        }

        presenter.getBestSeller(this,false)
    }

    /** 찜목록 추가 결과 **/
    override fun setBucketBook(type : String) {
        val handler = Handler(Looper.getMainLooper())
        when(type){
            "1" -> {
                handler.postDelayed(Runnable {
                    Toast.makeText(this, "목록에 저장하였습니다.", Toast.LENGTH_SHORT).show()
                },0)
            }
            "2" -> {
                handler.postDelayed(Runnable {
                    Toast.makeText(this, "이미 저장 하신 상품입니다.", Toast.LENGTH_SHORT).show()
                },0)
            }
        }
    }
    
    /** 상세 다이얼로그 호출 **/
    override fun getModel(model: Book) {
        val dialog = BookDetailFragment(model)
        dialog.show(supportFragmentManager, "detailDialog")
    }

    /** 구매링크 이동 **/
    override fun goBuy(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    /** 찜목록 추가 이벤트 호출 **/
    override fun goStore(model: Book) {
        presenter.saveBook(this,model)
    }

    companion object{
        private const val TAG = "RetrofitResult"
    }
}