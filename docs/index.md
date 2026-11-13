# RTVRPTW · Documentación técnica

**Recomendador Turístico con Ventanas de Tiempo** — proyecto original ExpositoTOP, adaptado para la Práctica 3 de Laboratorio de Desarrollo y Herramientas (ULL).

## Objetivo

El sistema selecciona puntos de interés turísticos que pueden visitarse mediante rutas sujetas a **ventanas de tiempo**, tiempos de servicio y disponibilidad de vehículos o rutas. La calidad de una solución se expresa mediante las puntuaciones acumuladas de los puntos visitados.

## Componentes principales

- **Lector de instancias:** `TOPTWReader` transforma los ficheros de `Instances/TOPTW/` en objetos `TOPTW`.
- **Modelo y solución:** `TOPTW` conserva datos y distancias; `TOPTWSolution` representa las rutas.
- **Algoritmo:** `TOPTWGRASP` construye soluciones heurísticas mediante una lista restringida de candidatos (RCL).
- **Evaluación:** `TOPTWEvaluator` actualiza la puntuación obtenida.
- **Utilidades:** `BellmanFord`, `PowerSet`, `Pair` y `ExpositoUtilities`.

## Contenido de este portal

La **guía de uso** explica compilación y ejecución; **arquitectura** expone las clases y sus relaciones; **calidad del código** prepara el análisis SonarQube; **decisiones técnicas** documenta las adaptaciones introducidas. La **referencia de clases** se genera automáticamente con MkDoxy a partir de los comentarios Java/Doxygen.

Este portal aplica *Docs-as-Code*: documentación Markdown versionada junto al programa y regenerada mediante comandos reproducibles.
