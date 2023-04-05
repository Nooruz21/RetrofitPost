package com.example.retrofitpractic.retrofit

import android.icu.text.CaseMap.Title

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val price: Float,
    val discountPercentage:Float,
    val rating:Float,
    val stock:Float,
    val brand:String,
    val category:String,
    val thumbnail:String,
    val image: List<String>,
)
