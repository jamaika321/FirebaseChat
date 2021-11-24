package ru.radiknasybullin.chatapp.presentation.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import ru.radiknasybullin.chatapp.R
import ru.radiknasybullin.chatapp.data.database.AUTH
import ru.radiknasybullin.chatapp.data.database.USER
import ru.radiknasybullin.chatapp.data.database.initFirebase
import ru.radiknasybullin.chatapp.data.database.initUser
import ru.radiknasybullin.chatapp.data.utilits.APP_ACTIVITY
import ru.radiknasybullin.chatapp.data.utilits.replaceFragment
import ru.radiknasybullin.chatapp.data.utilits.restartActivity
import ru.radiknasybullin.chatapp.data.utilits.showToast
import ru.radiknasybullin.chatapp.databinding.ActivityMainBinding
import ru.radiknasybullin.chatapp.presentation.ui.fragments.homePage.HomeFragment
import ru.radiknasybullin.chatapp.presentation.ui.fragments.register.LoginFragment
import ru.radiknasybullin.chatapp.presentation.ui.fragments.settings.ProfileFragment
import ru.radiknasybullin.chatapp.presentation.ui.fragments.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        APP_ACTIVITY = this
        initFirebase()
        initUser {
                initHomePage()
        }
    }

    private fun initHomePage() {
        if (AUTH.currentUser != null) {
            if (USER.name.isEmpty()) {
                replaceFragment(ProfileFragment())
            } else {
                replaceFragment(HomeFragment(), false)
            }
        } else {
            replaceFragment(LoginFragment(), false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out -> {
                showToast("салам алейкум")
                AUTH.signOut()
                restartActivity()
            }
            R.id.settgins -> {
                replaceFragment(SettingsFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }


}