package neel.com.fatimahifzulquran.fragment.studentDetail

import android.app.Application
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.FragmentComputerTestBinding
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.viewModel.UpdateStudentInfoViewModel


class ComputerTestFragment : Fragment() {


    private lateinit var binding : FragmentComputerTestBinding
    private val safeVarargs : ComputerTestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val section = safeVarargs.computerSection
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_computer_test,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

/*


    class Factory(val app: Application, val section : String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UpdateStudentInfoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UpdateStudentInfoViewModel(app,section) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
*/




}