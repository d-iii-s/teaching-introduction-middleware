# Swagger-based bash client

Whenever you change the API definition (and also before you run the example
for the first time) you need to run the following script:

```shell
./on-api-update.sh
```

## Running the example

```shell
./client.sh --help
./client.sh --silent readUsers | json_reformat
./add-employees.sh <new-employees.csv
./client.sh --silent readUsers | json_reformat
```
