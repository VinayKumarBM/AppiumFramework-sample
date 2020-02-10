echo "Staring emulator..."
adb devices
cd %ANDROID_HOME%\emulator
emulator @MyAVD
echo "Emulator has started."