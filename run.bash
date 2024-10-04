#!/bin/bash
set -e
g++ main.cpp -std=c++11
./a.out $*