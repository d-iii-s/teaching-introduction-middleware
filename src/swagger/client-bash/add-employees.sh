#!/bin/sh

get_field() {
    POS=`echo ",$CSV_HEADER," | sed "s#\(.*,\)$1,.*#\1#" | tr -c -d ',' | wc -c`
    echo "$2" | cut '-d,' -f "$POS"
}

RUN=echo
RUN=

read CSV_HEADER
while read LINE; do
    FIRST_NAME=`get_field first "$LINE"`
    LAST_NAME=`get_field last "$LINE"`
    DEPARTMENT=`get_field dept "$LINE"`
    
    LOGIN=`echo "$FIRST_NAME.$LAST_NAME" | tr 'A-Z' 'a-z'`

    $RUN ./client.sh createUser \
        "firstname==$FIRST_NAME" \
        "lastname==$LAST_NAME" \
        "department==$DEPARTMENT" \
        "email==$LOGIN@example.com" \
        "homepage==http://www.example.com/people/$LOGIN"
done
