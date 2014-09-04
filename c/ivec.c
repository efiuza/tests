#include <stdlib.h>
#include <stdio.h>

#define IVEC_SIZE 5

static void print_ivec(int, int *);
static void map_square(int, int *);

int main(int argc, char **argv)
{

    int i, j, ivec[IVEC_SIZE];

    /* fill up the vector... */
    for (i = 0; i < IVEC_SIZE; ++i) {
        j = i + 1;
        ivec[i] = j < argc ? atoi(*(argv + j)) : j;
    }

    print_ivec(IVEC_SIZE, ivec);
    map_square(IVEC_SIZE, ivec);
    print_ivec(IVEC_SIZE, ivec);

    return EXIT_SUCCESS;

}

static void print_ivec(int cnt, int *ivec)
{
    int i;
    puts("Printing vector:");
    for (i = 0; i < cnt; ++i) {
        printf(
            "  %02d. %d\n",
            i + 1,
            *(ivec + i)
        );
    }
}

static void map_square(int cnt, int *ivec)
{
    int i, n;
    for (i = 0; i < cnt; ++i) {
        n = *(ivec + i);
        *(ivec + i) = n * n;
    }
}

