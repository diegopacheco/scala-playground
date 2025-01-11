### Build
```
sbt compile
```

### Run
```
sbt run
```

### Rationale

The Goals are:

1. Correctness: Proof is correct
2. Predictability: Make it predictable and reliable like water.
3. Repeatability: Make sure always get the SAME RESULT.

Proofing something:

1. Always CROSS_CHECK, there is too systems? IDs should batch in both of THEM.
2. IDs are just numbers, they are not unique, they are just a number. Make sure you get whats behind the ID so you know if you are comparing the same thinng or not - good example: use email.
3. Always check the data, do IFs, do Assertions, do alternative check(proof of 9).
4. Checking NOT NULL or contains 1 is not enough.

Anti-Patterns:

1. Code that produce different results on different runs.
2. Code that does not check anything (not ifs, no assertions, no cross-verifications)
3. Code that "Assume" it's okay but does not verify
4. Smells: Only check if one on the list, get the first on the list, get the last on the list, only check if is not null.
5. Code that rely on magical numbers from previous runs(Ouroboros).
