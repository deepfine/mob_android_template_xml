package com.deepfine.main

import android.view.LayoutInflater
import com.deepfine.main.databinding.ActivityMainBinding
import com.deepfine.presentation.base.BaseActivity
import com.deepfine.presentation.base.EmptyViewModel

class MainActivity : BaseActivity<ActivityMainBinding, EmptyViewModel>() {
  override val bindFactory: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate
  override val viewModel: EmptyViewModel = EmptyViewModel()

  override fun onBind() {
  }

  override fun initView() {
  }
}
