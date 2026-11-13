# Registro de decisiones (ADR)

## ADR-001: Estructura estándar de Maven
**Decisión:** migrar los archivos Java a `src/main/java`, manteniendo `Instances/` en la raíz por compatibilidad con rutas relativas originales.

## ADR-002: Java 11 como nivel de compilación
**Decisión:** establecer `maven.compiler.release=11` para mantener compatibilidad con el proyecto original. Se recomienda ejecutar Maven y SonarScanner con un JDK moderno.

## ADR-003: Gestión de fallos del lector
**Decisión:** sustituir la terminación del proceso por excepciones descriptivas, permitiendo probar los errores.

## ADR-004: API Doxygen independiente bajo MkDocs
**Decisión:** generar Doxygen en HTML y copiarlo bajo `docs/api-doxygen/` con un script. Esto evita depender de compatibilidad de MkDoxy y enlaza una API real dentro del mismo portal.

## ADR-005: Proteger credenciales
**Decisión:** pasar tokens SonarQube por variable de entorno y excluir artefactos generados del repositorio.
