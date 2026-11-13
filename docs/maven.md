# Maven

Maven gestiona la compilación desde el archivo `pom.xml`, siguiendo un ciclo de vida. Su estructura convencional utiliza `src/main/java` para código y `src/test/java` para pruebas.

| Fase | Comando | Resultado esperado |
| --- | --- | --- |
| Validación | `mvn validate` | Comprueba estructura y POM |
| Compilación | `mvn compile` | Crea clases en target/classes |
| Pruebas | `mvn test` | Ejecuta JUnit |
| Empaquetado | `mvn package` | Crea un JAR |
| Instalación | `mvn install` | Copia JAR al repositorio local |
| Despliegue | `mvn deploy` | Necesita distribución remota configurada |

Los plugins configurados incluyen Compiler (Java 11), Surefire (JUnit 5), JAR (manifiesto), JaCoCo (cobertura), Sonar Maven Scanner y Deploy. Las fases se lanzan desde el IDE mediante Maven Goals o desde un terminal en la carpeta del `pom.xml`.

**Importante:** `deploy` a un repositorio remoto requiere `distributionManagement` y credenciales; no se configura ningún repositorio ficticio.
