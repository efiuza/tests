#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

    char *format = "%02d. %s\n";
    int i;

    for (i = 1; i < argc; ++i) {
        printf(format, i, argv[i]);
    }

    return EXIT_SUCCESS;

}

