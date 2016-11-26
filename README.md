# Mobile Vision API Demo
Google Developers Festival Hanoi 2016 is held in Posts and Telecommunications Institude of Technology at November 26th, 2016.

I have a talk about Mobile Vision API in Android (Vietnamese), with comparison against OpenCV. This repository contains demo code from multiple examples of Google about Mobile Vision API, including face detection, a funny overlay graphic within detected face, barcode detection, text recognition and usage of multi-detectors. 

Note that those examples are from Google, I just make some modifications for easier reading and organizing. I myself implement a simple face detection using OpenCV on Android for reference. The slide is shared at https://docs.google.com/presentation/d/1cSbQDsil3YyHCQIEi2HSBmhy533NLmtt7jKUg2qfuvM/edit?usp=sharing (English).

Contact me at diep@awpspace.net if you have any question.

# Notes
Please make sure your local.properties has NDK and OpenCV variables set, like this:
```
ndk.dir=/Volumes/DATA/App/Android/ndk
sdk.dir=/Volumes/DATA/App/Android
opencv.dir=/Volumes/DATA/App/Android/opencvlibs/sdk/native/jni/include
```
OpenCV JNI Libraries must be copied from OpenCV Android SDK to /src/main/jniLibs. Those libraries are ignored from this repository.
