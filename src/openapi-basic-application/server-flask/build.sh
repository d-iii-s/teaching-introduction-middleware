#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../openapi-generator-cli.jar generate -i ../api.yaml -o ./target -g python-flask -c config.json
