package vitor_ag.rir_app.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vitor_ag.rir_app.data.Photo

class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<Photo?>? {
        val type = object : TypeToken<List<Photo?>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromImageList(imageList: List<Photo?>?): String? {
        if (imageList == null) return null
        val type = object : TypeToken<List<Photo?>?>() {}.type
        return Gson().toJson(imageList, type)
    }
}