package com.crocodic.core.base.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.crocodic.core.helper.util.ClickPrevention

/**
 * Created by @yzzzd on 4/19/18.
 */

/**
 * @constructor [@DrawableRes]
 * @constructor diffUtil, you can create a instance DiffUtil from [PaginationAdapter.DiffUtilCallback]
 */
open class SingleClickListAdapter<VB : ViewDataBinding, T : Any?>(
    layoutRes: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : CoreListAdapter<VB, T>(layoutRes, diffUtil) {

    override fun onBindViewHolder(holder: ItemViewHolder<VB, T>, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let { item ->
            holder.bind(item)
            onItemClick?.let { `fun` ->
                holder.itemView.setOnClickListener(object : ClickPrevention {
                    override fun onClick(v: View?) {
                        `fun`.invoke(holder.bindingAdapterPosition, item)
                        super.onClick(v)
                    }
                })
            }
        }
    }
}