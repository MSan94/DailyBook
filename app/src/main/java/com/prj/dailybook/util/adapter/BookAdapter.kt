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
            Log.d("TestAppAdapter", "호출5")
            binding.textViewDescription.text = bookModel.description
            binding.textViewTitle.text = bookModel.title
            Glide
                .with(binding.imageViewCover.context)
                .load(bookModel.coverSmallUrl)
                .into(binding.imageViewCover)
            binding.textViewAuth.text = "작가 : ${bookModel.author}"
            if(bookModel.rank == null){
                binding.textViewRank.text = "순위정보 없음"
            }else {
                binding.textViewRank.text = "순위 : ${bookModel.rank}"
            }
            binding.textViewCategory.text = bookModel.categoryName
            binding.constraintTop.setOnClickListener {
                detailInterface.getModel(bookModel)
            }
            binding.imageViewJjim.setOnClickListener { 
                Log.d("Test", "찜 클릭")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        Log.d("TestAppAdapter", "호출3")
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        Log.d("TestAppAdapter", "호출4")
        holder.bind(bookList[position])
    }


    override fun notifyAdapter() {
        notifyDataSetChanged()
        Log.d("TestAppAdapter", "호출2")
    }

    override fun addItems(items: ArrayList<Book>) {
//        this.bookList.addAll(items)
        bookList.clear()
        bookList = items
        notifyDataSetChanged()
        Log.d("TestAppAdapter", "호출1")
    }

    override fun clearItem() {
        bookList.clear()
        Log.d("TestAppAdapter", "호출0")
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