#!/bin/bash
set -euo pipefail

python -m venv .venv
. .venv/bin/activate

PYTHONPATH=.:target python -m openapi_server
