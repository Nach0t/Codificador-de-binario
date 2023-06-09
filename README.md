### Esteganografía LSB - Ocultar y Leer Mensajes en Imágenes
Este proyecto consiste en un programa en Java que utiliza la técnica de esteganografía LSB (Least Significant Bit) para ocultar mensajes dentro de imágenes en formato PNG. Permite ocultar un mensaje en los píxeles modificados de la imagen y luego leer y obtener ese mensaje oculto.

### Cómo funciona:
1. Este programa tiene dos partes principales: ocultar el mensaje en una imagen (encode) y leer el mensaje oculto en una imagen (decode).

### Requisitos:
1. Tener instalado el Java JDK 8 o versiones superiores.
2. Usar una imagen.png acorde al mensaje a ocultar. (Este codigo ya porpociona 2 imagenes)
3. Llamar una imagen input.png y la otra out.png.

### Ocultar el mensaje en una imagen:
1. Se carga la imagen PNG de entrada.
2. El mensaje a ocultar se convierte en bytes y se altera su frecuencia levemente, haciéndolo imperceptible para el ojo humano.
3. Se verifica si el tamaño del mensaje es compatible con el tamaño de la imagen para asegurar que se pueda ocultar.
4. Se oculta el tamaño del mensaje en los primeros 32 bits de la imagen (4 bytes).
5. Se altera el bit menos significativo de cada byte de la imagen para codificar el mensaje.

### Leer el mensaje oculto en una imagen:
1. Se carga la imagen PNG con el mensaje oculto.
2. Se extrae el tamaño del mensaje oculto de los primeros 32 bits de la imagen.
3. Se recuperan los bytes del mensaje oculto de los píxeles de la imagen.
4. Se convierten los bytes del mensaje en una cadena de texto.

### Ejecutar el programa:

# Puedes ejecutar el programa en la línea de comandos utilizando los siguientes comandos:

## En CMD (Windows):
1. Abre una terminal CMD.
2. Navega hasta la ubicación del archivo. (Utiliza el comando "cd /ruta/del/archivo/.../").
3. Compila el código fuente ejecutando "javac Main.java".
4. Verifica que se hayan generado los archivos ".class" ejecutando "dir" para listar el directorio.
5. Ejecuta el programa para ocultar un mensaje en una imagen con el comando "java Main encode 'mensaje a ocultar' input.png out.png".
6. Ejecuta el programa para leer y obtener el mensaje oculto de una imagen con el comando "java Main decode out.png".

## En Terminal (Linux / macOS / Unix):
1. Navega hasta la ubicación del archivo utilizando el comando "cd /ruta/del/archivo/.../".
2. Una vez que estés en la ubicación del archivo, compila el código fuente con el comando "javac Main.java".
3. Verifica que se hayan generado los archivos ".class" ejecutando el comando "ls" para listar el directorio.
4. Luego, ejecuta el programa utilizando el comando "java Main encode 'mensaje a ocultar' input.png out.png" para ocultar el mensaje en la imagen.
5. Finalmente, utiliza el comando "java Main decode out.png" para leer y obtener el mensaje oculto de la imagen.


