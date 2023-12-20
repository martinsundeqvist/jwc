# README

## How to build
<!---
TODO: Wrap all of the below in a setup script that works on UNIX systems
-->
Build the jar:
```sh
mvn clean package
```

Add to shell script `jwc.sh` with this content
```sh
#!/bin/bash
java -cp /path/to/your/jwc-1.0-SNAPSHOT.jar com.martinsundeqvist.jwc.Main "$@"
```

Make executable
```sh
chmod +x jwc.sh
```