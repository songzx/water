package com.water.example.luceneexample;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Word;

public class SegWorkTest {
	private Seg seg = null;

	@Before
	public void init() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "dicdata/";
		//seg = new MaxWordSeg(Dictionary.getInstance(path));
		seg = new ComplexSeg(Dictionary.getInstance(path));
	}

	private String segWords(Reader input, String wordSpilt) {
		StringBuilder sb = new StringBuilder();
		try {
			MMSeg mmSeg = new MMSeg(input, seg);

			Word word = null;
			boolean first = true;
			while ((word = mmSeg.next()) != null) {
				if (!first) {
					sb.append(wordSpilt);
				}
				String w = word.getString();
				sb.append(w);
				first = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String segWords(String txt, String wordSpilt) {
		return segWords(new StringReader(txt), wordSpilt);
	}

	@Test
	public void testseg1() {
		String keywork = "中华人民共和国";
		String strs = segWords(keywork, "|");
		Assert.assertEquals("中华人民共和国", strs);
	}
	
	@Test
	public void testseg2() {
		String keywork = "朱小丹";
		String strs = segWords(keywork, "|");
		Assert.assertEquals("朱小丹", strs);
	}
	
	@Test
	public void testseg3() {
		String keywork = "金兰之友";
		String strs = segWords(keywork, "|");
		Assert.assertEquals("金兰之友", strs);
	}
}
