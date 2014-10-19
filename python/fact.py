#!/usr/bin/env python

from factorial import fact, print_fact
from sys import argv

argc = len(argv)

# check number of supplied arguments
if (argc < 2):
    print("Numerical arguments expected...\n")
    exit()

# calculate factorial of each argument
for i in range(1, argc):
    print_fact(argv[i])
