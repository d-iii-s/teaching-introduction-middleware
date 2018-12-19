#!/bin/sh

set -x
set -o errexit

rm -rf ./generated
java -jar ../swagger-codegen-cli.jar generate -i ../api.yaml -o generated -l r
rm -rf ./inventory
mkdir -p ./inventory/R
cp generated/R/*.r inventory/R/
cp generated/DESCRIPTION inventory/
echo 'exportPattern( "." )' > inventory/NAMESPACE

