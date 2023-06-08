import java.awt.image.BufferedImage;

public class Encoder {

    public static void codificarMensaje(BufferedImage image, String mensaje) {
        int ancho = image.getWidth();
        int alto = image.getHeight();
        byte[] messageBytes = EsteganografiaLSB.codificarMensaje(mensaje);

        // Ocultar el tamaño del mensaje en los primeros 32 bits de la imagen
        int TamañoMensaje = messageBytes.length;
        int[] sizeBits = intToBits(TamañoMensaje);

        int indice = 0;
        for (int i = 0; i < sizeBits.length; i++) {
            int bit = sizeBits[i];
            int x = indice % ancho;
            int y = indice / ancho;
            int rgb = image.getRGB(x, y);
            int newRgb = esconderBit(rgb, bit);
            image.setRGB(x, y, newRgb);
            indice++;
        }

        // Ocultar cada byte del mensaje en los píxeles de la imagen
        for (byte b : messageBytes) {
            int[] byteBits = byteToBits(b);
            for (int i = 0; i < byteBits.length; i++) {
                int bit = byteBits[i];
                int x = indice % ancho;
                int y = indice / ancho;
                int rgb = image.getRGB(x, y);
                int newRgb = esconderBit(rgb, bit);
                image.setRGB(x, y, newRgb);
                indice++;
            }
        }
    }

    private static int esconderBit(int rgb, int bit) {
        int mask = 0xFE; // Máscara para establecer el bit menos significativo en 0
        int newRgb = (rgb & mask) | bit;
        return newRgb;
    }

    private static int[] intToBits(int valor) {
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = (valor >> (31 - i)) & 1;
        }
        return bits;
    }

    private static int[] byteToBits(byte valor) {
        int[] bits = new int[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = (valor >> (7 - i)) & 1;
        }
        return bits;
    }
}
