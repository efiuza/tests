
all: test_c test_s

test_c: test.c
	gcc test.c -Wall -ansi -pedantic -o test_c

test_s: test_s.o
	ld test_s.o --dynamic-linker /lib/ld-linux.so.2 -lc -o test_s

test_s.o: test.s
	as test.s -o test_s.o

