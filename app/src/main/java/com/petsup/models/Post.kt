package com.petsup.models

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("content")
    var content: String
)