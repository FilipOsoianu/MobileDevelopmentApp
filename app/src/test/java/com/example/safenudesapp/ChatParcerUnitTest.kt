package com.example.safenudesapp

import com.example.safenudesapp.services.model.Chat

import android.util.Log
import org.junit.Test
import com.example.safenudesapp.services.model.FriendRequest
import com.example.safenudesapp.services.utils.ChatParser
import com.example.safenudesapp.services.utils.UtilsJsonParse
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

class ChatParcerUnitTest {
    private val chatParser = ChatParser()

    @Test
    fun friendRequestConvectorTest() {
        val chat1 = mockk<Chat>()
        val chat2 = mockk<Chat>()

        every { chat1.userOne } returns 2
        every { chat1.userTwo } returns 3
        every { chat1.chatId } returns 2

        every { chat2.userOne } returns 2
        every { chat2.userTwo } returns 4
        every { chat2.chatId } returns 4

        val list = listOf<Chat>(chat1, chat2)

        val chatId = chatParser.getChatId(list, 2, 3)
        assertThat(chatId, `is`(2))

        val chatId1 = chatParser.getChatId(list, 2, 4)
        assertThat(chatId1, `is`(4))

    }


}