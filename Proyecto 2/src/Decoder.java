import java.awt.image.BufferedImage;

public class Decoder {

    public String decodificarMensaje(BufferedImage imagen) {
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();

        // Extraer el tamaño del mensaje oculto de los primeros 32 bits de la imagen
        int tamaño = 0;
        for (int i = 0; i < 32; i++) {
            int x = i % ancho;
            int y = i / ancho;
            int rgb = imagen.getRGB(x, y);
            int bit = extraerBit(rgb);
            tamaño = (tamaño << 1) | bit;
        }

        // Recuperar los bytes del mensaje oculto de los píxeles de la imagen
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

        // Convertir los bytes del mensaje en una cadena de texto
        return new String(bytesMensaje);
    }

    private int extraerBit(int rgb) {
        return rgb & 1;
    }
}
