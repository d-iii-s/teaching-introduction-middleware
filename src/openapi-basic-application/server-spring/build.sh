#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../openapi-generator-cli.jar generate -i ../api.yaml -o ./target -g spring

cd target

cp -a ../src .

mvn package
