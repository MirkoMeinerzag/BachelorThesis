#!/bin/bash

# CHANGE THESE FOR YOUR APP
app_package="com.projects.task_hijacking_testapp"
dir_app_name="TaskHijackingTestApp"
MAIN_ACTIVITY="MainActivity"

ADB_EXE="/c/adb/adb" # how you execute adb
ADB_SH="$ADB shell" # this script assumes using `adb root`. for `adb su` see `Caveats`

path_sysapp="/system/priv-app" # assuming the app is priviledged
apk_host="./app/build/outputs/apk/app-debug.apk"
apk_name=$dir_app_name".apk"
apk_target_dir="$path_sysapp/$dir_app_name"
apk_target_sys="$apk_target_dir/$apk_name"

# Delete previous APK
# rm -f $apk_host

# Compile the APK: you can adapt this for production build, flavors, etc.
./gradlew assembleDebug || exit -1 # exit on failure

# Install APK: using adb root
$ADB_SH root 2> /dev/null
$ADB_SH remount # mount system
$ADB_SH push $apk_host $apk_target_sys

# Give permissions
$ADB_SH "chmod 755 $apk_target_dir"
$ADB_SH "chmod 644 $apk_target_sys"

#Unmount system
$ADB_SH "mount -o remount,ro /"

# Stop the app
$ADB_SH shell "am force-stop $app_package"

# Re execute the app
$ADB_SH shell "am start -n \"$app_package/$app_package.$MAIN_ACTIVITY\" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER"