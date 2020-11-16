package neel.com.fatimahifzulquran.viewModel

import android.app.Application
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.database.StudentDatabase.Companion.getInstance
import neel.com.fatimahifzulquran.database.StudentRepo
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.model.asStudentEntity
import neel.com.fatimahifzulquran.util.Converters.Companion.formattedDateFromString
import java.lang.Exception


class StudentViewModel(application : Application) : AndroidViewModel(application){

    val _studentName = MutableLiveData<String>()
    val studentName : LiveData<String>
    get() = _studentName

    val studentFatherName = MutableLiveData<String>()
    val address = MutableLiveData<String>()

    val _studentAge = MutableLiveData<String>()
    val studentAge : LiveData<String>
        get() = _studentAge


    val _studentJoiningDate = MutableLiveData<String>()
    val studentJoiningDate : LiveData<String>
        get() = _studentJoiningDate

    val _studentFinshingDate = MutableLiveData<String>()

    val _studentImage = MutableLiveData<Uri>()
    val studentImage : LiveData<Uri>
        get() = _studentImage


    val studentDescription = MutableLiveData<String>()
    val eduType = MutableLiveData<String>()
    val isEnglishStudent = MutableLiveData<Boolean>(false)
    val rbComputerSection = MutableLiveData<String>()



    private val isPhotoChosen = MutableLiveData<Boolean>()
    val openGallery : LiveData<Boolean>
    get() = isPhotoChosen



    private val _addNewStudent = MutableLiveData<Student>()
    val addNewStudent : LiveData<Student>
    get() = _addNewStudent


    private val studentDatabase = getInstance(application)
   // private val repo = StudentRepository(studentDatabase)
    private val repo = StudentRepo(studentDatabase.studentDao)

    private val  _navigateToCrudFragment = MutableLiveData<Boolean>()
    val navigateToCrudFragment : LiveData<Boolean>
    get() = _navigateToCrudFragment




   // val students  = repo.allStudents
    val students  = repo.studentsByJoiningDate(1514743200000L,1577815200000L)

    fun onProfileImageClick(){
        isPhotoChosen.value = true
    }

    fun updateStudentImage(uri : Uri){
        _studentImage.value = uri
    }



    fun addNewStudent() {
        viewModelScope.launch {

            try {

                val student = Student(
                        name = studentName.value.toString(),
                        father_name = studentFatherName.value.toString(),
                        education_type = eduType.value.toString(),
                        computer_section = rbComputerSection.value.toString(),
                        isEnglishStudent = isEnglishStudent.value!!,
                        age = studentAge.value?.toInt()!!,
                        address = studentName.value.toString(),
                        hobbies = studentName.value.toString(),
                        joining_date = formattedDateFromString(studentJoiningDate.value.toString()),
                        finishing_date = formattedDateFromString(_studentFinshingDate.value.toString()),
                        student_description = studentDescription.value.toString(),
                        image_url = studentImage.value.toString()
                )

                repo.insert(student.asStudentEntity())

            }catch (e:Exception){
                Log.d("student_vm",e.toString())
            }

        }
    }


    fun navigateToListFragmentCompleted(){
        _navigateToCrudFragment.value = false
    }


    fun onAddBtnClick(){
        _navigateToCrudFragment.value = true
    }

    enum class EduType{
        Nazera, Hifz, Noorani
    }

    fun onEduTypeRbClick(view:View){
        if(view is RadioButton){
           // val checked = view.isChecked

            when(view.id){
                R.id.rb_nazera -> {
                    eduType.value = EduType.Nazera.toString()
                }
                R.id.rb_hifz -> {
                eduType.value = EduType.Hifz.toString()
                }

                R.id.rb_noorani -> {
                    eduType.value = EduType.Noorani.toString()
                }

                else -> ""
            }
        }
    }

    enum class ComputerSection {
        A,B,C
    }

    fun onComputerSectionRbClick(view:View){
        if(view is RadioButton ){
            when(view.id){
                R.id.rb_cs_a -> {
                    rbComputerSection.value = ComputerSection.A.toString()
                }
                R.id.rb_cs_b ->{
                    rbComputerSection.value = ComputerSection.B.toString()
                }
                R.id.rb_cs_c ->{
                    rbComputerSection.value = ComputerSection.C.toString()
                }
            }
        }
    }

    fun onEnglishStudentTypeClick(view: View){
        if(view is RadioButton){
                when (view.id){
                    R.id.rb_es_yes -> isEnglishStudent.value = true
                    R.id.rb_es_no -> isEnglishStudent.value = false
                }
        }
    }

    fun onJoiningDateClick(){

    }


    fun deleteStudentRecord(rollNum: Long) =  viewModelScope.launch  {
        repo.deleteStudentByRollNum(rollNum)
    }


}







