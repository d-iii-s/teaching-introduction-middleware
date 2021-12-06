#!/bin/bash
set -euo pipefail

# Redirect input from file.
exec 0< "populate.csv"

read CSV_HEADER

GET_FIELD () {
    local FIELD=${1}
    local LINE=${2}
    local POS=$(echo ",${CSV_HEADER}," | sed -re "s/(.*,)${FIELD},.*/\1/" | tr -c -d ',' | wc -c)
    echo "${LINE}" | cut -d , -f ${POS}
}

while read CSV_LINE
do
    FIRST=$(GET_FIELD first "${CSV_LINE}")
    LAST=$(GET_FIELD last "${CSV_LINE}")
    DEPT=$(GET_FIELD dept "${CSV_LINE}")

    LOGIN=$(echo "${FIRST}.${LAST}" | tr 'A-Z' 'a-z')

    target/client.sh createUser \
        "firstname==${FIRST}" \
        "lastname==${LAST}" \
        "department==${DEPT}" \
        "mail==${LOGIN}@example.com" \
        "homepage==http://www.example.com/~${LOGIN}"
done
