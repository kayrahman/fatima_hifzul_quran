package neel.com.fatimahifzulquran.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.model.asStudentEntity


class StudentRepo(private val studentDao: StudentDao) {

    val allStudents : LiveData<List<Student>> =
        Transformations.map(
        studentDao.getStudentsByAlphabeticalOrder()){
            it.asStudentList()
        }

    fun studentsByJoiningDate(start_date:Long,end_date:Long) :  LiveData<List<Student>> =
       Transformations.map(
            studentDao.getStudentsByJoiningDate(start_date,end_date)){
            it.asStudentList()
        }

    suspend fun updateStudentInfo(student : Student){
        withContext(Dispatchers.IO){
           val response_update = studentDao.updateStudentRecord(student.asStudentEntity())
            Log.d("response_update",response_update.toString())
        }
    }

   suspend fun deleteStudentByRollNum(roll_num:Long){
       withContext(Dispatchers.IO) {
           studentDao.deleteStudentRecordById(roll_num)
       }
    }



    suspend fun insert(student:StudentEntity){

        withContext(Dispatchers.IO) {
            studentDao.insert(student)
        }
    }




}