package com.nuryazid.androidcore.ui.main

import com.crocodic.core.base.adapter.PaginationAdapter
import com.crocodic.core.base.adapter.SingleClickListAdapter
import com.nuryazid.androidcore.R
import com.nuryazid.androidcore.data.DataExample
import com.nuryazid.androidcore.databinding.ItemListSampleBinding

class MainAdapter : SingleClickListAdapter<ItemListSampleBinding, DataExample>(
    R.layout.item_list_sample,
    PaginationAdapter.DiffUtilCallback()
) {
    override fun onBindViewHolder(
        holder: ItemViewHolder<ItemListSampleBinding, DataExample>,
        position: Int
    ) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.binding.tvName.setOnClickListener {

            }
        }
    }
}