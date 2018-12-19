#!/bin/sh

set -x
set -o errexit

rm -rf ./generated/
mkdir -p ./generated/
java -jar ../swagger-codegen-cli.jar generate -i ../api.yaml -o generated -l typescript-angular
rm -rf src/app/api
mkdir -p src/app/api
cp -R generated/api generated/model generated/*.ts src/app/api/
