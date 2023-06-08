# Esteganografía LSB - Ocultar y Leer Mensajes en Imágenes

Este proyecto consiste en un programa en Java que utiliza la técnica de esteganografía LSB (Least Significant Bit) para ocultar mensajes dentro de imágenes en formato PNG. Permite ocultar un mensaje en los píxeles modificados de la imagen y luego leer y obtener ese mensaje oculto.

## Cómo funciona

Este programa tiene dos partes principales: ocultar el mensaje en una imagen (encode) y la otra que lee el mensaje oculto en una imagen (decode).

### Ocultar el mensaje en una imagen

1. Se carga la imagen PNG de entrada.
2. El mensaje a ocultar se convierte en bytes alterando su frecuencia levemente, haciendo inpersitible por el ojo humano.
3. Se verifica si el mensaje se puede ocultar en la imagen asegurandose si el tamaño de la imagen y el tamaño del mensaje son correctos.
4. Se oculta el tamaño del mensaje en los primeros 32 bits imagen (4 bytes).
5. Se altera el ultimo dijito de cada bits para lograr realizar el ocultamiento.

### Como ejecutar el programa

1. Abrir una terminal (CMD, poweshell, Terminales de unix, etc)
2. Abrir la ubicacion del archivo. (En cmd es cd /direcion/del/archivo/.../)
3. Una vez hayas abierto la ubicacion del archivo ejecute: Javac Main.java.
4. Si no hubo nigun error ejecute "dir" para asegurarse que se encuentran los archivos ".class".
5. Despues ejecute " Java Main encode <mensaje a ocultar> input.png out.png ".
6. Para finalizar ejecute " Java Main decode out.png ".




