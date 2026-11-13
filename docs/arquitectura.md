# Arquitectura del sistema

## Vista general

El código se organiza en dos paquetes principales y un paquete de tipos auxiliares:

- **`top`**: núcleo TOPTW, algoritmo, lector, solución y punto de entrada.
- **`es.ull.esit.utilities`**: funciones auxiliares de grafos, matemáticas y generación de subconjuntos.
- **`es.ull.esit.utils`**: clase genérica `Pair`.

## Flujo de procesamiento

1. La clase `mainTOPTW` selecciona la instancia y el número de iteraciones.
2. `TOPTWReader.readProblem()` lee los datos del archivo y genera un `TOPTW` con matriz de distancias.
3. `TOPTWSolution` crea una estructura para registrar rutas, sucesores y predecesores.
4. `TOPTWGRASP` construye soluciones con una lista restringida de candidatos que cumplen las restricciones evaluadas por el algoritmo.
5. `TOPTWSolution.evaluateFitness()` suma la puntuación de los puntos visitados; `TOPTWEvaluator` puede almacenar ese valor como función objetivo.
6. El programa muestra la solución y un resumen de las puntuaciones de las iteraciones.

## Relaciones relevantes

| Clase | Responsabilidad | Colaboración |
| --- | --- | --- |
| `TOPTW` | Mantener coordenadas, ventanas temporales, puntuaciones y distancias | `TOPTWReader`, `TOPTWSolution` |
| `TOPTWReader` | Analizar el formato de entrada | `TOPTW`, `ExpositoUtilities` |
| `TOPTWSolution` | Mantener rutas y evaluación de su puntuación | `TOPTW`, `TOPTWGRASP` |
| `TOPTWGRASP` | Heurística aleatorizada | `TOPTWSolution` |
| `TOPTWEvaluator` | Guardar la evaluación de la solución | `TOPTWSolution` |
| `BellmanFord`, `PowerSet`, `Pair` | Apoyo reutilizable | Código de utilidad |

Los **diagramas estructurales** se generan en la referencia Doxygen con Graphviz (`HAVE_DOT = YES`). No se añaden patrones ni capas nuevas porque la práctica se centra en calidad, compilación y documentación del proyecto existente.
