#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../swagger-codegen-cli-three.jar generate -i ../api-three.yaml -o ./target -l python-flask
