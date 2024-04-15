package com.arjungupta08.hotelmanager

import java.io.Serializable

data class PropertyData(
    val property : PropertyDataClass,
    val documentId : String
) : Serializable
