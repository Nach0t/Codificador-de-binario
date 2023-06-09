import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Estenografia {

    public static BufferedImage leerImagen(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    public static void guardarImagen(BufferedImage imagen, String filePath) throws IOException {
        ImageIO.write(imagen, "png", new File(filePath));
    }
}
