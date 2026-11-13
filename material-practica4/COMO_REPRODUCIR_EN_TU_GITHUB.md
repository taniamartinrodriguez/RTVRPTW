# Práctica 4: reproducir el flujo en TU repositorio de GitHub

La demostración incluida en el ZIP se ha construido con Git **en local** a partir de los archivos de Práctica 3. Tiene historial y tag propios, diferentes del historial del repositorio que entregaste en la Práctica 3. **No hagas `git push --force` desde esa demostración, porque podrías sobrescribir el historial original.**

## Antes de empezar

1. Abre IntelliJ IDEA con **tu clon real** del repositorio de la Práctica 3. Si no tienes un clon, usa `git clone URL_DE_TU_REPOSITORIO` y abre esa carpeta.
2. Asegúrate de conservar los cambios de Práctica 3 en el remoto. En terminal integrada:

```bash
git switch main
git pull --ff-only origin main
git status
```

Si tu rama se llama `master`, usa `master` en los pasos en lugar de `main`. No continúes si hay cambios locales sin guardar; haz commit o guardalos primero.

## 1. Rama de funcionalidad

```bash
git switch -c feature/nueva-funcionalidad
```

En la carpeta final del proyecto que te he entregado, busca los archivos **nuevos/modificados de la feature**: `src/main/java/top/TOPTWInstanceSummary.java`, `src/main/java/top/mainTOPTW.java`, `src/test/java/top/TOPTWInstanceSummaryTest.java`. Copia sus contenidos en **tu clon real** (no copies `.git`). Como alternativa, prueba:

```bash
git apply --check /RUTA_AL_ARCHIVO/p4_feature.patch
git apply /RUTA_AL_ARCHIVO/p4_feature.patch
```

Si `git apply --check` falla, compara los archivos de tu clon original con los incluidos en el ZIP y aplica **solo** esos cambios. Después:

```bash
git add src/main/java/top/mainTOPTW.java src/main/java/top/TOPTWInstanceSummary.java src/test/java/top/TOPTWInstanceSummaryTest.java
git commit -m "Feat: mostrar resumen de instancia sin ejecutar GRASP"
```

Ejecuta Copilot Chat con el prompt 1 de `docs/practica4-control-versiones.md` y guarda la respuesta y una captura para el informe.

## 2. Rama del hotfix, nacida desde main

```bash
git switch main
git switch -c hotfix/correccion-sonarqube
```

Aplica el parche `p4_hotfix.patch` con `git apply --check` primero (o copia únicamente `TOPTWReader.java` y los dos tests nuevos en `TOPTWReaderTest.java`, evitando perder pruebas anteriores):

```bash
git apply --check /RUTA_AL_ARCHIVO/p4_hotfix.patch
git apply /RUTA_AL_ARCHIVO/p4_hotfix.patch
git add src/main/java/top/TOPTWReader.java src/test/java/top/TOPTWReaderTest.java
git commit -m "Fix: validar ficheros TOPTW incompletos o mal formados"
```

Ejecuta Copilot Chat con el prompt 2; registra qué diferencia existe entre su solución y el código final.

## 3. Merge en el orden del profesor

```bash
git switch main
git merge --no-ff hotfix/correccion-sonarqube -m "Merge hotfix en main"
git switch feature/nueva-funcionalidad
git merge --no-ff main -m "Actualiza feature con main y hotfix"
git switch main
git merge --no-ff feature/nueva-funcionalidad -m "Merge de nueva funcionalidad en main"
git branch -d feature/nueva-funcionalidad
git branch -d hotfix/correccion-sonarqube
```

Si aparece un conflicto no previsto, resuélvelo y registra los comandos reales utilizados.

## 4. Conflicto intencional

```bash
git switch -c test/conflicto
git switch main
```

En `main`, cambia la **primera línea** de `README.md` a `# RTVRPTW - versión estable v1.1`. Luego:

```bash
git add README.md
git commit -m "Docs: actualiza encabezado de README en main"
git switch test/conflicto
```

En `test/conflicto`, cambia la primera línea de `README.md` a `# RTVRPTW - pruebas de integración`. Luego:

```bash
git add README.md
git commit -m "Test: modifica README para simular conflicto"
git merge main
```

Git mostrará `CONFLICT (content): Merge conflict in README.md`. Antes de resolver, guarda captura o la salida de `git status`. Ejecuta el prompt 3 en Copilot Chat. Abre `README.md`, elimina los marcadores `<<<<<<<`, `=======` y `>>>>>>>`, y deja un único encabezado que tenga sentido, por ejemplo `# RTVRPTW - versión 1.1 (integración verificada)`. Entonces:

```bash
git add README.md
git commit -m "Resolve: resuelve conflicto de integración en README"
git switch main
git merge --no-ff test/conflicto -m "Merge de resolución de conflicto en main"
```

El enunciado no exige borrar `test/conflicto`: puedes mantenerla para enseñar el historial.

## 5. Comprobación, versión y subida

```bash
mvn clean verify
java -jar target/rtvrptw-1.1.jar --resumen c101Recortada.txt
git status
git log --graph --oneline --all --decorate
git tag -a v1.1 -m "RTVRPTW versión 1.1"
git push origin main
git push origin v1.1
```

**Importante:** Si el POM de tu repositorio aún dice `<version>1.0-SNAPSHOT</version>`, actualiza a `1.1` y haz commit **antes** de crear la etiqueta; si ya está creado el tag `v1.1`, verifica su contenido antes de actuar, no lo sobrescribas.

## 6. Completar informe

- Sustituir `URL pendiente` por el enlace real de GitHub.
- Incluir respuestas **auténticas** y capturas de las tres conversaciones con Copilot; nunca inventarlas.
- Añadir capturas de `git log --graph`, `git status` durante el conflicto y `git tag -n`.
- Verificar la compilación y los tests JUnit en IntelliJ.
