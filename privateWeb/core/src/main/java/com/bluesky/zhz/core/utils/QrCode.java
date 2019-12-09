package com.bluesky.zhz.core.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QrCode {

	/**
	 * 写二维码 返回BufferedImage 
	 * @param content 二维码的内容
	 * @param width 
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage writeToImage(String content, int width, int height) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	/**
	 * 写二维码 
	 * @param content 二维码的内容
	 * @param path  生成后的完整路径 如"D:/test.jpg"
	 * @param width  如 80
	 * @param height 如 80
	 * @throws Exception
	 */
	public static void writeToFile(String content, String path, int width, int height) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(path));
	}
	
	/**
	 * 写二维码 返回流
	 * @param content 二维码的内容
	 * @param width  如 80
	 * @param height 如 80
	 * @throws Exception
	 */
	public static ByteArrayOutputStream writeToStream(String content, int width, int height) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "jpg", stream);
		return stream;
	}
	
	/**
	 * 读取二维码信息
	 * @param path 读取的完整路径 如"D:/test.jpg"
	 * @return
	 * @throws Exception
	 */
	public static String read(String path) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);// 读取文件，识别成一个图片
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		// 二维码的参数
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		Result result = formatReader.decode(binaryBitmap, hints);

		return result.getText();
	}

}
