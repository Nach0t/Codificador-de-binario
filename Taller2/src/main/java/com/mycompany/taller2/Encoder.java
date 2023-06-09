
package com.mycompany.taller2;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Esta clase se utiliza para codificar un mensaje en una imagen con la ayuda de la esteganografía.
 */
public class Encoder {

    /**
     * Codifica un mensaje en una imagen y guarda la imagen resultante.
     *
     * @param mensaje        El mensaje que se desea codificar.
     * @param archivoEntrada La ruta del archivo de imagen de entrada.
     * @param archivoSalida  La ruta del archivo de imagen de salida.
     * @throws IOException Si ocurre un error al leer/escribir la imagen.
     */
    public void codificarMensaje(String mensaje, String archivoEntrada, String archivoSalida) throws IOException {
        BufferedImage imagen = Esteganografia.leerImagen(archivoEntrada);
        byte[] bytesMensaje = codificarMensajeEnBytes(mensaje);

        int tamañoMensaje = bytesMensaje.length;
        int[] bitsTamaño = enteroABits(tamañoMensaje);

        int índice = 0;
        for (int i = 0; i < bitsTamaño.length; i++) {
            int bit = bitsTamaño[i];
            int x = índice % imagen.getWidth();
            int y = índice / imagen.getWidth();
            int rgb = imagen.getRGB(x, y);
            int nuevoRgb = ocultarBit(rgb, bit);
            imagen.setRGB(x, y, nuevoRgb);
            índice++;
        }

        for (byte b : bytesMensaje) {
            int[] bitsByte = byteABits(b);
            for (int i = 0; i < bitsByte.length; i++) {
                int bit = bitsByte[i];
                int x = índice % imagen.getWidth();
                int y = índice / imagen.getWidth();
                int rgb = imagen.getRGB(x, y);
                int nuevoRgb = ocultarBit(rgb, bit);
                imagen.setRGB(x, y, nuevoRgb);
                índice++;
            }
        }

        Esteganografia.guardarImagen(imagen, archivoSalida);
    }

    /**
     * Oculta un bit en el valor RGB de un píxel.
     *
     * @param rgb El valor RGB del píxel.
     * @param bit El bit a ocultar (0 o 1).
     * @return El nuevo valor RGB con el bit oculto.
     */
    private int ocultarBit(int rgb, int bit) {
        int máscara = 0xFE;
        int nuevoRgb = (rgb & máscara) | bit;
        return nuevoRgb;
    }

    /**
     * Convierte un entero en un arreglo de bits.
     *
     * @param valor El entero a convertir.
     * @return El arreglo de bits.
     */
    private int[] enteroABits(int valor) {
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = (valor >> (31 - i)) & 1;
        }
        return bits;
    }

    /**
     * Convierte un byte en un arreglo de bits.
     *
     * @param valor El byte a convertir.
     * @return El arreglo de bits.
     */
    private int[] byteABits(byte valor) {
        int[] bits = new int[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = (valor >> (7 - i)) & 1;
        }
        return bits;
    }

    /**
     * Convierte un mensaje en un arreglo de bytes.
     *
     * @param mensaje El mensaje a convertir.
     * @return El arreglo de bytes del mensaje.
     */
    private byte[] codificarMensajeEnBytes(String mensaje) {
        return mensaje.getBytes();
    }
}
