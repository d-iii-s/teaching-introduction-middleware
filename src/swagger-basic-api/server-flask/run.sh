#!/bin/bash
set -euo pipefail

python -m venv .venv
. .venv/bin/activate

pip install -r target/requirements.txt
pip install connexion[swagger-ui]

PYTHONPATH=.:target python -m swagger_server
