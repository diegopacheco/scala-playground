#/bin/bash

echo "Generating tables..."
bazel run //db:db_codegen_runner --java_runtime_version=remotejdk_21

#
# Had todo the copy outside of bazel because mr hermetic builds don't let me copy files from bazel-bin
#
echo "Copying generated files..."
mkdir -p ./cmd/src/main/scala/com/github/diegopacheco/scalaplayground/db/slick/
chmod -R 755 ./cmd/src/main/scala/com/github/diegopacheco/scalaplayground/db/slick/
cp -R ./bazel-bin/db/src/main/scala/ ./cmd/src/main/

echo "DONE..."