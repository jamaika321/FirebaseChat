package ru.radiknasybullin.chatapp.presentation.ui.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import ru.radiknasybullin.chatapp.data.database.*
import ru.radiknasybullin.chatapp.data.utilits.*
import ru.radiknasybullin.chatapp.databinding.FragmentVerifyBinding

class VerifyFragment(val phoneNumber: String, val id: String) : Fragment() {

    lateinit var mBinding: FragmentVerifyBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentVerifyBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = phoneNumber
        mBinding.idOtp.addTextChangedListener(AppTextWatcher {
            val string = mBinding.idOtp.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = mBinding.idOtp.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = ""


                REF_DATABASE_ROOT.child(NODE_USERS).child(phoneNumber)
                    .addListenerForSingleValueEvent(AppValueEventListener {

                        REF_DATABASE_ROOT.child(
                            NODE_PHONES
                        ).child(phoneNumber).setValue(uid)
                            .addOnFailureListener { showToast(it.message.toString()) }
                            .addOnSuccessListener {
                                REF_DATABASE_ROOT.child(
                                    NODE_USERS
                                ).child(phoneNumber).updateChildren(dateMap)
                                    .addOnSuccessListener {
                                        showToast("Добро пожаловать")
                                        restartActivity()
                                    }
                                    .addOnFailureListener { showToast(it.message.toString()) }
                            }
                    })


            } else showToast(task.exception?.message.toString())
        }
    }

    private fun initUserName(){

    }

}