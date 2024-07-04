package com.deepfine.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @Description BaseActivity
 * @author yc.park (DEEP.FINE)
 * @since 2022-01-28
 * @version 1.0.0
 */
abstract class BaseActivity<B : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {
  protected lateinit var binding: B
  protected abstract val bindFactory: (LayoutInflater) -> B
  protected abstract val viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = bindFactory.invoke(layoutInflater)
    setContentView(binding.root)

    onBind()
    initView()
  }

  protected abstract fun onBind()
  protected abstract fun initView()
}