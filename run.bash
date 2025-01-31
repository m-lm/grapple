#!/bin/bash
set -e
mvn clean compile
mvn exec:java