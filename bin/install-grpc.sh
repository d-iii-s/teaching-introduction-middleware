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
git clone -b v1.7.x http://github.com/grpc/grpc .
git submodule update --init

echo --- Building ...
# BUGFIX: Specifying some paths explicitly helps bypass possible system protobuf installations.
make -j 8 prefix="${PREFIX:?}" EXTRA_CFLAGS="-I${PREFIX:?}/include" EXTRA_CXXFLAGS="-I${PREFIX:?}/include" EXTRA_CPPFLAGS="-I${PREFIX:?}/include" EXTRA_LDFLAGS="-L${PREFIX:?}/lib"

echo --- Installing ...
# BUGFIX: Specifying some paths explicitly helps bypass possible system protobuf installations.
make prefix="${PREFIX:?}" EXTRA_CFLAGS="-I${PREFIX:?}/include" EXTRA_CXXFLAGS="-I${PREFIX:?}/include" EXTRA_CPPFLAGS="-I${PREFIX:?}/include" EXTRA_LDFLAGS="-L${PREFIX:?}/lib" install
# BUGFIX: Standard installation installs incorrect symbolic links that need to be renamed.
for BASE in grpc++ grpc++_cronet grpc++_error_details grpc++_reflection grpc++_unsecure
do
    mv --force "${PREFIX:?}/lib/lib${BASE:?}.so.5" "${PREFIX:?}/lib/lib${BASE:?}.so.1" || true
done

echo --- Cleaning up ...
popd
rm --recursive --force "${TMP_DIR:?}"

echo === Done.
