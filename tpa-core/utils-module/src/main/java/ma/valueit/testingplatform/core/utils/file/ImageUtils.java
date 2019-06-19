package ma.valueit.testingplatform.core.utils.file;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {

    public static BufferedImage base64StringToImage(String data) {
        // tokenize the data
        String base64Image = data.split(",")[1];

        // create a buffered image
        BufferedImage bufferedImage = null;
        byte[] imageByte;
        ByteArrayInputStream bis = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            imageByte = decoder.decode(base64Image);
            bis = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(bis);
            bis.close();

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    // todo logg here
                }
            }
        }

        return bufferedImage;
    }

    public static File bufferedImageToImageFile(BufferedImage image, String path, String format) {
        // write the image to a file
        File outputfile = new File(path);
        try {
            ImageIO.write(image, format, outputfile);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return outputfile;
    }

    public static String imgToBase64String(BufferedImage image, String format) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, format, os);
            return String.format("data:image/%s;base64,%s", format, Base64.getEncoder().encodeToString(os.toByteArray()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static BufferedImage base64StringToImg(String data) {
        try {
            // tokenize the data
            String base64String = data.split(",")[1];

            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public static BufferedImage blurImage(BufferedImage image) {
        float ninth = 1.0f / 9.0f;
        float[] blurKernel = {
                ninth, ninth, ninth,
                ninth, ninth, ninth,
                ninth, ninth, ninth
        };

        Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints hints = new RenderingHints(map);
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
        return op.filter(image, null);
    }

}
