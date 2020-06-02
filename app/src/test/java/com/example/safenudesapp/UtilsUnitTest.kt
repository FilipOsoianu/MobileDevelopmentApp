package com.example.safenudesapp

import android.util.Log
import org.junit.Test
import com.example.safenudesapp.services.model.FriendRequest
import com.example.safenudesapp.services.utils.UtilsJsonParse
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

class UtilsUnitTest {
    private val utilsJsonParse = UtilsJsonParse()

    @Test
    fun friendRequestConvectorTest() {
        val updateRelationship = mockk<FriendRequest>()
        every { updateRelationship.friendId } returns 3

        val jsonObject = utilsJsonParse.convertFriendRequestToJsonObject(updateRelationship)
        assertThat(jsonObject.toString(), `is`(("{\"friendId\":3}")))
    }


}