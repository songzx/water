package com.water.example.rqcodeexample;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class RqcodeExampleTest {

	private File tofile;

	@Before
	public void init() {
		String topath = "D:\\coding\\testdata\\rqcode\\test.jpg";
		tofile = new File(topath);
	}

	@Test
	public void testcoder() throws Exception {
		String contents = "q";
		int width = 100;
		int height = 100;
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToFile(bitMatrix, "jpg", tofile);
	}
}
