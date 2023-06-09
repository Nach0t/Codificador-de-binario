import java.awt.image.BufferedImage;

public class Encoder {

    public void codificarMensaje(BufferedImage imagen, String mensaje) {
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        byte[] bytesMensaje = codificarMensaje(mensaje);

        // Ocultar el tamaño del mensaje en los primeros 32 bits de la imagen
        int tamañoMensaje = bytesMensaje.length;
        int[] bitsTamaño = enteroABits(tamañoMensaje);

        int índice = 0;
        for (int i = 0; i < bitsTamaño.length; i++) {
            int bit = bitsTamaño[i];
            int x = índice % ancho;
            int y = índice / ancho;
            int rgb = imagen.getRGB(x, y);
            int nuevoRgb = ocultarBit(rgb, bit);
            imagen.setRGB(x, y, nuevoRgb);
            índice++;
        }

        // Ocultar cada byte del mensaje en los píxeles de la imagen
        for (byte b : bytesMensaje) {
            int[] bitsByte = byteABits(b);
            for (int i = 0; i < bitsByte.length; i++) {
                int bit = bitsByte[i];
                int x = índice % ancho;
                int y = índice / ancho;
                int rgb = imagen.getRGB(x, y);
                int nuevoRgb = ocultarBit(rgb, bit);
                imagen.setRGB(x, y, nuevoRgb);
                índice++;
            }
        }
    }

    private int ocultarBit(int rgb, int bit) {
        int máscara = 0xFE; // Máscara para establecer el bit menos significativo en 0
        int nuevoRgb = (rgb & máscara) | bit;
        return nuevoRgb;
    }

    private int[] enteroABits(int valor) {
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = (valor >> (31 - i)) & 1;
        }
        return bits;
    }

    private int[] byteABits(byte valor) {
        int[] bits = new int[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = (valor >> (7 - i)) & 1;
        }
        return bits;
    }

    private byte[] codificarMensaje(String mensaje) {
        return mensaje.getBytes();
    }
}
