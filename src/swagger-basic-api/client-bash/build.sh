#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../swagger-codegen-cli-two.jar generate -i ../api-two.yaml -o ./target -l bash -c config.json

chmod a+x target/client.sh
