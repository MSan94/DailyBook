package com.prj.dailybook.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.prj.dailybook.R
import com.prj.dailybook.databinding.DialogBookDetailBinding
import com.prj.dailybook.util.model.Book

class BookDetailFragment(model : Book) : DialogFragment() {

    private var _binding : DialogBookDetailBinding? = null
    private val binding get() = _binding!!
    var model : Book = model

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBookDetailBinding.inflate(inflater,container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.d("ModelTest",model.toString())
        Glide
            .with(binding.imageViewCover.context)
            .load(model.coverSmallUrl)
            .into(binding.imageViewCover)
        binding.textViewAuth.text = "작가 : ${model.author}"
        binding.textViewCategory.text = model.categoryName
        binding.textViewDescription.text = model.description
        binding.textViewTitle.text = model.title

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.popup_width)
        val height = resources.getDimensionPixelSize(R.dimen.popup_height)
        dialog?.window?.setLayout(width,height)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
