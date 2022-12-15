package com.example.test.models

data class MeaningResponse(
    val definitions: List<Definition>,
    val pronunciation: String,
    val word: String
)