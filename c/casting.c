#include <stdlib.h>
#include <stdio.h>

/*
 * Structures
 */

struct task {
  void *data;
  void (*operation)(void *);
};

struct task_entry {
  struct task task;
  struct task_entry *next;
};

/*
 * Operations
 */

static void _opa(void *);
static void _opb(void *);
static void _opc(void *);

/*
 * Main
 */

int main(int argc, char *argv[])
{

  struct task_entry mte, *tep, *tea = NULL;
  int i, j, tealen;
  char chr, *str;
  void (*op)(void *);

  /* check parameters */
  if ( argc < 2 ) {
    fputs("Not enough arguments...\n", stderr);
    return 1;
  }

  /* get rid of application name (first argument)... */
  --argc;
#ifdef _IMPL_ALTERNATIVE
  argv = (char **)((char *)argv + sizeof(char **));
#else
  ++argv;
#endif

  /* alloc space if more than one task */
  tealen = argc - 1;
  if ( tealen > 0 ) {
    tea = (struct task_entry *)malloc( sizeof(struct task_entry) * tealen );
    if ( tea == NULL ) {
      fputs("Not enough memory...\n", stderr);
      return 2;
    }
    i = tealen - 1;
    tea[i].next = NULL;
    while ( i > 0 ) {
#ifdef _IMPL_ALTERNATIVE
#ifdef _IMPL_HARD
      (tea + i - 1)->next = (struct task_entry *)((char *)tea + sizeof(struct task_entry) * i);
#else
      tea[i - 1].next = &tea[i];
#endif
#else
      tea[i - 1].next = tea + i;
#endif
      --i;
    }
  }

  mte.next = tea;

  /* prepare linked list... */
  tep = &mte, i = 0;
  do {
    if ( i >= argc ) {
      fputs("Index out of bounds...\n", stderr);
      return 3;
    }
    op = &_opa;
    str = argv[i];
    chr = str[0] | 0x20;
    if ( (chr == 'a' || chr == 'b' || chr == 'c') && str[1] == ':' ) {
       str += 2;
       if ( chr == 'b' || chr == 'c' )
         op = chr == 'b' ? &_opb : &_opc;
    }
    tep->task.data = (void *)str;
    tep->task.operation = op;
    ++i;
  } while ( (tep = tep->next) != NULL );

  /* execute task list... */
  tep = &mte, i = 1;
  do {
    printf("TASK #%d\n", i++);
    (*tep->task.operation)(tep->task.data);
    puts("DONE!");
  } while ( (tep = tep->next) != NULL );

  /* release memory if any... */
  if ( tea != NULL )
    free(tea);

  return 0;

}

static void _opa(void *data)
{
  puts("$ Operation A...");
  printf(" - Data: %s\n", data);
}

static void _opb(void *data)
{
  int i;
  char chr, *str = (char *)data;
  puts("$ Operation B...");
  printf(" - Data: %s\n", str);
  for ( i = 0; (chr = *(str + i)) != '\0'; ++i ) {
    if ( chr >= 'a' && chr <= 'z' )
      *(str + i) = chr & ~0x20;
  }
  printf(" - Transformed: %s\n", str);
}

static void _opc(void *data)
{
  int i;
  char chr, *str = (char *)data;
  puts("$ Operation C...");
  printf(" - Data: %s\n", str);
  for ( i = 0; (chr = *(str + i )) != '\0'; ++i ) {
    if ( (chr & 0x60) == 0x60 )
      *(str + i) = chr & ~0x20;
  }
  printf(" - Transformed: %s\n", str);
}

