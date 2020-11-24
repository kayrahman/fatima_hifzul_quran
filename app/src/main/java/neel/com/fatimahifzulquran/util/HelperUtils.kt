package neel.com.fatimahifzulquran.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import neel.com.fatimahifzulquran.Constants.Companion.OUTPUT_PATH
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


@Throws(FileNotFoundException::class)
fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
    val name = String.format("cropped_profile_photo-%s.png", UUID.randomUUID().toString())
    val outputDir = File(applicationContext.filesDir, OUTPUT_PATH)
    if (!outputDir.exists()) {
        outputDir.mkdirs() // should succeed
    }
    val outputFile = File(outputDir, name)
    var out: FileOutputStream? = null
    try {
        out = FileOutputStream(outputFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
    } finally {
        out?.let {
            try {
                it.close()
            } catch (ignore: IOException) {
            }

        }
    }
    return Uri.fromFile(outputFile)
}


fun openDatePicker(fragmentManager: FragmentManager, onClick: (String) -> Unit){
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

        onClick(dateString)

    }
    //  datePicker.show(supportFragmentManager, "MyTAG")
      datePicker.show(fragmentManager, "MyTAG")

}
/*

 fun addDialogLauncher(
        viewGroup: ViewGroup, @StringRes stringResId: Int, alertDialogBuilder: AlertDialog.Builder) {
    val dialogLauncherButton = MaterialButton(viewGroup.context)
    dialogLauncherButton.setOnClickListener { v: View? -> alertDialogBuilder.show() }
    dialogLauncherButton.setText(stringResId)
    viewGroup.addView(dialogLauncherButton)
}
*/
