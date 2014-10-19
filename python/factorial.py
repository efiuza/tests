# -*- coding: utf-8 -*-

def fact(n):
    try:
        # make sure n is an int
        n = int(n)
        # check argument value
        if (n < 1):
            raise ValueError
        # calculate value
        i = r = 1
        while i < n:
            i = i + 1
            r = r * i
        return r
    except:
        return -1;


def print_fact(n):
    f = fact(n)
    if (f > 0):
        print("{0}! = {1}".format(n, f))
    else:
        print('"{0}" is not a valid argument'.format(n))

