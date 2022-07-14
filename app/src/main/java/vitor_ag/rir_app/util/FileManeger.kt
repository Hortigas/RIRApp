package vitor_ag.rir_app.util

import android.content.Context
import android.util.Log
import vitor_ag.rir_app.data.Photo
import java.io.File

object FileManeger {

    fun savePhotosOnStorage(
        context: Context,
        uniqueId: Int,
        photos: List<Photo>
    ): List<Photo> {
        return photos.mapIndexed { i, item ->
            Photo(
                uri = moveCacheImageToFile(
                    context = context,
                    filename = "${uniqueId}_Foto${i + 1}",
                    oldPath = item.uri
                ),
                createdDate = item.createdDate,
                gps = item.gps,
                category = item.category
            )
        }
    }

    private fun moveCacheImageToFile(
        context: Context,
        filename: String,
        oldPath: String,
    ): String {
        val cacheImage = File(oldPath)
        val path = File("${context.filesDir}/photos")
        path.mkdirs()
        val fileImage = File(path, "$filename.jpg")

        if (cacheImage.renameTo(fileImage))
            Log.d("PHOTO", "sucesso")
        return fileImage.toString()
    }

    fun clearCache(context: Context) {
        val directory = File(context.cacheDir, "photos")
        directory.deleteRecursively()
    }
}