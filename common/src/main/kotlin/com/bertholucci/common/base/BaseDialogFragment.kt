package com.bertholucci.common.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.bertholucci.common.R

abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment() {

    lateinit var binding: T

    private var show = false

    abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_MusikeTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        context?.run {
            dialog.window?.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.common_bg_dialog_rounded
                )
            )
        }
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (show || isAdded || dialog != null && dialog!!.isShowing) return

        try {
            show = true
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        try {
            show = false
            super.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}