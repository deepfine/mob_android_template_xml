package com.deepfine.presentation.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.repeatOnStarted(vararg blocks: suspend CoroutineScope.() -> Unit) {
  lifecycleScope.launch {
    blocks.forEach { block ->
      launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
      }
    }
  }
}
