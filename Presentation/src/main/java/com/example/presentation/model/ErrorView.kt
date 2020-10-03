package com.example.presentation.model


data class ErrorView(
   val message: String,
    override var type: Int = ItemType.ERROR.type
): Type()