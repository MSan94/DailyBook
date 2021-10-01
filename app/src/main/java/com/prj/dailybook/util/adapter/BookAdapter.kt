package com.prj.dailybook.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.prj.dailybook.databinding.ItemBookBinding
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.view.dialog.BookDetailFragment
import com.prj.dailybook.view.dialog.CloseDialogFragment


class BookAdapter(val detailInterface : DetailInterface) : ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil) {

    inner class BookItemViewHolder(private val binding : ItemBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(bookModel : Book){
            binding.textViewDescription.text = bookModel.description
            binding.textViewTitle.text = bookModel.title
            Glide
                .with(binding.imageViewCover.context)
                .load(bookModel.coverSmallUrl)
                .into(binding.imageViewCover)
            binding.textViewAuth.text = "작가 : ${bookModel.author}"
            binding.textViewRank.text = "랭킹 : ${bookModel.rank}"
            binding.textViewCategory.text = bookModel.categoryName

            binding.constraintTop.setOnClickListener {
                detailInterface.getModel(bookModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
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