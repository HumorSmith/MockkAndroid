package com.ifreedomer.mockk.note.util
sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
