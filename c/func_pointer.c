#include <stdlib.h>
#include <stdio.h>

struct st {
    int num;
    int (*func)(int);
};

static int square(int base) {
    return (base * base);
}

int main(int argc, char *argv[])
{

    struct st st;

    if (argc < 2) {
        puts("Numeric argument expected...");
        goto _exit_failure;
    }

    st.num = atoi(argv[1]);
    st.func = square; /* this works too: &square; */

    printf(
        "Number: %d, Square: %d\n",
        st.num,
        (*st.func)(st.num)
    );

    return EXIT_SUCCESS;

_exit_failure:
    return EXIT_FAILURE;

}

