package neel.com.fatimahifzulquran.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.ListItemStudentBinding
import neel.com.fatimahifzulquran.model.Student


class StudentListGridAdapter(val onClickListener: OnStudentItemClickListener) : ListAdapter<Student, StudentListGridAdapter.StudentViewHolder>(DiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding: ListItemStudentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_student,
            parent, false

        )


        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.listener(item)
        }

        holder.binding.ivOptionsMenu.setOnClickListener {
            onClickListener.onOptionMenuClick(holder.itemView,item)
        }

    }


    companion object DiffCallBack : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.roll_num == newItem.roll_num
        }
    }


    class StudentViewHolder(val binding: ListItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.student = student
            binding.executePendingBindings()
        }

    }


    class OnStudentItemClickListener(var listener : (Student) -> Unit,var popupListener : (View,Student)-> Unit){
        fun onClick(student: Student) = listener(student)
        fun onOptionMenuClick(itemView: View, student : Student) = popupListener(itemView,student)
    }

}