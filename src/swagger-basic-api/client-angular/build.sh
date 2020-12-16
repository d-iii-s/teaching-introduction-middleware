#!/bin/bash
set -euo pipefail

rm -rf ./src/app/backend
java -jar ../swagger-codegen-cli-two.jar generate -i ../api-two.yaml -o ./src/app/backend -l typescript-angular

npm install
