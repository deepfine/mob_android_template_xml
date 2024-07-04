package com.deepfine.presentation.extensions

import android.content.Context
import android.view.LayoutInflater

val Context.layoutInflater: LayoutInflater
  get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
