package neel.com.fatimahifzulquran.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import neel.com.fatimahifzulquran.model.Student
import java.util.*


@Entity(tableName = "student_table")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
        var roll_num:Long = 0L,
    val name: String,
    val father_name: String,
    val age: Int,
    val education_type:String,
    val computer_section: String,
    val isEnglishStudent:Boolean,
    val joining_date : Date?,
    val finishing_date : Date?,
    val address: String,
    val hobbies: String,
    val emergency_contact: String,
    val imageUrl:String,
    val ambition:String
)

fun StudentEntity.asStudent():Student =
        Student(
                roll_num = roll_num,name = name,father_name = father_name,
                age = age,education_type = education_type,computer_section = computer_section,
                isEnglishStudent = isEnglishStudent,
                joining_date = joining_date,finishing_date = finishing_date,address = address,hobbies = hobbies,
                emergency_contact = emergency_contact,image_url = imageUrl,ambition = ambition
        )

fun List<StudentEntity>.asStudentList() : List<Student> =
        map {
                it.asStudent()
        }
