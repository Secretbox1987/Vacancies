package com.example.presentation.model

data class HeaderView(
    val created_at: String?,
    override var type: Int = ItemType.HEADER.type
): Type()