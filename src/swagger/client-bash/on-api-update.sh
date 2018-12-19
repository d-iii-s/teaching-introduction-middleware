#!/bin/sh

set -x
set -o errexit

rm -rf ./generated
java -jar ../swagger-codegen-cli.jar generate -i ../api.yaml -o generated -l bash
sed 's#^host=""$#host="http://localhost:8080"#' <generated/client.sh >client.sh
chmod +x client.sh
