package com.github.diegopacheco.scalaplayground.conversions;

import java.util.Optional;

public class ReallyJava {
    public Optional<Integer> addTwo(Optional<Integer> opt) {
        return opt.map(value -> value + 2);
    }
}