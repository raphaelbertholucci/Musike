package com.bertholucci.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bertholucci.common.base.BaseDialogFragment
import com.bertholucci.search.databinding.DialogSortBinding

class SearchPopularityDialog(val onSelectOption: (Boolean?) -> Unit = {}) :
    BaseDialogFragment<DialogSortBinding>() {

    override fun getViewBinding() = DialogSortBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
    }

    private fun addListeners() {
        binding.tvCrescent.setOnClickListener {
            onSelectOption.invoke(true)
            dismiss()
        }

        binding.tvDescending.setOnClickListener {
            onSelectOption.invoke(false)
            dismiss()
        }

        binding.tvNone.setOnClickListener {
            onSelectOption.invoke(null)
            dismiss()
        }
    }
}