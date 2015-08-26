# Preamble #

Before building, it is important that you read the [Licensing](Licensing.md) page. This ABC-VoIP app is GPLv3, and it is really **necessary** to understand what that means before developing or distributing this application or modified copies.

# Basics #

The application has two distinct parts, which guide the compilation process :

  * The pjsip dynamic library part that, when compiled, produces a .so file (to be more precise a .so by android target).
  * The java application part that produce a .apk file (and can be installed on your android device).

Both, the java and the native part must have the same version of code if you want the app to run. This is due to a high correlation between the sip library and the calls made to it by the java application. Thus, we recommend to build both parts consecutively and follow the complete process.


# Requirements #

The requirements to compile both parts of the application are meant to be easily satisfied. The following guide and instructions are targeted towards compiling on a Linux system. While compilation and development on other systems in possible, it hasn't been thoroughly tested and provides greater difficulty to set up.

## System Requirements ##

For the successful compilation the following are required:
  * python
  * make
  * subversion
  * git
  * quilt
  * unzip
  * wget
  * swig2.0

You can obtain them by the simple command (in Debian based systems):
```
sudo apt-get install subversion git quilt unzip wget swig2.0 python make
```

You **may** need to adapt the above mentioned _**package names**_ depending on your _**distribution**_. If compiling on a non Linux system, the key requirement is to ensure that these prerequisites are available as _command line tools_ in your development environment.


You will also need the **Android SDK** and the **Android NDK**. Since these change about as often as the Android OS, we do not explain their installation instructions here. Please consult their corresponding documentation:
  * [Android SDK](http://developer.android.com/sdk/installing.html)
  * [Android NDK](http://developer.android.com/sdk/ndk/index.html)

The build process will assume that it can find and use the Android NDK and SDK at compile time, so we recommend putting them in your PATH. Here's an example of how to do this in Linux:
```
export ANDROID_NDK=/_path_to/android-ndk-linux/
export ANDROID_SDK=/_path_to/android-sdk-linux/
export PATH=$PATH:$ANDROID_SDK/tools:$ANDROID_SDK/platform-tools:$ANDROID_NDK
```

You don't have to do this as a permanent setting, as this change can be done only at compile time. However, if you're likely to work with Android development in the near future, this step could come in useful.


## Developer Requirements ##

This guide assumes a certain familiarity with development processes and practices. The following are recommended required knowledge for any developer aiming to successfully build and/or change the application:
  * Linux (or `*`nix systems)
  * JAVA
  * compilation of C code
  * Makefiles (how they work)
  * Android development

If you find yourself having trouble with any of the above, we recommend first looking for resources on-line about the topic in question. We know there are many great resources out there to help you. If you couldn't find the answer yourself, you can contact us with your question and we will answer to the best of our ability and availability.