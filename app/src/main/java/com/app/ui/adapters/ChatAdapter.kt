package com.app.ui.adapters


import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.R
import com.app.databinding.ItemChatMessageBinding
import com.app.models.sn.Message

class ChatAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(val binding: ItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val msg = messages[position]
        holder.binding.messageTextView.text = msg.text

        // Align message based on sender
        val layoutParams = holder.binding.messageTextView.layoutParams as LinearLayout.LayoutParams
        layoutParams.gravity = if (msg.isUser) Gravity.END else Gravity.START
        holder.binding.messageTextView.layoutParams = layoutParams

        // Set background
        holder.binding.messageTextView.setBackgroundResource(
            if (msg.isUser) R.drawable.bg_user_message else R.drawable.bg_bot_message
        )
        if (msg.isTyping) {
            holder.binding.messageTextView.text = "Typing..."
            holder.binding.messageTextView.setTypeface(null, Typeface.ITALIC)
            holder.binding.messageTextView.setTextColor(Color.GRAY)
        } else {
            holder.binding.messageTextView.text = msg.text
            holder.binding.messageTextView.setTypeface(null, Typeface.NORMAL)
            holder.binding.messageTextView.setTextColor(Color.BLACK)
        }



        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_in)
        holder.binding.messageTextView.startAnimation(animation)
    }

    override fun getItemCount(): Int = messages.size
}
