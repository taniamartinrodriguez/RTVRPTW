"""Copia el HTML generado por Doxygen a MkDocs preservando referencias relativas."""
from pathlib import Path
import shutil
root = Path(__file__).resolve().parents[1]
src = root / "build/doxygen/html"
dst = root / "docs/api-doxygen"
if not (src / "index.html").exists():
    raise SystemExit("Primero ejecuta: doxygen Doxyfile")
if dst.exists(): shutil.rmtree(dst)
shutil.copytree(src, dst)
print(f"API integrada en {dst}")
