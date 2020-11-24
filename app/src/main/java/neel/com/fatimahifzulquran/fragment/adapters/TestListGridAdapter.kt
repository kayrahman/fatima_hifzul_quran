package neel.com.fatimahifzulquran.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.ListItemStudentBinding
import neel.com.fatimahifzulquran.databinding.ListItemTestBinding
import neel.com.fatimahifzulquran.model.ClassTest
import neel.com.fatimahifzulquran.model.Student


class TestListGridAdapter() : ListAdapter<ClassTest, TestListGridAdapter.StudentViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding: ListItemTestBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_test,
            parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
           // onClickListener.listener(item)
        }

        holder.binding.ivOptionsMenu.setOnClickListener {
           // onClickListener.onOptionMenuClick(holder.itemView,item)
        }

    }


    companion object DiffCallBack : DiffUtil.ItemCallback<ClassTest>() {
        override fun areItemsTheSame(oldItem: ClassTest, newItem: ClassTest): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ClassTest, newItem: ClassTest): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class StudentViewHolder(val binding: ListItemTestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(classTest: ClassTest) {
            binding.student = classTest
            binding.executePendingBindings()
        }

    }


    class OnStudentItemClickListener(var listener : (ClassTest) -> Unit,var popupListener : (View,ClassTest)-> Unit){
        fun onClick(student: ClassTest) = listener(student)
        fun onOptionMenuClick(itemView: View, student : ClassTest) = popupListener(itemView,student)
    }

}