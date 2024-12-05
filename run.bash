#!/bin/bash
set -e
CORE_JAR="lib/gs-core-2.0.jar"
SWING_JAR="lib/gs-ui-swing-2.0.jar"
find . -name "*.java" -print | xargs javac -cp "$CORE_JAR:$SWING_JAR"
java -cp "$CORE_JAR:$SWING_JAR:." src.core.Main "$@"