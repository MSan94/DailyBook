package com.prj.dailybook.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.databinding.ActivityBookBinding
import com.prj.dailybook.presenter.BookPresenter
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.view.dialog.BookDetailFragment

class BookActivity : AppCompatActivity(), BookContract.View, DetailInterface {

    override lateinit var presenter: BookContract.Presenter
    val binding by lazy { ActivityBookBinding.inflate(layoutInflater) }
    lateinit var adapter: BookAdapter

    var parentCommentPageNum : Int? = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

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

    override fun search(search: String) {
        presenter.getBookList(search, this, false)
        binding.bookRecyclerView.smoothScrollToPosition(0)
    }

    override fun recyclerInit() {
    }

    override fun getModel(model: Book) {
        val dialog = BookDetailFragment(model)
        dialog.show(supportFragmentManager, "detailDialog")
    }

    override fun goBuy(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun goStore(model: Book) {
        Toast.makeText(this,"찜",Toast.LENGTH_SHORT).show()
    }


}