#!/bin/bash
set -euo pipefail

rm -rf ./src/app/backend
java -jar ../openapi-generator-cli.jar generate -i ../api.yaml -o ./src/app/backend -g typescript-angular

npm install
