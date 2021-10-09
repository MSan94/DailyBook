package com.prj.dailybook.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.databinding.ActivityBookBinding
import com.prj.dailybook.presenter.BookPresenter
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.listener.BookInterface
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.view.dialog.BookDetailFragment

/**
 * @author 안명성
 */

class BookActivity : AppCompatActivity(), BookContract.View, BookInterface {

    override lateinit var presenter: BookContract.Presenter
    val binding by lazy { ActivityBookBinding.inflate(layoutInflater) }
    lateinit var adapter: BookAdapter

    var parentCommentPageNum : Int? = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    /** 초기화 **/
    override fun init() {
        adapter = BookAdapter(this)
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
        presenter = BookPresenter().apply {
            view = this@BookActivity
            adapterView = adapter
            adapterModel = adapter
            book = BookListData
        }
        binding.btnSearch.setOnClickListener {
            if (binding.editTextSearch.text.isEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            search(binding.editTextSearch.text.toString())
        }

    }

    override fun showProgress() {
    }

    override fun hideProgress() {

    }

    /** 검색 처리 **/
    override fun search(search: String) {
        presenter.getBookList(search, this, false)
        binding.bookRecyclerView.smoothScrollToPosition(0)
    }

    override fun recyclerInit() {
    }
    
    
    /** 찜목록 이벤트 결과 **/
    override fun setBucketBook(type : String) {
        val handler = Handler(Looper.getMainLooper());
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

    /** 아이템 상세 프래그먼트 호출 **/
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


}