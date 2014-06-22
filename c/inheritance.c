#include <stdio.h>

struct animal {
  int species;
  int paws;
};

struct dog {
  struct animal animal;
  char name[20 + 1];
};

void animal_set_species(struct animal *animal, int species)
{
  animal->species = species;
}

void animal_set_paws(struct animal *animal, int paws)
{
  animal->paws = paws;
}

int animal_get_species(struct animal *animal)
{
  return animal->species;
}

int animal_get_paws(struct animal *animal)
{
  return animal->paws;
}

void dog_init(struct dog* dog)
{
  animal_set_species((struct animal*)dog, 1);
  animal_set_paws((struct animal*)dog, 4);
  *dog->name = '\0';
}

void dog_set_name(struct dog *dog, const char *name)
{
  int i;
  char c, *p = dog->name;
  for (i = 0; i < 20; ++i) {
    c = *(name + i);
    if (c == '\0')
      break;
    *(p + i) = c;
  }
  *(p + i) = '\0';
}

char *dog_get_name(struct dog *dog)
{
  return dog->name;
}

int dog_get_species(struct dog *dog)
{
  return animal_get_species((struct animal *)dog);
}

int dog_get_paws(struct dog *dog)
{
  return animal_get_paws((struct animal *)dog);
}

int main(int argc, char *argv[]) {

  struct dog dog;

  dog_init(&dog);
  dog_set_name(&dog, "This Dog Name is Bigger Than 20 Bytes");

  printf(
    "Dog Name: %s, "
    "Species: %d, "
    "Paws: %d\n",
    dog_get_name(&dog),
    dog_get_species(&dog),
    dog_get_paws(&dog)
  );

  animal_set_paws((struct animal *)&dog, 3);

  printf("New number of paws: %d\n", dog_get_paws(&dog));

  return 0;

}

