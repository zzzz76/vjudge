package judge.remote.provider.ecnu;

import judge.httpclient.DedicatedHttpClient;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2021-01-11
 */
public class ECNUCaptchaRecognizer {
    private static Tesseract tesseractNum;
    private static Tesseract tesseractOpe;

    static {
        tesseractNum = new Tesseract();
        tesseractNum.setDatapath("src/main/resources/tessdata"); // 这个数据集竟然更好
        tesseractNum.setPageSegMode(10);
        tesseractNum.setTessVariable("tessedit_char_whitelist", "123456789");
        tesseractNum.setTessVariable("user_defined_dpi", "70");
        tesseractNum.setOcrEngineMode(1);
    }

    static {
        tesseractOpe = new Tesseract();
        tesseractOpe.setDatapath("src/main/resources/tessdata");
        tesseractOpe.setPageSegMode(10);
        tesseractOpe.setTessVariable("tessedit_char_whitelist", "eilmnpstu");
        tesseractOpe.setTessVariable("user_defined_dpi", "70");
        tesseractOpe.setOcrEngineMode(1);
    }

    private static String[] operationList = {
            "", "minus", "times", "pplus"
    };


    /**
     * 刷新验证码
     *
     * @param client
     * @param key
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static BufferedImage refreshCaptcha(DedicatedHttpClient client, String key) throws ClientProtocolException, IOException {
        // 这里的key如果是url就更好了
        HttpGet get = new HttpGet("/vcode.php");
        BufferedImage image = client.execute(get, new ResponseHandler<BufferedImage>() {
            @Override
            public BufferedImage handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                FileOutputStream fos = null;
                try {
                    File captchaImg = File.createTempFile("ECNU", ".png");
                    fos = new FileOutputStream(captchaImg);
                    response.getEntity().writeTo(fos);
                    return ImageIO.read(captchaImg);
                } finally {
                    fos.close();
                }
            }}
        );
        return image;
    }

    /**
     * 对单个数字进行识别
     *
     * @param bufferedImage
     * @return
     */
    private static int doOCRToNum(BufferedImage bufferedImage) {
        String result = "";
        try {
            result = tesseractNum.doOCR(modelFiltering(bufferedImage));
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        if (result == null || result.isEmpty()) return 0;
        else return Integer.valueOf(result.substring(0, 1));
    }

    /**
     * 对操作符进行识别
     *
     * @param bufferedImage
     * @return
     */
    private static int doOCRToOpe(BufferedImage bufferedImage) {

        String result = "";
        try {
            result = tesseractOpe.doOCR(modelFiltering(bufferedImage));
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        if (result == null || result.isEmpty()) {
            return 0;
        } else {
            int min = 10, index = 0;
            for (int i = 1; i< operationList.length; i++) {
                int diff = levenshtein(operationList[i], result);
                if (diff < min) {
                    min = diff;
                    index = i;
                }
            }
            return index;
        }
    }

    private static void printImage(BufferedImage image) {
        int h = image.getHeight();
        int w = image.getWidth();
        for(int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (isBlack(image.getRGB(x, y))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static boolean isBlack(int rgb) {
        Color color = new Color(rgb);
        if (color.getRed() + color.getGreen() + color.getBlue() < 100) {
            return true;
        }
        return false;
    }

    /**
     * 计算字符串相异度
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int levenshtein(String str1, String str2) {
        int w = str1.length() + 1;
        int h = str2.length() + 1;
        int[][] matrix = new int[h][w];
        for (int x = 0; x < w; x++) {
            matrix[0][x] = x;
        }
        for (int y = 0; y < h; y++) {
            matrix[y][0] = y;
        }

        for (int y = 1; y < h; y++) {
            for (int x = 1; x < w; x++) {
                int temp = matrix[y - 1][x- 1];
                if (str1.charAt(x - 1) != str2.charAt(y - 1)) {
                    temp++;
                }
                matrix[y][x] = min(min(temp, matrix[y][x - 1] + 1), matrix[y - 1][x] + 1);
            }
        }
        return  matrix[h - 1][w - 1];
    }

    /**
     * 对图片进行分割
     *
     * @param image
     * @return
     */
    private static List<BufferedImage> splitImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int fixh = 30, fixw = 20; // fixw = 40
        List<BufferedImage> arrayList = new ArrayList<BufferedImage>();
        arrayList.add(image.getSubimage(0, 0, fixw, fixh));
        arrayList.add(image.getSubimage(fixw + 15, 0, w - fixw * 2 - 30, fixh));
        arrayList.add(image.getSubimage(w - fixw, 0, fixw, fixh));
        return arrayList;
    }

    /**
     * 对图片模板过滤
     *
     * @param image
     */
    private static BufferedImage modelFiltering(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[] pix = new int[w * h];
        image.getRGB(0, 0, w, h, pix, 0, w);
        int[] midPix = modelFiltering(pix, w, h);
        int[] finalPix = modelFiltering(midPix, w, h);
        image.setRGB(0, 0, w, h, finalPix, 0, w);
        return image;
    }

    /**
     * 模板过滤算法
     *
     * @param pix
     * @param w
     * @param h
     * @return
     */
    private static int[] modelFiltering(int[] pix, int w, int h) {
        int[] newPix = new int[w * h];
        ColorModel cm = ColorModel.getRGBdefault();
        // 坐标向量
        int[][] vector = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1,  0}, {0,  0}, {1,  0},
                {-1,  1}, {0,  1}, {1,  1}
        };

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                // 判定该点是否为黑色
                if (cm.getRed(pix[x + y * w]) == 0) {
                    // 判定该点是否在边界上
                    if (x != 0 && x != w - 1 && y != 0 && y != h - 1) {
                        // 对周围点进行统计
                        int white = 0, black = 0,gray = 0;
                        // 对点x, y遍历所有向量
                        for (int i = 0; i < 9; i++) {
                            int red = cm.getRed(pix[x + vector[i][0] + (y + vector[i][1]) * w]);
                            if (red == 0) black++;
                            else if (red == 255) white++;
                            else gray++;
                        }
                        if (white < 3 && gray >= 1) newPix[x+y*w] = pix[x+y*w];
                        else newPix[x+y*w] = -1;
                    } else {
                        newPix[x + y * w] = -1;
                    }
                } else {
                    newPix[x + y * w] = pix[x + y * w];
                }
            }
        }
        return newPix;
    }
}
