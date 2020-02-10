#!/bin/bash

echo "Stopping Emulator";
adb kill-server

adb -s emulator-5554 emu kill