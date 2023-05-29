/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller_2;

/**
 *
 * @author ignac
 */
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Codigo2 {
    public static void main(String[] args) {
        String mensaje = "hola profe";
        String rutaImagen = "C:\\Users\\ignac\\Downloads\\Imagen.png";
        String rutaImagenSalida = "C:\\Users\\ignac\\Downloads\\Imagen2.png";

        // Ocultar el mensaje en la imagen
        ocultarMensaje(mensaje, rutaImagen, rutaImagenSalida);

        // Leer el mensaje oculto en la imagen
        String mensajeOculto = leerMensajeOculto(rutaImagenSalida);

        System.out.println("Mensaje oculto: " + mensajeOculto);
    }

    public static void ocultarMensaje(String mensaje, String rutaImagen, String rutaImagenSalida) {
        try {
            // Cargar la imagen original
            BufferedImage imagen = ImageIO.read(new File(rutaImagen));

            // Convertir el mensaje a un arreglo de bytes
            byte[] bytesMensaje = mensaje.getBytes();

            // Ocultar el mensaje en los bits menos significativos de los componentes RGB
            int indice = 0;
            for (int y = 0; y < imagen.getHeight(); y++) {
                for (int x = 0; x < imagen.getWidth(); x++) {
                    int pixel = imagen.getRGB(x, y);
                    if (indice < bytesMensaje.length) {
                        // Obtener el byte correspondiente al índice actual
                        byte byteMensaje = bytesMensaje[indice];

                        // Obtener los componentes RGB del píxel
                        int r = (pixel >> 16) & 0xFF;
                        int g = (pixel >> 8) & 0xFF;
                        int b = pixel & 0xFF;

                        // Ocultar los bits del byteMensaje en los bits menos significativos de los componentes RGB
                        r = (r & 0xFE) | ((byteMensaje >> 6) & 0x03);
                        g = (g & 0xFE) | ((byteMensaje >> 4) & 0x03);
                        b = (b & 0xFC) | ((byteMensaje >> 2) & 0x03);

                        // Actualizar el píxel modificado en la imagen
                        int pixelModificado = (pixel & 0xFF000000) | (r << 16) | (g << 8) | b;
                        imagen.setRGB(x, y, pixelModificado);

                        indice++;
                    }
                }
            }

            // Guardar la imagen modificada con el mensaje oculto
            File archivoSalida = new File(rutaImagenSalida);
            ImageIO.write(imagen, "png", archivoSalida);

            System.out.println("Mensaje oculto correctamente en la imagen.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String leerMensajeOculto(String rutaImagenOculta) {
        StringBuilder mensajeOculto = new StringBuilder();

        try {
            // Cargar la imagen oculta
            BufferedImage imagenOculta = ImageIO.read(new File(rutaImagenOculta));

            // Leer el mensaje oculto en los bits menos significativos de los componentes RGB
            int indice = 0;
            byte byteMensaje = 0;
            for (int y = 0; y < imagenOculta.getHeight(); y++) {
                for (int x = 0; x < imagenOculta.getWidth(); x++) {
                    int pixel = imagenOculta.getRGB(x, y);

                    // Obtener los componentes RGB del píxel
                    int r = (pixel >> 16) & 0xFF;
                    int g = (pixel >> 8) & 0xFF;
                    int b = pixel & 0xFF;

                    // Recuperar los bits ocultos de los componentes RGB
                    byteMensaje = (byte) ((byteMensaje << 2) | (r & 0x03));
                    byteMensaje = (byte) ((byteMensaje << 2) | (g & 0x03));
                    byteMensaje = (byte) ((byteMensaje << 2) | (b & 0x03));

                    indice++;

                    // Cada 4 bytes leídos forman un carácter del mensaje
                    if (indice % 4 == 0) {
                        // El byteMensaje contiene el carácter del mensaje oculto
                        mensajeOculto.append((char) byteMensaje);
                        byteMensaje = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensajeOculto.toString();
    }
}