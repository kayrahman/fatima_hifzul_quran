package neel.com.fatimahifzulquran.util

import android.util.Log
import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }


    companion object {


        fun formattedDateFromString(value: String): Date? {
            // val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            val sdf = SimpleDateFormat("yyyy-MM")
            var convertedDate: Date? = null
            //  var formattedDate: String? = null
            try {
                convertedDate = sdf.parse(value)
                //   formattedDate = SimpleDateFormat("MMMMM dd,yyyy").format(convertedDate)

            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return convertedDate
        }


        fun formattedStringFromDate(date: Date?):String{
            var formattedDate: String? = null
            try{
                //formattedDate = SimpleDateFormat("MMMMM dd,yyyy").format(date)
                formattedDate = SimpleDateFormat("yyyy-MM").format(date)
            }catch (e:Exception){
                e.printStackTrace()
            }

            return formattedDate.toString()

        }


    }


}