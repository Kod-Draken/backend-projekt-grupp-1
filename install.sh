#!/bin/bash

echo "Installing"
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "Build successful!"
else
    echo "Build failed!"
    exit 1
fi