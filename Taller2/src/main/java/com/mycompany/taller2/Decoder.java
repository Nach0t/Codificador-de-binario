
package com.mycompany.taller2;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Esta clase se utiliza para decodificar un mensaje oculto en una imagen utilizando esteganografía LSB.
 */
public class Decoder {

    /**
     * Decodifica un mensaje oculto en una imagen.
     *
     * @param archivoEntrada La ruta del archivo de imagen de entrada.
     * @return El mensaje oculto en la imagen decodificado.
     * @throws IOException Si ocurre un error al leer la imagen.
     */
    public String decodificarMensaje(String archivoEntrada) throws IOException {
        BufferedImage imagen = Esteganografia.leerImagen(archivoEntrada);

        int ancho = imagen.getWidth();
        int tamaño = 0;
        for (int i = 0; i < 32; i++) {
            int x = i % ancho;
            int y = i / ancho;
            int rgb = imagen.getRGB(x, y);
            int bit = extraerBit(rgb);
            tamaño = (tamaño << 1) | bit;
        }

        byte[] bytesMensaje = new byte[tamaño];
        int índice = 0;
        for (int i = 32; i < tamaño * 8 + 32; i++) {
            int x = i % ancho;
            int y = i / ancho;
            int rgb = imagen.getRGB(x, y);
            int bit = extraerBit(rgb);
            bytesMensaje[índice / 8] = (byte) ((bytesMensaje[índice / 8] << 1) | bit);
            índice++;
        }

        return new String(bytesMensaje);
    }

    /**
     * Extrae el bit menos significativo de un valor RGB.
     *
     * @param rgb El valor RGB del píxel.
     * @return El bit menos significativo del valor RGB.
     */
    private int extraerBit(int rgb) {
        return rgb & 1;
    }
}