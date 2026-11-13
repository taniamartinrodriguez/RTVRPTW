# Instalación y uso

## Requisitos

Java JDK 21 para un entorno moderno de análisis SonarQube (el código está compilado para Java 11), Maven, Python 3, Graphviz, Doxygen y una instancia de SonarQube.

## Compilación y pruebas

```bash
mvn -version
mvn clean verify
mvn package
```

## Ejecución

Desde la raíz del proyecto:

```bash
java -jar target/rtvrptw-1.0.0-SNAPSHOT.jar
```

**Atención:** ejecuta muchas iteraciones y produce abundante salida; por seguridad comprueba primero `mvn test`. El directorio `Instances/` debe encontrarse en la raíz de ejecución.

## Documentación

```bash
python -m pip install mkdocs mkdocs-material
doxygen Doxyfile
mkdocs build
mkdocs serve
```

Los HTML de Doxygen se generan en `build/doxygen/html/index.html`. Para mantener un único portal estático sin depender de un plugin adicional, usa el script `scripts/integrar_doxygen.py` antes de `mkdocs build`.
