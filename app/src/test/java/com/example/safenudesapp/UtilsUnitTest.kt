package com.example.safenudesapp

import android.util.Log
import org.junit.Test
import com.example.safenudesapp.services.model.FriendRequest
import com.example.safenudesapp.services.utils.UtilsJsonParse
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

class UtilsUnitTest {
    @Test
    fun password_validator() {
        val utilsJsonParse = UtilsJsonParse()
        val updateRelationship = FriendRequest(3)
        val jsonObject = utilsJsonParse.convertFriendRequestToJsonObject(updateRelationship)
        assertThat(jsonObject.toString(), `is`(("{\"friendId\":3}")))
    }
    
}