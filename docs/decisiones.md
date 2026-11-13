# Registro breve de decisiones técnicas

Este registro resume las decisiones de adaptación para la práctica, sin introducir nuevas funcionalidades ajenas al proyecto original.

## D-01 · Mantener paquetes y heurística existentes

**Decisión:** trasladar las clases existentes a `src/main/java` sin rediseñar el algoritmo GRASP. **Motivo:** el objetivo de la actividad es evaluar herramientas de calidad y documentación, no reimplementar la lógica del recomendador. **Consecuencia:** persisten algunas oportunidades de refactorización que deben analizarse con SonarQube.

## D-02 · Hacer explícitos los errores de entrada

**Decisión:** reemplazar `System.exit(0)` en el lector por `UncheckedIOException` y cerrar recursos automáticamente. **Motivo:** permitir que IDE, pruebas y ejecución por lotes distingan un archivo inválido de una ejecución correcta.

## D-03 · Construcción reproducible y pruebas mínimas

**Decisión:** declarar Java 17, JUnit 5 y plugins Maven con versión fija; habilitar JaCoCo para que SonarQube pueda importar cobertura. **Motivo:** facilitar ejecuciones equivalentes por terminal e IntelliJ.

## D-04 · Unificar documentación

**Decisión:** usar Doxygen para comentarios y diagramas, y MkDocs Material con MkDoxy para publicar manuales Markdown y referencia de código. **Motivo:** cumplir el enfoque Docs-as-Code. Los textos explicativos se han elaborado con asistencia de un LLM y revisado a partir del código original; **no** sustituyen la medición real de SonarQube.

## D-05 · Demostrar deploy sin publicar en un servidor ajeno

**Decisión:** configurar en Maven un repositorio de archivos local en `target/repositorio-maven/`. **Motivo:** probar la fase de despliegue con un destino verificable sin requerir credenciales externas. **Límite:** no es un despliegue de la aplicación a producción ni equivale a su publicación en GitHub.
