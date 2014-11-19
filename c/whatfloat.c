#include <limits.h>
#include <stdlib.h>
#include <stdio.h>

#if CHAR_BIT < 8
#error Unsupported Architecture...
#endif

static int parseNumber(const char *);

int main(int argc, char *argv[]) {

  int num;

  if ( argc < 2 ) {
    puts("Numeric argument expected...");
    goto _exit;
  }

  num = parseNumber(argv[1]);

  printf(
    "Supplied integer: %d\n"
    "Float interpretation: %.8f\n",
    num,
    *(float *)&num
  );

_exit:
  return EXIT_SUCCESS;

}

static int parseNumber(const char *s) {

  char ch;
  int i, d, n = 0, isHex = ( *s == '0' && ( *(s + 1) | 0x20 ) == 'x' );

  for ( i = isHex ? 2 : 0; ( ch = *(s + i) ) != '\0'; ++i ) {
    if ( ch < '0' || ch > '9' ) {
      if ( !isHex || ( ch |= 0x20 ) < 'a' || ch > 'f' ) {
        break;
      }
      d = ( ch - 'a' ) + 10;
    } else {
      d = ch - '0';
    }
    n = isHex ? ( ( n << 4 ) | d ) : ( ( n * 10 ) + d );
  }

  return n;

}

