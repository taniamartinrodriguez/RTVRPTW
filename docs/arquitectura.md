# Arquitectura

El paquete `top` contiene `TOPTW` (modelo de datos y distancias), `TOPTWReader` (lectura de instancias), `TOPTWSolution` (solución), `TOPTWGRASP` (heurística GRASP), `TOPTWRoute` (elementos de ruta), `TOPTWEvaluator` (clase cuyo método principal está comentado) y `mainTOPTW` (ejecución por lotes).

El paquete `es.ull.esit.utilities` aporta algoritmos auxiliares y manipulación de cadenas. El programa lee ficheros de `Instances/TOPTW` y usa las clases del paquete `top` para evaluar soluciones.

La clase `mainTOPTW` original ejecuta 29 instancias, cada una con tres configuraciones GRASP de 10 000 iteraciones; **no es un smoke test**.
