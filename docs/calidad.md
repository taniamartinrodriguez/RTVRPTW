# Calidad y SonarQube

Para analizar el código con SonarQube, primero inicia un servidor en `http://localhost:9000`, crea un proyecto con la clave `rtvrptw-practica3` y genera un token. Nunca subas el token a GitHub.

```bash
mvn clean verify
# Linux/macOS: export SONAR_TOKEN=token_generado
# PowerShell: $env:SONAR_TOKEN="token_generado"
mvn org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356:sonar
```

Los problemas observados por inspección son: terminación prematura por `System.exit(0)` en el lector original (corregido), creación recurrente de `Random` (mejorado), método `TOPTWEvaluator.evaluate` inactivo y excesiva escritura en consola dentro de GRASP (pendiente).

**No son métricas verificadas de SonarQube.** Se deben registrar capturas del panel, bugs, vulnerabilidades, code smells, duplicación, deuda técnica, cobertura y quality gate después de ejecutar el análisis.
