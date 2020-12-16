#!/bin/bash
set -euo pipefail

GET_CLI () {
    local PRJ=${1}
    local VER=${2}
    local SUF=${3}
    wget "https://repo1.maven.org/maven2/${PRJ}/${VER}/swagger-codegen-cli-${VER}.jar" -O "swagger-codegen-cli-${SUF}.jar" 
}

GET_CLI "io/swagger/swagger-codegen-cli" "2.4.17" "two"
GET_CLI "io/swagger/codegen/v3/swagger-codegen-cli" "3.0.23" "three"
