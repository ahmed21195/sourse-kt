package com.app.telefric.makeen.data.model.response

import com.google.gson.annotations.SerializedName

data class MakeenResponse(
    @SerializedName("data")
    val responseData: Data,
    val status: Boolean
)