package com.deepfine.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel> : Fragment() {
  protected lateinit var binding: B
  protected abstract val bindFactory: (LayoutInflater, ViewGroup?, Boolean) -> B
  protected abstract val viewModel: VM

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = bindFactory(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    onBind()
    initView()
  }

  protected abstract fun onBind()
  protected abstract fun initView()
}