# Práctica 4 · Gestión de versiones con Git

## Alcance y requisito de trazabilidad

Esta versión se ha construido en un repositorio Git **local independiente** a partir de los archivos de la Práctica 3. Incluye commits, ramas, fusiones, un conflicto auténtico de Git y un tag anotado. No se ha conectado con la historia remota de GitHub del estudiante. Para entregar debe reproducirse el flujo en el repositorio original conservando sus commits; **nunca usar `git push --force` para reemplazarla**.

## Cambios de código

- `feature/nueva-funcionalidad`: clase `TOPTWInstanceSummary`, opción `--resumen` en `top.mainTOPTW` y prueba `TOPTWInstanceSummaryTest`. Ejemplo: `java -jar target/rtvrptw-1.1.jar --resumen c101Recortada.txt`.
- `hotfix/correccion-sonarqube`: `TOPTWReader` comprueba cabecera, líneas y columnas, además del cierre de archivo. Incluye dos pruebas JUnit sobre una instancia truncada y una cabecera insuficiente. Es una mejora de robustez **relacionada con** los problemas de fiabilidad analizados en Práctica 3; no se ha atribuido a una regla concreta de SonarQube sin verificarla.

## Ramas y fusiones realizadas

1. Partir de `main` (línea base estable de Práctica 3).
2. Crear `feature/nueva-funcionalidad`, modificar código y registrar commit `Feat`.
3. Regresar a `main`, crear `hotfix/correccion-sonarqube`, corregir lector y registrar commit `Fix`.
4. Volver a `main` y fusionar **primero** la rama hotfix mediante `git merge --no-ff`.
5. Cambiar a feature y fusionar la nueva `main` para incorporar el hotfix.
6. Volver a `main`, fusionar la feature y borrar las ramas feature/hotfix locales.
7. Crear `test/conflicto` desde `main`; en ambas ramas, cambiar de manera diferente la primera línea de `README.md` y hacer commits independientes.
8. Intentar `git merge main` desde test: Git informa `CONFLICT (content): Merge conflict in README.md`.
9. Resolver manualmente el encabezado como `# RTVRPTW · versión 1.1 — integración verificada`, eliminar marcadores, hacer `git add` y `git commit`.
10. Incorporar la resolución a `main`, actualizar documentación y generar el tag anotado `v1.1`.

El archivo de evidencias externas al repositorio `registro_git_completo.txt` reproduce las órdenes y sus salidas, y `conflicto_antes_de_resolver.txt` conserva los marcadores para su análisis. `test/conflicto` se ha mantenido como rama local para que pueda inspeccionarse el merge de conflicto; las ramas `feature` y `hotfix` se eliminaron tal como pide el enunciado.

## Uso de GitHub Copilot: necesario en IntelliJ o VS Code

**No se ha simulado ni falsificado una conversación con GitHub Copilot.** Ejecutar estos tres prompts reales en Copilot Chat, capturar sus respuestas y compararlas con el código propuesto:

### Prompt 1: nueva funcionalidad

> Estoy trabajando en el proyecto Java RTVRPTW/TOPTW, construido con Maven. Sugiere una nueva funcionalidad sencilla y compatible con las clases `TOPTW`, `TOPTWReader` y `mainTOPTW`, sin modificar el algoritmo GRASP. Necesito clases afectadas, pseudocódigo, pruebas JUnit 5 y un comando de uso. Propongo un argumento `--resumen` para mostrar el número de puntos de interés, vehículos, límite de tiempo y puntuación total de una instancia. ¿Cómo lo implementarías de forma mínima?

### Prompt 2: corrección SonarQube

> En el proyecto Java TOPTW, el método `TOPTWReader.readProblem(String)` lee instancias con `BufferedReader`. Una entrada truncada puede llegar a `null` antes de llamar a `simplifyString` o `split`, y una línea con pocas columnas causa un error poco descriptivo. Propón un hotfix mínimo que cierre siempre los recursos, valide cabecera, líneas y columnas, preserve el comportamiento con archivos correctos e incluya dos pruebas JUnit 5. No reformules el algoritmo de optimización.

### Prompt 3: resolución de conflicto

> He provocado un conflicto Git al modificar de forma diferente la primera línea de `README.md` en `main` y `test/conflicto`. Al ejecutar `git merge main` desde `test/conflicto`, veo `<<<<<<< HEAD`, `=======` y `>>>>>>> main`. Explícame qué significa cada bloque, cómo resolverlo manualmente conservando un título unificado y qué comandos usar para concluir el merge. No me propongas `git reset --hard` ni `git push --force`.

**Importante:** incluir en el informe las respuestas realmente obtenidas y una valoración crítica: qué sugería Copilot, qué se aceptó o rechazó y por qué. Los prompts anteriores son materiales preparados, no evidencia de que Copilot se haya utilizado.

## Verificación

```bash
git status --short
git log --graph --all --decorate --oneline
git show v1.1 --no-patch
git branch -a
mvn clean verify
java -jar target/rtvrptw-1.1.jar --resumen c101Recortada.txt
```

La compilación de las clases Java y una ejecución del resumen y GRASP se comprobaron localmente con JDK 21. Las pruebas JUnit/Maven no pudieron ejecutarse en este entorno porque Maven no está instalado; deben ejecutarse desde IntelliJ antes de la entrega.

## Publicación de la práctica real

1. Trabajar **en el repositorio de GitHub de la Práctica 3** con `git clone <URL>`, `git switch main` y `git pull --ff-only origin main`.
2. Comprobar `git status` limpio y reproducir la secuencia de ramas/commits siguiendo la guía separada incluida con el ZIP.
3. Comprobar tests, fusionar y etiquetar.
4. Solo al finalizar: `git push origin main` y `git push origin v1.1`.
5. Añadir URL del repo y respuestas/capturas de Copilot al informe.
