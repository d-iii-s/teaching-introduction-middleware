#!/bin/bash -e

# Check that the environment script has been sourced.

if [[ ! -v PREFIX ]]
then
    echo !!! Source the environment script first.
    exit 1
fi

# Proceed with prefix as configured.

TMP_DIR="$(mktemp --directory)"
echo === Building in ${TMP_DIR:?} ...
pushd "${TMP_DIR:?}"

echo --- Cloning source repository ...
git clone -b 3.6.x --depth 1 http://github.com/protocolbuffers/protobuf .

echo --- Configuring ...
./autogen.sh
./configure --prefix="${PREFIX:?}"

echo --- Building ...
make -j 8

echo --- Installing ...
make install

echo --- Cleaning up ...
popd
rm --recursive --force "${TMP_DIR:?}"

echo === Done.
