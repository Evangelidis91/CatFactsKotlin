package com.evangelidis.catsfacts.model

data class CatFactsResponse(
    val current_page: Int,
    val `data`: MutableList<CatFact>,
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

data class CatFact(
    val fact: String,
    val length: Int
)