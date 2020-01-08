package com.evangelidis.catsfacts.model

data class CatfactsResponse(
    val current_page: Int,
    val `data`: ArrayList<DataX>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: Any,
    val path: String,
    val per_page: String,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)

data class DataX(
    val fact: String,
    val length: Int
)