package com.vis.mycustomlauncher

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class AppRepository(context: Context, appName: String) {
    private val packageManager: PackageManager = context.packageManager
    private val applicationInfo: ApplicationInfo = context.applicationInfo
    private val settingsApp = AppDetail(
            label = "$appName settings",
            icon = packageManager.getApplicationIcon(applicationInfo),
            intent = Intent(context, PreferencesActivity::class.java)
    )

    fun findAll(): List<AppDetail> {
        val i = Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER)
////works until android 10 from android 11 requires query parameter in manifest
        return packageManager.queryIntentActivities(i, 0).map {
            AppDetail(
                    label = it.loadLabel(packageManager).toString(),
                    name = it.activityInfo.packageName,
                    icon = it.activityInfo.loadIcon(packageManager),
                    intent= Intent(packageManager.getLaunchIntentForPackage(it.activityInfo.packageName)
                     ))
        }.plus(settingsApp).sortedBy { it.label }
    }
}
