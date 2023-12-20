# README

## How to build

Build the jar:
```
mvn clean package
```

Add to shell script `jwc.sh` with this content
```
#!/bin/bash
java -cp /path/to/your/jwc-1.0-SNAPSHOT.jar com.martinsundeqvist.jwc.Main "$@"
```

Make executable
```
chmod +x jwc.sh
```