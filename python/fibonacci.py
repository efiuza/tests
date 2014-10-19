# -*- coding: utf-8 -*-

def numbers(n):
    try:
        n = int(n)
        if (n < 1):
            raise ValueError
        # check if item list exists and is valid
        if not hasattr(numbers, "items") or len(numbers.items) < n:
            # build item list
            r = []
            c = n
            a, b = 0, 1
            while c > 0:
                r.append(a)
                t = a + b
                a, b = b, t
                c -= 1
            setattr(numbers, "items", tuple(r))
        return numbers.items[0:n]
    except:
        # an empty tuple is returned
        return tuple()


def number_at_index(n):
    try:
        n = int(n)
        if (n < 0):
            raise ValueError
        r = numbers(n + 1)
        return r[n]
    except:
        return -1
