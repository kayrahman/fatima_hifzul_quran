package neel.com.fatimahifzulquran.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.UpdateStudentInfoFragmentBinding
import neel.com.fatimahifzulquran.viewModel.UpdateStudentInfoViewModel
import java.util.*

class UpdateStudentInfoFragment : Fragment() {


    private lateinit var viewModel: UpdateStudentInfoViewModel
    private lateinit var binding : UpdateStudentInfoFragmentBinding
   // private val safeArgs : UpdateStudentInfoFragmentArgs by navArgs<>()
    val safeArgs : UpdateStudentInfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.update_student_info_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val student = safeArgs.studentInfo
        viewModel = ViewModelProvider(this,UpdateStudentInfoViewModel.Factory(activity?.application!!,student)).get(UpdateStudentInfoViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.isJoiningDateClicked.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it){
                openDatePicker()
            }
        })


    }

    fun openDatePicker(){
        val builder = MaterialDatePicker.Builder.datePicker()
            .also {
                it.setTitleText("Pick Date")
            }
        // create the date picker
        val datePicker = builder.build()

        datePicker.addOnPositiveButtonClickListener {

            // Create calendar object and set the date to be that returned from selection
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = Date(it)
            /*val dateString = "${calendar.get(Calendar.DAY_OF_MONTH)}- " +
                    "${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.YEAR)}"*/
            val dateString = "${calendar.get(Calendar.YEAR)}-"+"${calendar.get(Calendar.MONTH) + 1}"

            Log.d("date",dateString)

            viewModel.updateJoiningDate(dateString)

        }

      //  datePicker.show(supportFragmentManager, "MyTAG")
        datePicker.show(childFragmentManager, "MyTAG")

    }


    }

