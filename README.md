# Esteganografía LSB - Ocultar y Leer Mensajes en Imágenes

Este proyecto consiste en un programa en Java que utiliza la técnica de esteganografía LSB (Least Significant Bit) para ocultar mensajes dentro de imágenes en formato PNG. Permite ocultar un mensaje en los píxeles modificados de la imagen y luego leer y recuperar ese mensaje oculto.

## Cómo funciona

El programa consta de dos partes principales: ocultar el mensaje en una imagen y leer el mensaje oculto en una imagen.

### Ocultar el mensaje en una imagen

1. Se carga la imagen PNG de entrada especificada por el usuario.
2. El mensaje a ocultar se convierte en bytes.
3. Se verifica si el mensaje se puede ocultar en la imagen verificando el tamaño de la imagen y el tamaño del mensaje.
4. Se oculta el tamaño del mensaje en los primeros 32 bits (4 bytes) de la imagen.
5. Se oculta cada byte del mensaje en los píxeles de la imagen, modificando los bits menos significativos de los componentes de color.

### Leer el mensaje oculto en una imagen

1. Se carga la imagen PNG con el mensaje oculto especificada por el usuario.
2. Se extrae el tamaño del mensaje oculto de los primeros 32 bits (4 bytes) de la imagen.
3. Se recorren los píxeles de la imagen y se recuperan los bits menos significativos de los componentes de color para formar los bytes del mensaje oculto.
4. Se convierten los bytes del mensaje oculto en una cadena de caracteres.

## Uso del programa
