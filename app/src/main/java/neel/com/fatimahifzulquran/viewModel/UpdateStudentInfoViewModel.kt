package neel.com.fatimahifzulquran.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.database.StudentDatabase
import neel.com.fatimahifzulquran.database.StudentRepo
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.model.asStudentEntity
import neel.com.fatimahifzulquran.util.Converters
import neel.com.fatimahifzulquran.util.Converters.Companion.formattedStringFromDate
import java.lang.Exception

class UpdateStudentInfoViewModel(val app : Application,val student : Student) : ViewModel() {

    val studentName = MutableLiveData<String>()
    val studentFatherName = MutableLiveData<String>()
    val studentAge = MutableLiveData<String>()
    val studentAddress = MutableLiveData<String>()
    val joiningDate = MutableLiveData<String>()
    val finishingDate = MutableLiveData<String>()
    val hobbies = MutableLiveData<String>()
    val studentImage = MutableLiveData<String>()
    val eduType = MutableLiveData<String>()
    val isEnglishStudent = MutableLiveData<Boolean>(false)
    val rbComputerSection = MutableLiveData<String>()


    private val studentDatabase = StudentDatabase.getInstance(app)
    private val repo = StudentRepo(studentDatabase.studentDao)

    private val _isJoiningDateClicked = MutableLiveData<Boolean>(false)
    val isJoiningDateClicked :LiveData<Boolean>
    get() = _isJoiningDateClicked


    init {
        studentName.value = student.name
        studentFatherName.value = student.father_name
        studentAge.value = student.age.toString()
        studentAddress.value = student.address
        joiningDate.value = formattedStringFromDate(student.joining_date)
        finishingDate.value = formattedStringFromDate(student.finishing_date)
        hobbies.value = student.hobbies
        studentImage.value = student.image_url

    }


    fun updateStudentInfo(){
        viewModelScope.launch {
            try{
                val student = Student(
                    roll_num = student.roll_num,
                    name = studentName.value.toString(),
                    father_name = studentFatherName.value.toString(),
                    education_type = eduType.value.toString(),
                    computer_section = rbComputerSection.value.toString(),
                    isEnglishStudent = isEnglishStudent.value!!,
                    age = studentAge.value?.toInt()!!,
                    address = studentName.value.toString(),
                    hobbies = studentName.value.toString(),
                    joining_date = Converters.formattedDateFromString(joiningDate.value.toString()),
                    finishing_date = Converters.formattedDateFromString(finishingDate.value.toString()),
                    student_description = hobbies.value.toString(),
                    image_url = studentImage.value.toString()
                )

                repo.updateStudentInfo(student)
            }catch (e:Exception){
                Log.d("response_update",e.toString())
            }
        }

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
        if(view is RadioButton){
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
    fun onProfileImageClick(){}

    fun onJoiningDateClick(){
        _isJoiningDateClicked.value = true
    }

    fun updateJoiningDate(date:String){
        joiningDate.value = date
    }


    class Factory(val app: Application,val student: Student) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UpdateStudentInfoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UpdateStudentInfoViewModel(app,student) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }



}











