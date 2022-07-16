package vitor_ag.rir_app.util

import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode

object GpsConverter {
    private val sb = StringBuilder(20)

    fun latitudeRef(latitude: Double): String? {
        return if (latitude < 0.0) "S" else "N"
    }

    fun longitudeRef(longitude: Double): String? {
        return if (longitude < 0.0) "W" else "E"
    }

    fun convertToDMS(init: Double): String {
        var coord = Math.abs(init)
        val degree = coord.toInt()
        coord *= 60.0
        coord -= degree * 60.0
        val minute = coord.toInt()
        coord *= 60.0
        coord -= minute * 60.0
        val second = (coord * 1000.0).toInt()
        sb.setLength(0)
        sb.append(degree)
        sb.append("/1,")
        sb.append(minute)
        sb.append("/1,")
        sb.append(second)
        sb.append("/1000")
        return sb.toString()
    }

    fun convertToDouble(init: String): Double {
        var stringDms = init
        Log.d("PHOTO_AG", stringDms)
        var dms = stringDms.split(",", ignoreCase = false, limit = 3)

        val stringD = dms[0].split("/", ignoreCase = false, limit = 2)
        val d0 = stringD[0].toDouble()
        val d1 = stringD[1].toDouble()

        val dd = d0 / d1

        val stringM = dms[1].split("/", ignoreCase = false, limit = 2)
        val m0 = stringM[0].toDouble()
        val m1 = stringM[1].toDouble()

        val dm = m0 / m1

        val stringS = dms[2].split("/", ignoreCase = false, limit = 2)
        val s0 = stringS[0].toDouble()
        val s1 = stringS[1].toDouble()

        val ds = s0 / s1

        return BigDecimal((dd + dm / 60 + ds / 3600)).setScale(7, RoundingMode.HALF_EVEN).toDouble()
    }
}