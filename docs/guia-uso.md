# Guía de uso

## Preparar el proyecto

1. Instalar **JDK 17 o superior** y **Maven**.
2. Abrir la carpeta del proyecto en **IntelliJ IDEA** como proyecto Maven.
3. Confirmar que el IDE usa el JDK correcto y recargar las dependencias Maven.

## Compilar y ejecutar

Desde la carpeta raíz del proyecto:

```bash
mvn clean verify
java -jar target/rtvrptw-1.0-SNAPSHOT.jar c101Recortada.txt 2 3
```

Los parámetros de la ejecución representan **archivo de instancia**, **número de iteraciones** y **tamaño máximo de la RCL**. El programa carga la instancia desde `Instances/TOPTW/`, construye una solución para cada iteración y muestra la media y la mejor puntuación. El comportamiento aleatorio implica que las puntuaciones no tienen que ser iguales en ejecuciones sucesivas.

**Atención:** sin argumentos se ejecuta el procesamiento original de 29 instancias, con tres valores de RCL y 10 000 iteraciones por configuración. No es adecuado para la primera prueba.

## Órdenes de Maven en terminal e IntelliJ

| Orden | Función | Panel Maven en IntelliJ |
| --- | --- | --- |
| `mvn -version` | Comprobar instalación | Terminal integrada |
| `mvn validate` | Validar el modelo POM | Lifecycle → validate |
| `mvn compile` | Compilar las clases | Lifecycle → compile |
| `mvn test` | Ejecutar pruebas JUnit | Lifecycle → test |
| `mvn package` | Generar el archivo JAR | Lifecycle → package |
| `mvn install` | Instalar el JAR en `.m2` | Lifecycle → install |
| `mvn deploy` | Publicar en el repositorio local de demostración | Lifecycle → deploy |
| `mvn clean` | Limpiar `target/` | Lifecycle → clean |
| `mvn verify` | Ejecutar comprobaciones y emitir informe JaCoCo | Lifecycle → verify |

**Fases encadenadas:** `package` incluye las fases anteriores del ciclo *default*; `install` incluye también `package`; `deploy` incluye las anteriores. `clean` pertenece a un ciclo independiente.

## Documentación API

Instalar Doxygen, Graphviz y los paquetes de Python indicados en `requirements-docs.txt`. Ejecutar:

```bash
doxygen Doxyfile
mkdocs build --strict
mkdocs serve
```

El servidor de vista previa suele mostrarse en `http://127.0.0.1:8000/`; el HTML final queda en `site/`. El PDF nativo de la referencia se compila desde `target/doxygen/latex/` con `make` o `make.bat` y LaTeX disponible.
