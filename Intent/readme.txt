Test projects.
enviroment:<br>
samsung GT-I8150 <br>
ANDROID 版本2.3.6 <br>

APP name :tst<br>

start   acitivity in another package if its package name and class name is known.

start  service  in another package  if its package name and serivce name is known.
note a service is, by definition, private. You cannot start it explicitly from another application unless the manifest definition for the service contains either an intent filter or android:exported="true"
http://stackoverflow.com/questions/18603381/start-service-in-another-package-without-intent-filter

this starts acitivity & service in ../basic & ../serviceBasicPersistent