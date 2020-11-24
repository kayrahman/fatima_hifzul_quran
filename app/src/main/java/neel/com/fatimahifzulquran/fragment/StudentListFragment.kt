package neel.com.fatimahifzulquran.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.FragmentStudentListBinding
import neel.com.fatimahifzulquran.fragment.adapters.StudentListGridAdapter
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.viewModel.StudentViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StudentListFragment : Fragment() {

    private val viewModel : StudentViewModel by viewModels()
    private lateinit var binding : FragmentStudentListBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_list,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        val manager = GridLayoutManager(requireActivity(),3)
        binding.rvStudentList.layoutManager = manager

        val adapter = StudentListGridAdapter(StudentListGridAdapter.OnStudentItemClickListener(
                {listener->
            this.findNavController().navigate(StudentListFragmentDirections.actionStudentlistToStudentDetail(listener))
        },{view,student->
            openPopupMenu(view,student)
        }))
        binding.rvStudentList.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToCrudFragment.observe(viewLifecycleOwner, Observer {
            Log.d("addbtnclick",it.toString())
            if(it){
                this.findNavController().navigate(R.id.action_stud_fm_to_crud_fm)
                viewModel.navigateToListFragmentCompleted()
            }
        })

    }

    private fun openPopupMenu(view: View, student: Student) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.menu_student_list)

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {

                R.id.action_update -> {
                   //this.findNavController().navigate(StudentListFragmentDirections.actionStudentlistToUpdateStudent(student))
                    this.findNavController().navigate(StudentListFragmentDirections.actionStudentListFragmentToUpdateStudentInfoFragment(student))
                    true
                }
                R.id.action_delete -> {
                   // this.findNavController().navigate(R.id.action_stud_fm_to_crud_fm)
                    viewModel.deleteStudentRecord(student.roll_num)
                    true
                }
                else -> false
            }
        })

        popupMenu.show()


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_age -> {

                true
            }

            R.id.action_english_class -> {

                true
            }

            R.id.action_joining_date ->{

                true
            }

            else -> super.onOptionsItemSelected(item)

        }

    }

}








