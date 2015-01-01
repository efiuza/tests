#!/bin/bash

# define block size...
b=$[2**20]

if [ -f "$1" ]
then
  s=$(stat -c %s "$1")
  echo "File Name: $1"
  echo "File Size: $s bytes"
  if [ $s -gt 0 -a $[ $s % $b ] -eq 0 ]
  then
    echo "\$ $[ $s / $b ] blocks of $b bytes"
  fi
else
  echo "File not found..."
fi

