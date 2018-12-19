# Swagger-based Angular client

Whenever you change the API definition (and also before you run the example
for the first time) you need to run the following script:

```shell
./on-api-update.sh
```

## Warning

Some versions of NPM do not handle well when being executed on network drives.
It is recommended to run always on local drive (e.g. `/tmp`).

## Running the example

```shell
npm install
npm run start
```

and open your browser at http://localhost:4200/
