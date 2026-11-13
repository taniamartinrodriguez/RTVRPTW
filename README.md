# RTVRPTW — Práctica 3

Proyecto `ExpositoTOP` adaptado a Maven, con pruebas JUnit, configuración de SonarQube, Doxygen/Graphviz y portal MkDocs Material.

## Comandos

```bash
mvn -version
mvn clean verify
mvn package
mvn install
mvn org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356:sonar
python -m pip install -r requirements-docs.txt
doxygen Doxyfile
python scripts/integrar_doxygen.py
mkdocs build
mkdocs serve
```

`mvn deploy` requiere un repositorio de artefactos remoto y configuración de credenciales; GitHub Pages publica la documentación, no sustituye este repositorio Maven.

El JAR usa `top.mainTOPTW`, que ejecuta por defecto numerosos experimentos. Requiere el directorio `Instances/` en la carpeta de trabajo.

Consulta `docs/` y el informe PDF adjunto para registrar las pruebas reales, las capturas del IDE y las métricas de SonarQube.
