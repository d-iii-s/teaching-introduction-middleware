#!/bin/sh

set -x
set -o errexit

rm -rf ./generated/
mkdir -p ./generated/
java -jar ../swagger-codegen-cli.jar generate -i ../api.yaml -o generated -l spring
rm -rf ./src/gen/
mkdir -p ./src/gen/
cp -R ./generated/src/main/* ./src/gen/
rm -f ./src/gen/java/io/swagger/api/Application.java
for i in src/gen/java/io/swagger/api/UserApiController.java src/gen/java/io/swagger/api/UsersApiController.java; do
    mv "$i" "$i.template"
done
