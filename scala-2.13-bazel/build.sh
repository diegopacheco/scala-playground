#!/bin/bash

bazel build //src/main/scala/lib:greeting
bazel build //src/main/scala/cmd:runner
