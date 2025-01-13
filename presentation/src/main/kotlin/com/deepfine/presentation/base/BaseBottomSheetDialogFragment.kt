package com.deepfine.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.deepfine.presentation.R
import com.deepfine.presentation.utils.WindowAnimation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B : ViewBinding, VM : BaseViewModel> : BottomSheetDialogFragment() {
  protected lateinit var binding: B
  protected abstract val bindFactory: (LayoutInflater, ViewGroup?, Boolean) -> B
  protected abstract val viewModel: VM
  protected open val animation: WindowAnimation? = WindowAnimation.POP_UP

  override fun onStart() {
    super.onStart()

    dialog?.let {
      val bottomSheet: View = dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet)
      BottomSheetBehavior.from(bottomSheet).apply {
        state = BottomSheetBehavior.STATE_EXPANDED
        isHideable = false
      }
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogFragment).apply {
    setCanceledOnTouchOutside(true)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = bindFactory(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setAnimation()

    onBind()
    initView()
  }

  protected abstract fun onBind()
  protected abstract fun initView()

  private fun setAnimation() {
    animation?.let {
      dialog?.window?.attributes?.windowAnimations = it.style
    }
  }
}
