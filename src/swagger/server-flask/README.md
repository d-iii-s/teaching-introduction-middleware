# Swagger-based Python-with-Flask server

Whenever you change the API definition (and also before you run the example
for the first time) you need to run the following script:

```shell
./on-api-update.sh
```

## Running the example

```shell
virtualenv  -p python3 flask-env
source flask-env/bin/activate
pip install -r requirements.txt 
./main.py 
deactivate 
```
