package com.prj.dailybook.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prj.dailybook.contract.ScheduleAdapterContract
import com.prj.dailybook.databinding.ItemScheduleBinding
import com.prj.dailybook.util.listener.ScheduleInterface
import com.prj.dailybook.util.model.Schedule

class ScheduleAdapter() : ListAdapter<Schedule, ScheduleAdapter.ScheduleItemViewHolder>(diffUtil) , ScheduleAdapterContract.View, ScheduleAdapterContract.Model  {

    private var scheduleList : ArrayList<com.prj.dailybook.util.room.Schedule> = ArrayList()

    inner class ScheduleItemViewHolder(private val binding : ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model : com.prj.dailybook.util.room.Schedule){
            binding.textViewDate.text = model.date
            binding.textViewContents.text = model.contents
            binding.checkboxYn.isChecked = model.Yn != "N"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        return ScheduleItemViewHolder(ItemScheduleBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        holder.bind(scheduleList[position])
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun addItems(items: ArrayList<com.prj.dailybook.util.room.Schedule>) {
        scheduleList.clear()
        scheduleList = items
    }

    override fun clearItem() {
        scheduleList.clear()
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Schedule>(){
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.Id == newItem.Id
            }

        }
    }

}