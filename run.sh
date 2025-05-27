#!/bin/bash

echo "Starting app..."
java -jar ./target/GymBookingApp.jar

if [ $? -eq 0 ]; then
    echo "Build successful!"
else
    echo "Build failed!"
    exit 1
fi