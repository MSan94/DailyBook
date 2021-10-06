package com.prj.dailybook.util.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.databinding.ItemBookBinding
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.view.dialog.BookDetailFragment
import com.prj.dailybook.view.dialog.CloseDialogFragment


class BookAdapter(val detailInterface : DetailInterface) : ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil), AdapterContract.View, AdapterContract.Model {

    private var bookList : ArrayList<Book> = ArrayList()

    inner class BookItemViewHolder(private val binding : ItemBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(bookModel : Book){
            binding.textViewTitle.text = bookModel.title
            if(bookModel.priceSales != null && !bookModel.discountRate.equals("0")){
                binding.textViewPrice.text = "${bookModel.priceSales}원"
                binding.textViewDiscountRate.text = "${bookModel.discountRate}% 할인"
            }else{
                binding.textViewPrice.text = "${bookModel.priceStandard}원"
                binding.textViewDiscountRate.text = ""
            }
            Glide
                .with(binding.imageViewCover.context)
                .load(bookModel.coverSmallUrl)
                .into(binding.imageViewCover)
            binding.textViewAuth.text = bookModel.author
            binding.textViewPublisher.text = bookModel.publisher
            if(bookModel.rank == null){
                binding.textViewTmp.text = "평점 : ${bookModel.customerReviewRank.toString()}"
            }else {
                binding.textViewTmp.text = "순위 : ${bookModel.rank}"
            }
            binding.constraintTop.setOnClickListener {
                detailInterface.getModel(bookModel)
            }
            binding.textViewBuy.setOnClickListener {
                detailInterface.goBuy(bookModel.additionalLink.toString())
            }
            binding.textViewJjim.setOnClickListener {
                detailInterface.goStore(bookModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(bookList[position])
    }


    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addItems(items: ArrayList<Book>) {
        bookList.clear()
        bookList = items
        notifyDataSetChanged()
    }

    override fun clearItem() {
        bookList.clear()
    }

    //같은 아이템인지 판단 , 결정
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Book>(){
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.itemId == newItem.itemId
            }

        }
    }


}