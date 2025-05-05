🚀 Cómo ejecutar el proyecto desde consola

Sigue estos pasos para compilar y ejecutar POOBkemon desde la terminal:

# 1. Clona el repositorio
git clone https://github.com/CatizPlum/POOBkemon.git
cd POOBkemon

# 2. Crea la carpeta de compilación si no existe
mkdir -p bin

# 3. Compila el proyecto
javac -d bin -cp src src/gui/POOBkemon.java

# 4. Ejecuta el programa
java -cp bin gui.POOBkemon
✅ Requisitos
Java JDK 11 o superior
Sistema operativo compatible con Java (Windows, Linux, macOS)
