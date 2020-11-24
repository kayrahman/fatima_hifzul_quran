package neel.com.fatimahifzulquran.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import id.zelory.compressor.Compressor
import neel.com.fatimahifzulquran.Constants.Companion.REQUEST_PICK_FROM_GALLERY
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.databinding.FragmentCrudStudentBinding
import neel.com.fatimahifzulquran.util.openDatePicker
import neel.com.fatimahifzulquran.util.writeBitmapToFile
import neel.com.fatimahifzulquran.viewModel.StudentViewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CRUDStudentFragment : Fragment() {

    private val viewModel : StudentViewModel by viewModels()
    private lateinit var binding : FragmentCrudStudentBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crud_student,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.openGallery.observe(viewLifecycleOwner, Observer {
            if(it){
                choosePhotoFromGallery()
            }
        })


        viewModel.isJoiningDateClick.observe(viewLifecycleOwner, Observer {
            if(it){
                openDatePicker(childFragmentManager){
                    Log.d("joining_datee",it.toString())
                    viewModel.updateJoiningDate(it)
                }

            }
        })


    }


    private fun choosePhotoFromGallery() {
        Dexter.withActivity(requireActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {/* ... */
                        var galleryIntent = Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                        startActivityForResult(galleryIntent, REQUEST_PICK_FROM_GALLERY)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {/* ... */
                      //  msgHelper.toastShort(getString(R.string.msg_missing_read_storage_permission))
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                        token.continuePermissionRequest()
                    }
                }).check()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode === Activity.RESULT_CANCELED) {
            return
        }

        if (requestCode === REQUEST_PICK_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                val contentURI = data.data

                Log.d("content_uri",contentURI.toString())

                CropImage.activity(contentURI!!)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .setMinCropWindowSize(500, 500)
                        .start(requireContext(),this)


            }

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            val result = CropImage.getActivityResult(data)

            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                val thumb_filePath = File(resultUri.path!!)

                try {
                    val thumb_bitmap: Bitmap = Compressor(requireContext())
                            .setMaxHeight(200)
                            .setMaxWidth(200)
                            .compressToBitmap(thumb_filePath)

                   /* val baos = ByteArrayOutputStream()
                    thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    //   thumb_byte = baos.toByteArray()

                    viewModel.thumbImg.value = baos.toByteArray()
*/
                    // viewModel.userImg.value = baos.toByteArray()


                  //  val imageUri = Uri.fromFile(thumb_filePath)
                    val imageUri = writeBitmapToFile(requireContext(),thumb_bitmap)
                    viewModel.updateStudentImage(imageUri)
/*

                    Glide.with(requireActivity())
                            .load(imageUri)
                            .into(binding.ivStudent)
*/

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Log.d("result_uri",error.toString())
            }

        }else{
            Log.d("result_uri","result not found")

        }

    }




}