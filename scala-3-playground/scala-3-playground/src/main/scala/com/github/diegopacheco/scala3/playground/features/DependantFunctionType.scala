package com.github.diegopacheco.scala3.playground.features

trait Entry { type Key; val key: Key }

def extractKey(e:Entry):e.Key = e.key          // a dependent method

val extractor:(e: Entry) => e.Key = extractKey  // a dependent function value
//             ^^^^^^^^^^^^^^^^^^^
//             a dependent function type