#!/bin/sh

set -x
set -o errexit

rm -rf ./generated/
mkdir -p ./generated/
java -jar ../swagger-codegen-cli.jar generate -i ../api.yaml -o ./generated -l python-flask
rm -rf ./swagger_server/
mv ./generated/swagger_server/ .

patch -p0 <<EOF_PATCH
--- swagger_server/__main__.py
+++ swagger_server/__main__.py
@@ -3,2 +3,3 @@
 import connexion
+from flask_cors import CORS, cross_origin
 
@@ -9,2 +10,3 @@
     app = connexion.App(__name__, specification_dir='./swagger/')
+    CORS(app.app)
     app.app.json_encoder = encoder.JSONEncoder
EOF_PATCH

echo 'Flask_Cors >= 3.0.0' >>./generated/requirements.txt
mv generated/requirements.txt requirements.txt

# https://github.com/swagger-api/swagger-codegen/issues/8921
if python3 --version | grep -q '^Python 3.[789]'; then
    patch -p0 <<EOF_PATCH_PYTHON_37
--- swagger_server/util.py
+++ swagger_server/util.py
@@ -23,10 +23,10 @@
         return deserialize_date(data)
     elif klass == datetime.datetime:
         return deserialize_datetime(data)
-    elif type(klass) == typing.GenericMeta:
-        if klass.__extra__ == list:
+    elif hasattr(klass, '__origin__'):
+        if klass.__origin__ == list:
             return _deserialize_list(data, klass.__args__[0])
-        if klass.__extra__ == dict:
+        if klass.__origin__ == dict:
             return _deserialize_dict(data, klass.__args__[1])
     else:
         return deserialize_model(data, klass)

EOF_PATCH_PYTHON_37
fi
