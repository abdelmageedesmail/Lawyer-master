package al.ib.lawyer.view

import al.ib.lawyer.MainActivity
import al.ib.lawyer.R
import al.ib.lawyer.app.ChangeLanguage
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.widget.TextView
import android.os.Build
import java.util.*


class SelectLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)

        val english = findViewById<TextView>(R.id.english)
        val arabic = findViewById<TextView>(R.id.arabic)

        val userManager = al.ib.lawyer.app.UserManager(this)

        if (userManager.hasSelectedLanguage()){
            ChangeLanguage.updateLanguage(SelectLanguageActivity@this, userManager.getLanguage(), false)
            if (userManager.isLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        english.setOnClickListener {
            if (!userManager.isLoggedIn)
                startActivity(Intent(this, LoginActivity::class.java))
            else startActivity(Intent(this, MainActivity::class.java))

            ChangeLanguage.updateLanguage(SelectLanguageActivity@this, "en", false)
            userManager.setSelectedLanguage(true)
            userManager.setLanguage("en")
            finish()
        }

        arabic.setOnClickListener {
            if (!userManager.isLoggedIn)
                startActivity(Intent(this, LoginActivity::class.java))
            else startActivity(Intent(this, MainActivity::class.java))

            ChangeLanguage.updateLanguage(SelectLanguageActivity@this, "ar", false)
            userManager.setSelectedLanguage(true)
            userManager.setLanguage("ar")
            finish()
        }
    }


}
