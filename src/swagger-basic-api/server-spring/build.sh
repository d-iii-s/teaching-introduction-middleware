#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../swagger-codegen-cli-two.jar generate -i ../api-two.yaml -o ./target -l spring

cd target

cp -a ../pom.xml .
cp -a ../src .

mvn package
