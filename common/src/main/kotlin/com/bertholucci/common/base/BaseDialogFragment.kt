package com.bertholucci.common.base

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.bertholucci.common.R

private const val width_scale = 0.90F

abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment() {

    lateinit var binding: T

    private var show = false

    abstract fun getViewBinding(): T

    override fun onResume() {
        super.onResume()
        val dialog = dialog
        val metrics = DisplayMetrics()
        if (dialog != null) {
            dialog.window!!.setLayout(
                (metrics.widthPixels * width_scale).toInt(),
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
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
                    R.drawable.bg_dialog_rounded
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