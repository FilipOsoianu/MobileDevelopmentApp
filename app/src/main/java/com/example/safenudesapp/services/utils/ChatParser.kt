package com.example.safenudesapp.services.utils

import com.example.safenudesapp.services.model.Chat

class ChatParser {
    fun getChatId(chatList: List<Chat>, userId: Int, friendId: Int): Int {
        for (chat in chatList) {
            if ((chat.userOne == userId && chat.userTwo == friendId) ||
                (chat.userTwo == userId && chat.userOne == friendId)
            ) {
                return chat.chatId
            }
        }
        return 0
    }
}