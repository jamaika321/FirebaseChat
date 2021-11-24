package ru.radiknasybullin.chatapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.radiknasybullin.chatapp.databinding.MessageItemBinding
import ru.radiknasybullin.chatapp.entities.UserModel

class ChatAdapter(): ListAdapter<UserModel, ChatAdapter.ChatViewHolder>(ItemComparator()){


    class ChatViewHolder(private val binding: MessageItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) = with(binding){
            tvName.text = user.name
        }

        companion object {
            fun create(parent: ViewGroup): ChatViewHolder {
                return ChatViewHolder(MessageItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}