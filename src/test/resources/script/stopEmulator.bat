echo "Stopping emulator..."
adb kill-server
adb -s emulator-5554 emu kill
echo "Emulator has stopped"
