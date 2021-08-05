package com.crocodic.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppNotification (
    @Expose
    @SerializedName("title")
    val title: String? = null,
    @Expose
    @SerializedName("content")
    val content: String? = null,
    @Expose
    @SerializedName("action")
    val action: String? = null,
    @Expose
    @SerializedName("read_at")
    val readAt: String? = null,
    @Expose
    @SerializedName("created_at")
    val createdAt: String? = null
)