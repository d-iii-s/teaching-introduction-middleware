#!/bin/sh

read_user_ids() {
    ./client.sh --silent readUsers | json_reformat | sed -n 's#.*"id": \([0-9]*\),\?$#\1#p'
}

read_user_as_yaml() {
    ./client.sh --silent readUser id=$1 | json2yaml
}

yaml_to_m4_defines() {
    sed -n 's#^\([a-z]\+\): \(.*\)#define(`\1__", `\2")#p' | tr '"' "'"
}

mkdir -p prints/

for USER_ID in `read_user_ids`; do
    echo "Preparing $USER_ID ..."
    (
        (
            echo "today: `date`"
            read_user_as_yaml "$USER_ID"
        ) | yaml_to_m4_defines
        echo "------"
        cat template.m4
    ) | m4 | sed '1,/^--*$/d' >prints/$USER_ID.txt
done
