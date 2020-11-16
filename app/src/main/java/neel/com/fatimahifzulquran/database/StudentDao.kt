package neel.com.fatimahifzulquran.database

import androidx.lifecycle.LiveData
import androidx.room.*
import neel.com.fatimahifzulquran.model.Student


@Dao
interface StudentDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(student : StudentEntity)

    @Query("SELECT * FROM student_table")
    fun getStudentsByAlphabeticalOrder() : LiveData<List<StudentEntity>>

    @Query("SELECT * FROM student_table WHERE joining_date BETWEEN :start_date AND :end_date")
    fun getStudentsByJoiningDate(start_date:Long,end_date:Long) : LiveData<List<StudentEntity>>

    @Query("DELETE FROM student_table WHERE roll_num = :roll_num")
    fun deleteStudentRecordById(roll_num : Long)

    @Update
    fun updateStudentRecord(student : StudentEntity) : Int
}