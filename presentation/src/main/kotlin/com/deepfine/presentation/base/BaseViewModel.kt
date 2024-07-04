package com.deepfine.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

interface BaseViewModel {
}

abstract class BaseViewModelImpl : ViewModel(), BaseViewModel {
  protected fun <T> FlowCollector<T>.emitOn(value: T) {
    viewModelScope.launch {
      this@emitOn.emit(value)
    }
  }

  protected fun <T> Flow<T>.asStateFlow(initialValue: T) = stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue)

  protected suspend inline fun <R, T> Flow<Result<T>>.collectResult(crossinline onSuccess: (T) -> R, crossinline onFailure: (Throwable) -> R) = collect { result ->
    result.fold(
      onSuccess,
      onFailure
    )
  }

  protected suspend inline fun <R, T> Flow<Result<T>>.collectResultIndexed(crossinline onSuccess: (Int, T) -> R, crossinline onFailure: (Throwable) -> R) = collectIndexed { index, result ->
    result.fold(
      { onSuccess(index, it) },
      onFailure
    )
  }
}

class EmptyViewModel : BaseViewModel {
}