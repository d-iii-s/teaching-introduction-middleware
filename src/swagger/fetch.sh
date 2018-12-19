#!/bin/sh

VERSION="2.4.0"
PROJECT="swagger-codegen-cli"
BASE_URL="http://central.maven.org/maven2/io/swagger"

wget "$BASE_URL/$PROJECT/$VERSION/$PROJECT-$VERSION.jar" -O "$PROJECT.jar" 
