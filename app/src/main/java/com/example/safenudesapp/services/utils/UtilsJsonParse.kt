package com.example.safenudesapp.services.utils

import com.example.safenudesapp.services.model.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class UtilsJsonParse {

    fun convertAccountToJsonObject(account: Account) :JsonObject{
        val bodyJsonString = Gson().toJson(account)
        val bodyJsonObject = JsonParser.parseString(bodyJsonString)
        return bodyJsonObject as JsonObject
    }

    fun convertSendMessageToJsonObject(account: SendMessage) :JsonObject{
        val bodyJsonString = Gson().toJson(account)
        val bodyJsonObject = JsonParser.parseString(bodyJsonString)
        return bodyJsonObject as JsonObject
    }

    fun convertRelationshipToJsonObject(relationship: Relationship) :JsonObject{
        val bodyJsonString = Gson().toJson(relationship)
        val bodyJsonObject = JsonParser.parseString(bodyJsonString)
        return bodyJsonObject as JsonObject
    }

    fun convertCreateChatToJsonObject(chat: CreateChat) :JsonObject{
        val bodyJsonString = Gson().toJson(chat)
        val bodyJsonObject = JsonParser.parseString(bodyJsonString)
        return bodyJsonObject as JsonObject
    }

    fun convertFriendRequestToJsonObject(friendRequest: FriendRequest) :JsonObject{
        val bodyJsonString = Gson().toJson(friendRequest)
        val bodyJsonObject = JsonParser.parseString(bodyJsonString)
        return bodyJsonObject as JsonObject
    }


}
