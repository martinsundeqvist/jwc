# README

## Introduction
This is a Java implementation of the [wc](https://www.gnu.org/software/coreutils/manual/html_node/wc-invocation.html#wc-invocation) GNU coreutils utility. It is capable of counting 
- bytes
- characters
- lines
- words
- maximum line width

Of any number of input files.

## Build
Build the jar:
```sh
mvn clean package
```

Now go to `target/jwc-<version>.jar`.

## Example
To calculate the number of bytes in a file `example-file.txt`:

```
java -jar target/jwc-<version.jar> --bytes example-file.txt
```