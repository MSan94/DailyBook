package com.prj.dailybook.util.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prj.dailybook.R
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.AdapterPagerContract
import com.prj.dailybook.databinding.BookPagerItemBinding
import com.prj.dailybook.databinding.ItemBookBinding
import com.prj.dailybook.util.model.Book

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() , AdapterPagerContract.View, AdapterPagerContract.Model{

    private var model : ArrayList<Book> = ArrayList()

    inner class PagerViewHolder(private val binding : BookPagerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookModel : Book){
            Glide
                .with(binding.imageViewCover.context)
                .load(bookModel.coverLargeUrl)
                .into(binding.imageViewCover)
            binding.textViewTitle.text = bookModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(BookPagerItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(model[position])
    }

    override fun getItemCount(): Int = model.size

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addItems(items: ArrayList<Book>) {
        model.clear()
        for(i in 0 until 5) {
            model.add(items[i])
        }
        notifyDataSetChanged()
    }

    override fun clearItem() {
        model.clear()
    }


}