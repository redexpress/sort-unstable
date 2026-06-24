# sort-unstable

Quick, unstable sort for custom objects in Java.

## What it does

Sorts arrays and lists of custom objects.

## API

- `sort(T[])` — array, natural order (`T extends Comparable<? super T>`)
- `sort(T[], int, int)` — array, range `[from, to]`
- `sort(T[], Comparator)` — array, custom order
- `sort(List<T>)` — list, natural order
- `sort(List<T>, Comparator)` — list, custom order

## Benchmark

Run `mvn test -Dtest=Benchmark`.

N = 2,000,000, seed = 42, 4 datasets (random, nearly sorted, reverse, duplicates).

custom object random	Arrays.sort         674.693 ms
custom object random	Sort.sort           356.682 ms
custom object nearly sorted	Arrays.sort  3.462 ms
custom object nearly sorted	Sort.sort    47.891 ms
custom object reverse	Arrays.sort        29.967 ms
custom object reverse	Sort.sort          50.135 ms
custom object duplicates	Arrays.sort     278.675 ms
custom object duplicates	Sort.sort       277.653 ms
