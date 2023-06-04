/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.encoderdecoder;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageEncoder {
    public void encodeMensaje(String imagenEntrada, String imagenSalida, String mensaje) {
        try {
            BufferedImage imagen = ImageIO.read(new File(imagenEntrada));

            // Verificar si la imagen tiene suficiente espacio para codificar el mensaje
            int capacidad = imagen.getWidth() * imagen.getHeight() * 3 / 8;
            if (mensaje.length() > capacidad) {
                System.out.println("El mensaje es demasiado largo para ser codificado en la imagen.");
                return;
            }

            // Agregar un delimitador al mensaje para saber dónde termina
            mensaje = "%hola_profe/" + mensaje;

            int indiceMensaje = 0;
            for (int y = 0; y < imagen.getHeight(); y++) {
                for (int x = 0; x < imagen.getWidth(); x++) {
                    int rgb = imagen.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    // Codificar el mensaje en los bits menos significativos de cada componente RGB
                    if (indiceMensaje < mensaje.length()) {
                        char caracter = mensaje.charAt(indiceMensaje);
                        r = (r & 0xFE) | ((caracter >> 7) & 0x01);
                        g = (g & 0xFE) | ((caracter >> 6) & 0x01);
                        b = (b & 0xFE) | ((caracter >> 5) & 0x01);
                        indiceMensaje++;
                    }

                    rgb = (r << 16) | (g << 8) | b;
                    imagen.setRGB(x, y, rgb);
                }
            }

            ImageIO.write(imagen, "png", new File(imagenSalida));
            System.out.println("Imagen con mensaje codificado guardada en: " + imagenSalida);
        } catch (IOException e) {
            System.out.println("Error al leer o escribir la imagen: " + e.getMessage());
        }
    }
}

