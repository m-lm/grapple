#!/bin/bash
set -e
javac *.java
java Main
./a.out $*
