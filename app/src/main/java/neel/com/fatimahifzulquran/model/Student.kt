package neel.com.fatimahifzulquran.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import neel.com.fatimahifzulquran.database.StudentEntity
import java.util.*


@Parcelize
data class Student(
        val roll_num: Long=0L,
        val name: String,
        val father_name: String,
        val age: Int = 0,
        val education_type : String,
        val computer_section: String,
        val isEnglishStudent:Boolean,
        val hobbies: String,
        val joining_date: Date?,
        val finishing_date : Date?,
        val address: String,
        val student_description:String,
        val image_url:String
):Parcelable



fun Student.asStudentEntity():StudentEntity =
        StudentEntity(
                roll_num=roll_num,
                name = name,father_name = father_name,
                age = age,education_type = education_type,computer_section = computer_section,
                isEnglishStudent = isEnglishStudent, joining_date = joining_date,finishing_date = finishing_date,
                address = address,hobbies = hobbies,
                student_description = student_description,imageUrl = image_url
        )
