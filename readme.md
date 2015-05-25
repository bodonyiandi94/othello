# Othello

A projekt egy Swing alapú Othello játék implementációját tartalmazza, amely képes 
egy ranglistaadatbázis nyilvántartására és a klasszikustól eltérő táblaméreteken történő 
játszmák kezelésére is.

## Fordítás
mvn compile

## Tisztítás
mvn clean

## Futtatás

### Előállított JAR fájl segítségével
java -jar target/beadando-1.0-jar-with-dependencies

### Mavenen keresztül
mvn exec:java -Dexec.mainClass=hu.unideb.inf.it.controller.Main