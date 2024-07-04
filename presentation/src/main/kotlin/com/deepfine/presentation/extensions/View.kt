package com.deepfine.presentation.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay

@OptIn(ObsoleteCoroutinesApi::class)
fun View.setOnDebounceClickListener(delayMillis: Long = 300L, action: suspend (View) -> Unit) {
  val event = CoroutineScope(Dispatchers.Default).actor<View>(Dispatchers.Main) {
    for (event in channel) {
      action(event)
      delay(delayMillis)
    }
  }
  setOnClickListener {
    event.trySend(it)
  }
}

fun View.showKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  this.requestFocus()
  imm.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(windowToken, 0)
}
