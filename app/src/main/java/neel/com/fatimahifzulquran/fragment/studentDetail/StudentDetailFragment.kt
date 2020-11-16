package neel.com.fatimahifzulquran.fragment.studentDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.StudentDetailFragmentBinding


class StudentDetailFragment : Fragment() {
    private lateinit var  binding : StudentDetailFragmentBinding
    val safeVarargs : StudentDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.student_detail_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this

        val student = safeVarargs.studentDetail
        binding.viewModel = student

        Log.d("student_detail",student.toString())
    }

}