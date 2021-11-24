package ru.radiknasybullin.chatapp.presentation.ui.fragments.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import ru.radiknasybullin.chatapp.R
import ru.radiknasybullin.chatapp.data.database.AUTH
import ru.radiknasybullin.chatapp.data.utilits.APP_ACTIVITY
import ru.radiknasybullin.chatapp.data.utilits.replaceFragment
import ru.radiknasybullin.chatapp.data.utilits.restartActivity
import ru.radiknasybullin.chatapp.databinding.FragmentLoginBinding
import ru.radiknasybullin.chatapp.data.utilits.showToast
import java.util.concurrent.TimeUnit

class LoginFragment() : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Добро пожаловать")
                        restartActivity()
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(
                    VerifyFragment(
                        mPhoneNumber,
                        id
                    )
                )
                Log.d("LoginFragment", id)
            }
        }
        mBinding.loginBtn.setOnClickListener { sendCode() }
    }

    private fun sendCode() {

        if (mBinding.phoneNumber.text.toString().isEmpty()) {
            showToast("Введите номер телефона")
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = "+7" + mBinding.phoneNumber.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            APP_ACTIVITY,
            mCallback
        )
    }


}