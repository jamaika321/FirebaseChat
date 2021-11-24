package ru.radiknasybullin.chatapp.data.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.radiknasybullin.chatapp.data.utilits.AppValueEventListener
import ru.radiknasybullin.chatapp.data.utilits.showToast
import ru.radiknasybullin.chatapp.entities.UserModel

fun initFirebase() {
    /* Инициализация базы данных Firebase */
    AUTH =
        FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
//    USER =
//        UserModel()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
//    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}

inline fun initUser(crossinline function: () -> Unit){
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener{
            USER = it.getValue(UserModel::class.java)
                ?: UserModel()
//            if(USER.name.isEmpty()){
//                USER.name = CURRENT_UID
//            }
            function()
        })
}