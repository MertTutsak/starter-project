package com.merttutsak.starter.utility.extension

import android.content.Context
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.merttutsak.starter.R

fun DrawerLayout.setDrawer(activity: AppCompatActivity,  actionBar: ActionBar, fragment: Fragment){
    activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    val actionBarDrawerToggle = object : ActionBarDrawerToggle(
        activity,
        this,
        R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
    ) {
        override fun onDrawerOpened(drawerView: View) {
            activity.hideKeyboard()
            super.onDrawerOpened(drawerView)
        }
    }
    this.addDrawerListener(actionBarDrawerToggle)
    actionBarDrawerToggle.syncState()
}

fun DrawerLayout.show(context: Context){
    if (!this.isDrawerOpen(GravityCompat.START)) {
        this.openDrawer(GravityCompat.START)
        context.hideKeyboard()
    }
}

fun DrawerLayout.hide(context: Context){
    if (this.isDrawerOpen(GravityCompat.START)) {
        this.closeDrawer(GravityCompat.START)
        context.hideKeyboard()
    }
}

fun DrawerLayout.toggle(context: Context){
    if (this.isDrawerOpen(GravityCompat.START)) {
        hide(context)
    }else{
        show(context)
    }
}