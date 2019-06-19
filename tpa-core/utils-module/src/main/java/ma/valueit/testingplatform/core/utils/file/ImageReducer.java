package ma.valueit.testingplatform.core.utils.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageReducer {
    /**
     * Extension of images formats that can be supported for ImageIO read and write methods.
     * GIF is not included because transformation will remove animation.
     */
    public static final String[] IMAGE_EXTENSIONS = {"JPG", "PNG", "BMP"};

    /**
     * @param file
     * @return extension of file if any or empty String
     */
    public static String getExtension(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        return index == -1 ? "" : name.substring(index + 1);
    }

    /**
     * @param ext extension of a file
     * @return true if ext is a supported image extension, false otherwise
     */
    public static boolean checkImageExtension(String ext) {
        for (String imgExt : IMAGE_EXTENSIONS)
            if (ext.equalsIgnoreCase(imgExt))
                return true;

        return false;
    }

    /**
     * If input file is an image, output file will be a copy of this image.
     * Input image is transform with a simple read/write operation expecting a size reduction.
     * Output image is the smallest file between original and transform images.
     * <p>
     * If input file is not an image, output file will be a copy of input.
     * <p>
     * Last modified time is conserve from input file.
     *
     * @param input  a File to read from
     * @param output a File to be written to
     */
    public static void reduce(File input, File output) {
        try {
            String ext = getExtension(input);

            if (checkImageExtension(ext)) {
                BufferedImage img = ImageIO.read(input);
                ImageIO.write(img, ext, output);

                if (output.length() <= input.length()) {
                    Files.setLastModifiedTime(output.toPath(), Files.getLastModifiedTime(input.toPath()));
                } else {
                    Files.copy(input.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                }
            } else {
                Files.copy(input.toPath(), output.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
            }

            System.out.printf("%s\t%s\t%d\t%s\t%d\n", ext, input.getAbsolutePath(), input.length(), output.getAbsolutePath(), output.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reduceAll(File srcPath, File dstPath) {
        if (!srcPath.exists()) {
            return;
        }

        if (srcPath.isDirectory()) {
            if (!dstPath.exists()) {
                dstPath.mkdirs();
            }

            for (File src : srcPath.listFiles()) {
                File dst = new File(dstPath, src.getName());
                reduceAll(src, dst);
            }
        } else {
            reduce(srcPath, dstPath);
        }
    }

    /**
     * Read fully recursively srcPath, transform each image, and put them in dstPath respecting subfolder hierarchy.
     *
     * @param srcPath a directory to read images from
     * @param dstPath a directory to be written to
     */
    public static void reduceAll(String srcPath, String dstPath) {
        reduceAll(new File(srcPath), new File(dstPath));
    }

    /**
     * @param args {0 : srcPath, 1 : dstPath}
     */
    public static void main(String[] args) {
        reduceAll(args[0], args[1]);
    }
}
