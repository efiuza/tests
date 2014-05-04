#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

    int i;

    printf("Number of arguments supplied: %d\n", argc);

    for (i = 0; i < argc; i++)
        printf("  %02d. %s\n", i + 1, *(argv + i));

    return EXIT_SUCCESS;

}

