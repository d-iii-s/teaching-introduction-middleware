#!/bin/bash
set -euo pipefail

rm -rf ./target
java -jar ../openapi-generator-cli.jar generate -i ../api.yaml -o ./target -g python-flask -c config.json

python -m venv .venv
. .venv/bin/activate

pip install -r target/requirements.txt -c requirements.txt
pip install connexion[swagger-ui]
