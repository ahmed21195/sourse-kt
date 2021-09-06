package com.app.telefric.makeen.data.model.response

import com.google.gson.annotations.SerializedName

data class Data(
    val current_page: Int,
    @SerializedName("data")
    val dataList: List<ProductItem>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String,
    val path: String,
    val per_page: String,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)