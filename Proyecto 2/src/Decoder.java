import java.awt.image.BufferedImage;
import java.io.IOException;

public class Decoder {

    public String decodificarMensaje(String archivoEntrada) throws IOException {
        BufferedImage imagen = Estenografia.leerImagen(archivoEntrada);

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

    private int extraerBit(int rgb) {
        return rgb & 1;
    }
}
