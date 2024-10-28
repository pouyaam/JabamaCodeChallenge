package com.jabama.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResourceDeserializer : JsonDeserializer<Resource<*>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Resource<*> {
        val jsonObject = json?.asJsonObject

        return try {
            // If your API returns the data directly without a "data" wrapper
            if (typeOfT is ParameterizedType) {
                val dataType = typeOfT.actualTypeArguments[0]
                val data = context?.deserialize<Any>(jsonObject, dataType)
                Resource.Success(data)
            } else {
                // Fallback case
                Resource.Error(Exception("Invalid type"))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
