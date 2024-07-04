package com.deepfine.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.deepfine.presentation.R
import com.deepfine.presentation.utils.WindowAnimation

abstract class BaseDialogFragment<B : ViewBinding, VM : BaseViewModel> : DialogFragment() {
  protected lateinit var binding: B
  protected abstract val bindFactory: (LayoutInflater, ViewGroup?, Boolean) -> B
  protected abstract val viewModel: VM
  protected open val animation: WindowAnimation? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.DialogFragmentTheme)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    setAnimation()
    binding = bindFactory(inflater, container, false)
    return binding.root
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return object : Dialog(requireContext(), theme) {
      override fun onBackPressed() {
        onBackPressedDialog()
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    onBind()
    initView()
  }

  protected abstract fun onBind()
  protected abstract fun initView()

  protected open fun onBackPressedDialog() {
    super.dismiss()
  }

  private fun setAnimation() {
    animation?.let {
      dialog?.window?.attributes?.windowAnimations = it.style
    }
  }
}
