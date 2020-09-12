package com.jizhe7550.randomuser.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jizhe7550.randomuser.databinding.ItemUserBinding
import com.jizhe7550.randomuser.model.User

class UserAdapter(private val onItemClicked: (User, Int) -> Unit) :
    PagedListAdapter<User, RecyclerView.ViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(
            binding,
            onItemClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as UserViewHolder).bind(it)
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.name.fullName() == newItem.name.fullName()

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.name.fullName() == newItem.name.fullName()
        }
    }

    class UserViewHolder(
        private val binding: ItemUserBinding,
        private val onItemClicked: (User, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.user = item
            binding.clItem.setOnClickListener {
                onItemClicked(item, adapterPosition)
            }
        }
    }
}