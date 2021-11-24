package ru.radiknasybullin.chatapp.presentation.ui.fragments.settings

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import ru.radiknasybullin.chatapp.R
import ru.radiknasybullin.chatapp.data.database.*
import ru.radiknasybullin.chatapp.data.utilits.APP_ACTIVITY
import ru.radiknasybullin.chatapp.data.utilits.showToast
import ru.radiknasybullin.chatapp.databinding.FragmentProfileBinding


class ProfileFragment(): Fragment(){
    private lateinit var mBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProfileBinding.inflate(inflater, container, false)

        if (USER.name == ""){
            showToast("Введите ваше имя!")
        }
        mBinding.linearName.setOnClickListener {
            nameInitDialog("CHILD_USERNAME")
        }
        mBinding.tvProfileName.text = USER.name



        return mBinding.root
    }

    private fun nameInitDialog(dataType: String){
        val edName: EditText? = view?.findViewById(R.id.ed_name)
        val builder = AlertDialog.Builder(APP_ACTIVITY)
        builder.setView(R.layout.alert_label_editor)
        builder.setTitle("Androidly Alert")
        builder.setMessage("We have a message")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            changeUserData(dataType, edName?.text.toString())
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            showToast("no")
        }

        builder.setNeutralButton("Maybe") { dialog, which ->
            showToast("maybe")
        }
        builder.show()
    }

    private fun changeUserData(dataType: String, values: String){
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_USERNAME] = values
        REF_DATABASE_ROOT.child(USER.phone).child(CHILD_USERNAME).updateChildren(dateMap)
    }

}