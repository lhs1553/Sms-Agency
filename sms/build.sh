#!/bin/sh


rm -rf target

gradle clean bootjar
gradle bootjar --stacktrace --debug

mkdir target
cp build/libs/*.jar target/


