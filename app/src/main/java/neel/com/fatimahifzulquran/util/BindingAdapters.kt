package neel.com.fatimahifzulquran.util

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.fragment.StudentListGridAdapter
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.util.Converters.Companion.formattedStringFromDate
import java.util.*


@BindingAdapter("crudImageUrl")
fun bindLocalImage(imageView: ImageView, url : Uri?){

    if(url!=null) {
        Log.d("image","not empty")
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }else{

        Log.d("image","null")
        imageView.setImageResource(R.drawable.ic_profile)
    }

}


@BindingAdapter("imageUrl")
fun bindListImage(imageView: ImageView, image : String){

    if(image.isNotEmpty() || image.isNotBlank()) {
        Log.d("image",image)
        Glide.with(imageView.context)
                .load(Uri.parse(image.trim()))
                .into(imageView)
    }else{

        Log.d("image","null")
        imageView.setImageResource(R.drawable.ic_profile)
    }

}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Student>?) {
    val adapter = recyclerView.adapter as StudentListGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("ageWithYear")
fun bindAgeWithYear(textView: TextView, age : Int) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.ageWithYear), age)
}

@BindingAdapter("joiningDateFormatted")
fun bindJoiningDate(textView: TextView, joining_date : Date) {
    val context = textView.context
   // textView.text = String.format(context.getString(R.string.ageWithYear), age)
    textView.text = formattedStringFromDate(joining_date)

}