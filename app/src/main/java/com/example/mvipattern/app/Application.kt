package com.example.mvipattern.app

import android.app.Application
import android.content.Context
import com.example.mvipattern.di.ApplicationComponent
import com.example.mvipattern.di.ApplicationModule
import com.example.mvipattern.di.DaggerApplicationComponent
import com.example.mvipattern.utilities.LocaleHelper
import java.util.*


class UserApplication : Application() {

    lateinit var component: ApplicationComponent
        private set
    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.applyLanguageContext(newBase, Locale("en")))
    }

    override fun getApplicationContext(): Context {
        val context = super.getApplicationContext()
        return LocaleHelper.applyLanguageContext(context, Locale("en"))
    }

}