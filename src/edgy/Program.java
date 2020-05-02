package edgy;

import edgy.utils.ImageReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;

public class Program {
    public static void main(String ... args) throws IOException {
        URL resource = ClassLoader.getSystemResource("scene1.dat");
        ImageReader reader = new ImageReader(new File(resource.getFile()));
        int width = reader.getWidth();
        int height = reader.getHeight();
        byte[][] buf = reader.getFrameBytes(100);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = buf[y][x]
                | buf[y][x] << 8
                | buf[y][x] << 16
                        | 0xff000000;

                img.setRGB(x, y, rgb);
            }
        }
        ImageIO.write(img, "png", new File("work/out.png"));
    }
}
