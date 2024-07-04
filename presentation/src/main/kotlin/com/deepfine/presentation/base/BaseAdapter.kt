package com.deepfine.presentation.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepfine.presentation.extensions.setOnDebounceClickListener
import com.deepfine.presentation.utils.DiffCallback

abstract class BaseAdapter<T, H : RecyclerView.ViewHolder> : RecyclerView.Adapter<H>() {
  var onItemClickListener: OnItemClickListener<T>? = null
  protected var itemList: MutableList<T>? = null

  override fun getItemCount(): Int = itemList?.size ?: 0

  fun getList(): List<T>? = itemList

  open fun updateItems(items: List<T>, diffCallback: DiffCallback<*> = DiffCallback(itemList ?: listOf(), items)) {
    val diffResult = DiffUtil.calculateDiff(diffCallback)
    itemList = items.toMutableList()
    diffResult.dispatchUpdatesTo(this)
  }

  fun replaceItem(index: Int, item: T) {
    itemList?.apply {
      this[index] = item
    }

    notifyItemChanged(index)
  }

  fun addItem(item: T) {
    itemList?.let {
      it.add(item)
      notifyItemInserted(it.size - 1)
    }
  }

  override fun onBindViewHolder(holder: H, position: Int) {
    holder.itemView.setOnDebounceClickListener {
      runCatching {
        onItemClickListener?.onItemClick(
          itemList?.get(holder.adapterPosition),
        )
      }
    }
  }

  fun interface OnItemClickListener<T> {
    fun onItemClick(item: T?)
  }
}
