# RTVRPTW · versión 1.1 — integración verificada

Proyecto Java original **ExpositoTOP**, adaptado a **Maven**, con pruebas unitarias, análisis de calidad con **SonarQube** y documentación **Doxygen + MkDocs Material + MkDoxy**.

> **Análisis disponible:** el usuario ha facilitado el panel y un vídeo de SonarQube: Quality Gate Passed, fiabilidad E (6 incidencias), mantenibilidad A (91), seguridad A (0), cobertura 5,7 % y duplicaciones 0,0 %. **Son mediciones anteriores a las últimas correcciones.** Es necesario repetir el análisis para documentar el estado nuevo.

## Nuevo en la versión 1.1

- Consulta el resumen de una instancia sin ejecutar la búsqueda GRASP: `java -cp target/classes top.mainTOPTW --resumen c101Recortada.txt` (desde la raíz del proyecto).
- El lector de instancias informa con mensajes específicos cuando faltan líneas, columnas o valores de la cabecera.
- El historial de ramas, fusiones y conflicto se ha simulado localmente para la Práctica 4; no equivale a publicación en GitHub.

## Requisitos

- Java JDK 17 o superior (recomendado JDK 21 para SonarQube actual).
- Maven 3.9+.
- Para documentación: Python 3, Doxygen, Graphviz (`dot`) y los paquetes de `requirements-docs.txt`.
- Para obtener el PDF nativo de Doxygen: una distribución de LaTeX (p. ej. MiKTeX en Windows).
- Para análisis: servidor **SonarQube** accesible y token personal.

## Abrir en IntelliJ IDEA

1. Descomprimir el ZIP y abrir IntelliJ IDEA.
2. **File > Open**: seleccionar la carpeta `Practica3_RTVRPTW` (que contiene `pom.xml`).
3. Aceptar **Load Maven Project** o pulsar **Reload All Maven Projects**.
4. Configurar **Project SDK = JDK 17 o 21**. Esperar a que Maven descargue las dependencias.
5. Abrir el panel **Maven** y ejecutar *Lifecycle > validate, compile, test, package, install*.
6. Para **deploy**, ejecutar también *Lifecycle > deploy*: el destino de demostración es `target/repositorio-maven/`; no publica en Internet.
7. Para ejecutar la aplicación, abrir `top.mainTOPTW` y crear una configuración con **Program arguments**: `c101Recortada.txt 2 3`. El **Working directory** debe ser la raíz del proyecto.

## Comandos principales (terminal en la raíz)

```bash
mvn -version
mvn validate
mvn compile
mvn test
mvn package
mvn install
mvn deploy
mvn clean verify
```

La fase `deploy` usa el repositorio local de demostración configurado en `pom.xml`; `install` instala en `~/.m2/repository`, mientras `deploy` copia el artefacto a otro repositorio gestionado aquí dentro de `target`.

Prueba rápida del algoritmo tras `package`:

```bash
java -jar target/rtvrptw-1.1.jar c101Recortada.txt 2 3
```

**Nota:** si se ejecuta la clase principal **sin argumentos**, mantiene el lote original de 29 instancias con 10 000 iteraciones por variante, que genera una salida extensa. Para la práctica, se recomienda el comando anterior.

## SonarQube (análisis real)

1. Levantar SonarQube en el equipo (por ejemplo, `http://localhost:9000`) y crear el proyecto **RTVRPTW** con clave `es.ull.esit:rtvrptw` o ajustar la propiedad `sonar.projectKey`.
2. Crear un **token** en SonarQube. **No guardarlo** en el repositorio.
3. En la terminal, asignar `SONAR_TOKEN` y ejecutar el scanner desde la raíz:

   **Windows PowerShell**:
   ```powershell
   $env:SONAR_TOKEN = "TOKEN_PERSONAL"
   mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356:sonar -Dsonar.host.url=http://localhost:9000
   ```

   **macOS / Linux**:
   ```bash
   export SONAR_TOKEN="TOKEN_PERSONAL"
   mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356:sonar -Dsonar.host.url=http://localhost:9000
   ```

4. En SonarQube, revisar *Issues*, *Maintainability*, *Reliability*, *Security* y *Coverage*. Comparar los nuevos resultados con los ya documentados en `informe-tecnico.md` y `docs/calidad.md`.
5. Repetir el análisis **después de las correcciones** y conservar la captura de la nueva medición. Las cifras anteriores no prueban que las seis incidencias estén resueltas.

## Generar documentación

```bash
python -m pip install -r requirements-docs.txt
doxygen Doxyfile
mkdocs build --strict
mkdocs serve
```

- Doxygen: `target/doxygen/html/index.html`, XML y grafos generados con Graphviz.
- Portal MkDocs: `site/index.html`. MkDoxy incorpora la referencia Java.
- PDF Doxygen: desde `target/doxygen/latex/` ejecutar `make` en macOS/Linux o `make.bat` en Windows (requiere LaTeX); el resultado habitual es `refman.pdf`. Copiarlo a `documentacion-tecnica.pdf` antes de subir a GitHub.

El fichero `documentacion-tecnica.pdf` incluido es una **edición de lectura preparada a partir de la documentación Markdown**, útil para revisión. **Debe sustituirse por el PDF exportado por Doxygen** para ajustarse literalmente al apartado 9.4 del enunciado.

## Práctica 4: Git y ramas

Se ha realizado un **historial demostrativo local** de la Práctica 4, que puede consultarse con `git log --graph --oneline --decorate --all`. La base es el ZIP disponible de la Práctica 3; esta historia no incorpora los commits de tu repositorio personal de GitHub y **no debe subirse sobre la rama remota con `--force`**. El documento `docs/practica4-control-versiones.md` explica cómo reproducir el flujo sobre el repositorio original.

## Estructura

```text
Practica3_RTVRPTW/
├── pom.xml                       Configuración Maven y plugins
├── Doxyfile                      Configuración de Doxygen/Graphviz
├── mkdocs.yml                    Portal Material y API MkDoxy
├── requirements-docs.txt         Dependencias Python de MkDocs
├── Instances/TOPTW/              Instancias originales
├── src/main/java/                11 clases originales con correcciones puntuales
├── src/test/java/                Pruebas JUnit 5
├── docs/                         Manual, arquitectura, calidad y decisiones
├── documentacion-tecnica.pdf     PDF de consulta (sustituir con salida Doxygen)
└── informe-tecnico.pdf           Informe base para completar con resultados reales
```

Para la entrega también hay que subir el repositorio a GitHub y **añadir su enlace en el informe**, así como las capturas realizadas en el equipo local.
