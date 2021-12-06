#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../openapi-generator-cli.jar generate -i ../api.yaml -o ./target -g bash -c config.json

chmod a+x target/client.sh
