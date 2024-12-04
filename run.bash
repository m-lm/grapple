#!/bin/bash
set -e
find . -name "*.java" -print | xargs javac
java src.core.Main "$@"
./a.out $*