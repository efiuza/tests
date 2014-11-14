#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define LENGTH 8

void swap(void *, void *, size_t);
static void copy(char *, char*);

int main(int argc, char *argv[]) {

    long la, lb;
    int ia, ib;
    short sa, sb;
    char stra[LENGTH + 1], strb[LENGTH + 1];

    if (argc < 3) {
        puts("Oops! Two arguments expected...");
        return EXIT_SUCCESS;
    }

    copy(argv[1], stra);
    copy(argv[2], strb);
    printf("Received: %s, %s\n", stra, strb);
    swap(stra, strb, sizeof(stra));
    printf("Swap: %s, %s\n\n", stra, strb);

    la = atol(argv[1]);
    lb = atol(argv[2]);
    printf("Received: %ld, %ld (long #%ld)\n", la, lb, sizeof(la));
    swap(&la, &lb, sizeof(la));
    printf("Swap: %ld, %ld (long #%ld)\n\n", la, lb, sizeof(lb));

    ia = atoi(argv[1]);
    ib = atoi(argv[2]);
    printf("Received: %d, %d (int #%ld)\n", ia, ib, sizeof(ia));
    swap(&ia, &ib, sizeof(ia));
    printf("Swap: %d, %d (int #%ld)\n\n", ia, ib, sizeof(ib));

    sa = (short)atoi(argv[1]);
    sb = (short)atoi(argv[2]);
    printf("Received: %d, %d (short #%ld)\n", (int)sa, (int)sb, sizeof(sa));
    swap(&sa, &sb, sizeof(sa));
    printf("Swap: %d, %d (short #%ld)\n\n", (int)sa, (int)sb, sizeof(sb));

    return EXIT_SUCCESS;

}

static void copy(char *a, char *b) {

    int i, len = strlen(a);

    if (len > LENGTH)
        len = LENGTH;

    for (i = 0; i < LENGTH; ++i)
        *(b + i) = i < len ? *(a + i) : '_';

    *(b + i) = '\0';

}

void swap(void *a, void *b, size_t size) {
    char buf;
    while (size > 0) {
        --size;
        buf = *((char *)a + size);
        *((char *)a + size) = *((char *)b + size);
        *((char *)b + size) = buf;
    }
}

