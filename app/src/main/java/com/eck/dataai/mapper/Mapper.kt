package com.eck.dataai.mapper

interface Mapper<From, To> {
    fun map(value: From): To
}