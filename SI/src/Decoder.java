import java.awt.image.BufferedImage;

public class Decoder {

    public static String decodificarMensaje(BufferedImage imagen) {
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();

        // Extraer el tamaño del mensaje oculto de los primeros 32 bits de la imagen
        int size = 0;
        for (int i = 0; i < 32; i++) {
            int x = i % ancho;
            int y = i / ancho;
            int rgb = imagen.getRGB(x, y);
            int bit = extraerBit(rgb);
            size = (size << 1) | bit;
        }

        // Recuperar los bytes del mensaje oculto de los píxeles de la imagen
        byte[] messageBytes = new byte[size];
        int index = 0;
        for (int i = 32; i < size * 8 + 32; i++) {
            int x = i % ancho;
            int y = i / ancho;
            int rgb = imagen.getRGB(x, y);
            int bit = extraerBit(rgb);
            messageBytes[index / 8] = (byte) ((messageBytes[index / 8] << 1) | bit);
            index++;
        }

        return EsteganografiaLSB.decodificarMensaje(messageBytes);
    }

    private static int extraerBit(int rgb) {
        return rgb & 1;
    }
}
