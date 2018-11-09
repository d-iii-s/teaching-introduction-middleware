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
# BUGFIX: Even though submodule clones are large, do not attempt shallow submodule cloning.
git clone -b v1.16.x --depth 1 http://github.com/grpc/grpc .
git submodule update --init

echo --- Building ...
make -j 8 prefix="${PREFIX:?}"

echo --- Installing ...
make prefix="${PREFIX:?}" install
# BUGFIX: Standard installation installs incorrect symbolic links that need to be renamed.
for BASE in grpc++ grpc++_cronet grpc++_error_details grpc++_reflection grpc++_unsecure
do
    mv --force "${PREFIX:?}/lib/lib${BASE:?}.so.6" "${PREFIX:?}/lib/lib${BASE:?}.so.1" || true
done

echo --- Cleaning up ...
popd
rm --recursive --force "${TMP_DIR:?}"

echo === Done.
