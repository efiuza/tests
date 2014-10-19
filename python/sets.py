#/usr/bin/env python
# -*- coding: utf-8 -*-

from sys import argv

argc = len(argv)

if (argc < 2):
    print 'Arguments expected...'
    exit()

sets = list()
for i in range(1, argc):
    sets.append(set(argv[i]))

n = len(sets)
print "\nNumber of supplied sets: " + str(n) + "\n"

# describe each set
print 40 * "-" + "\n"
for i in range(n):
    print "Elements on Set #{0}:".format(i + 1)
    n = len(sets[i])
    s = "\t"
    j = 0
    for sobj in sets[i]:
        if (j > 0):
            s += ", "
        j += 1
        s += sobj
    print s, " #" + str(n) + "\n"

# calculate the difference between sets if number of sets
# is greater than 1
n = len(sets)
if (n > 1):
    print 40 * "-" + "\n"
    # generate string of differences
    s = ""
    for i in range(n):
        if (i > 0):
            s += " - "
        s += "#" + str(i + 1)
    # calculate differences
    sobj = sets[0]
    for i in range(1, n):
        sobj -= sets[i]
    # output result
    print "Difference (" + s + "):"
    s = "\t"
    j = 0
    for i in sobj:
        if (j > 0):
            s += ", "
        j += 1
        s += i
    print s, "#" + str(len(sobj)) + "\n"
