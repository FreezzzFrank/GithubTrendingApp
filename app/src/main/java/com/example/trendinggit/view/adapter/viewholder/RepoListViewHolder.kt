package com.example.trendinggit.view.adapter.viewholder

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.trendinggit.BR
import com.example.trendinggit.R
import com.example.trendinggit.model.Item
import com.example.trendinggit.viewmodel.RepoListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_repo_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class RepoListViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val repoListViewModel: RepoListViewModel
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(itemData: Item) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        Picasso.get().load(itemData.owner.avatar_url).into(itemView.item_avatar)
        itemView.onClick {
            val bundle = bundleOf("url" to itemData.html_url)
            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
        }
    }
}