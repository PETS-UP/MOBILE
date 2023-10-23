package com.petsup.models

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("posts")
    var posts: List<Post>
)