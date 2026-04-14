package com.app.ui.sidenavigation.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.R
import com.app.bases.BaseFragment
import com.app.databinding.FragmentChatbotBinding
import com.app.models.chatRequest.ChatRequest
import com.app.models.chatRequest.MessageRequest
import com.app.models.sn.Message
import com.app.network.RetrofitInstance
import com.app.ui.adapters.ChatAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ChatBotFragment: BaseFragment<FragmentChatbotBinding, HomeViewModel>() {

    override val mViewModel: HomeViewModel by viewModels()

    private lateinit var adapter: ChatAdapter
    private val messages = mutableListOf<Message>()

    // Firebase
    private val db = FirebaseFirestore.getInstance()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentChatbotBinding.inflate(inflater, container, false)

    override fun getToolbarBinding() = null
    override fun getToolbarTitle() = R.string.menu_home
    override fun isMenuButton() = true

    override fun setupUI(savedInstanceState: Bundle?) {
        adapter = ChatAdapter(messages)
        mViewBinding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mViewBinding.chatRecyclerView.adapter = adapter

        loadChatHistory()
    }

    override fun attachListener() {
        mViewBinding.sendButton.setOnClickListener {
            val text = mViewBinding.userInputEditText.text.toString().trim()
            if (text.isNotEmpty()) {
                sendMessage(text)
                mViewBinding.userInputEditText.setText("")
            }
        }
    }


    private fun sendMessage(text: String) {

        // User message
        messages.add(Message(text, true))
        adapter.notifyItemInserted(messages.size - 1)

        scrollToBottom()

        // Typing message
        val typingMessage = Message("Typing...", false, true)
        messages.add(typingMessage)
        val typingIndex = messages.size - 1
        adapter.notifyItemInserted(typingIndex)

        lifecycleScope.launch {

            val response = getAIResponse(text)

            // Remove typing
            messages.removeAt(typingIndex)
            adapter.notifyItemRemoved(typingIndex)

            // Add AI response
            messages.add(Message(response, false))
            adapter.notifyItemInserted(messages.size - 1)

            scrollToBottom()

            // Save to Firebase
            saveChat(text, response)
        }
    }


    private suspend fun getAIResponse(userMessage: String): String {
        return try {
            val response = RetrofitInstance.api.getChatResponse(
                "Bearer sk-xxxxxxxxxxxxxxxx",
                ChatRequest(
                    messages = listOf(
                        MessageRequest("system", "You are a helpful university campus assistant."),
                        MessageRequest("user", userMessage)
                    )
                )
            )
            response.choices.first().message.content
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }


    private fun saveChat(user: String, bot: String) {
        val chat = hashMapOf(
            "userMessage" to user,
            "botResponse" to bot,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("chats").add(chat)
    }


    private fun loadChatHistory() {
        db.collection("chats")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { result ->
                messages.clear()
                for (doc in result) {
                    val user = doc.getString("userMessage") ?: ""
                    val bot = doc.getString("botResponse") ?: ""

                    messages.add(Message(user, true))
                    messages.add(Message(bot, false))
                }
                adapter.notifyDataSetChanged()
                scrollToBottom()
            }
    }

    private fun scrollToBottom() {
        mViewBinding.chatRecyclerView.scrollToPosition(messages.size - 1)
    }

    override fun observeViewModel() {}
}

