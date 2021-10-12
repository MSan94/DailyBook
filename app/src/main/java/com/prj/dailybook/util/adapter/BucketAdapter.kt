package com.prj.dailybook.util.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prj.dailybook.contract.BucketAdapterContract
import com.prj.dailybook.databinding.ItemBookBinding
import com.prj.dailybook.databinding.ItemBucketBinding
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.Bucket
import kotlin.concurrent.thread

class BucketAdapter() : ListAdapter<Bucket, BucketAdapter.BucketItemViewHolder>(diffUtil) , BucketAdapterContract.View, BucketAdapterContract.Model  {

    private var bucketList : ArrayList<com.prj.dailybook.util.room.Bucket> = ArrayList()

    inner class BucketItemViewHolder(private val binding : ItemBucketBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(model : com.prj.dailybook.util.room.Bucket){
            Glide
                .with(binding.imageViewCover.context)
                .load(model.coverSmallUrl)
                .into(binding.imageViewCover)
            binding.checkBoxRead.isChecked = model.readYn == "1"
            binding.textViewTitle.text = model.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketItemViewHolder {
        return BucketItemViewHolder(ItemBucketBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: BucketItemViewHolder, position: Int) {
        holder.bind(bucketList[position])
    }

    override fun getItemCount(): Int {
        return bucketList.size
    }

    override fun addItems(items: ArrayList<com.prj.dailybook.util.room.Bucket>) {
        bucketList.clear()
        bucketList = items
    }

    override fun clearItem() {
        bucketList.clear()
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Bucket>(){
            override fun areItemsTheSame(oldItem: Bucket, newItem: Bucket): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Bucket, newItem: Bucket): Boolean {
                return oldItem.itemId == newItem.itemId
            }

        }
    }

}