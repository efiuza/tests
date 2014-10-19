#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys

print("Supplied Arguments:\n")
i = 1
for arg in sys.argv[1:]:
	print("{0}. {1} ({2})".format(i, arg, len(arg)))
	i = i + 1

print "\nTotal: {0}\n".format(len(sys.argv) - 1)
