package com.prj.dailybook.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.databinding.ActivityBestsellerBinding
import com.prj.dailybook.presenter.BestSellerPresenter
import com.prj.dailybook.presenter.BookPresenter
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.retrofit.RetrofitObject
import com.prj.dailybook.view.dialog.BookDetailFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestSellerActivity() : AppCompatActivity(), BestSellerContract.View, DetailInterface {

    private val binding by lazy { ActivityBestsellerBinding.inflate(layoutInflater) }
    override lateinit var presenter: BestSellerContract.Presenter

    val adapter = BookAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

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

    companion object{
        private const val TAG = "RetrofitResult"
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