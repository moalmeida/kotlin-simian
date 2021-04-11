# KOTLIN-SIMIAN

Example Simian DNA

## Installation

### Build environment requirements

In order to build Kotlin distribution you need to have:

- JDK 11
- Kotlin Compiler


### install dependencies (locally) and build the app

```bash
$ ./gradlew clean build
```

## Testing 

### run unit test with coverage

```bash
$ ./graglew test
```

## Running the app

### 

```bash
$ ./graglew run
```

The app will be available on the host **localhost:8080**

## Using published app

to call via command line

### Get Stats

```bash
REQUEST

$ curl --location --request GET http://localhost:8080/stats

RESPONSE

{
  "count_simian_dna": 1,
  "count_human_dna": 0,
  "ratio":"0.0"
}

```

### Verify Is Simian

```bash
REQUEST 

$ curl --location --request POST http://localhost:8080/simian \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna": [
        "CTGAGA",
        "CTATGC",
        "TATTGT",
        "AGAGGG",
        "CCCCTA",
        "TCACTG"
    ]
}'

RESPONSE

{
  "is_simian": true
}

```



