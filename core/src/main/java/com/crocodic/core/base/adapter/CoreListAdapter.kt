package com.crocodic.core.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocodic.core.BR
import com.crocodic.core.R

/**
 * Created by @yzzzd on 4/19/18.
 */

/**
 * @constructor [@DrawableRes]
 * @constructor diffUtil, you can create a instance DiffUtil from [PaginationAdapter.DiffUtilCallback]
 */
open class CoreListAdapter<VB : ViewDataBinding, T : Any?>(
    private var layoutRes: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, CoreListAdapter.ItemViewHolder<VB, T>>(diffUtil) {

    var onItemClick: ((position: Int, data: T?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<VB, T> {
        val finalViewType = when (viewType) {
            VIEW_TYPE_LOADING -> R.layout.cr_item_load_more
            else -> layoutRes
        }
        val binding = DataBindingUtil.inflate<VB>(
            LayoutInflater.from(parent.context),
            finalViewType,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<VB, T>, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            onItemClick?.let { `fun` ->
                holder.itemView.setOnClickListener {
                    `fun`.invoke(
                        position,
                        item
                    )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    class ItemViewHolder<VB : ViewDataBinding, T : Any?>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T?) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }
    }

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1
        fun RecyclerView.Adapter<RecyclerView.ViewHolder>.get() = this as CoreListAdapter<*, *>
        fun RecyclerView.ViewHolder.get() = this as ItemViewHolder<*, *>
    }
}
