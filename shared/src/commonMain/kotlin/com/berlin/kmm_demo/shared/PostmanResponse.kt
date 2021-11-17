package com.berlin.kmm_demo.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class PostmanResponse(val args: Args)

@Serializable
data class Args(val name: String, val address: String)
