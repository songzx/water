package com.water.metamodel;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 随机数据，组装测试数据
 * 
 * @author 0000
 * @date Feb 26, 2013 10:29:23 PM
 * @version 1.0
 * @classname RandData
 */
public class RandData {
	private static RandData randData = null;
	private final static String sname = "冯 陈 楮 卫 蒋 沈 韩 杨 冯 陈 楮 卫 蒋 沈 韩 杨 朱 秦 尤 许 何 吕 施 张 孔 曹 严 华 金 魏 陶 姜 戚 谢 邹 喻 柏 水 窦 章 云 苏 潘 葛 奚 范 彭 郎 鲁 韦 昌 马 苗 凤 花 方 俞 任 袁 柳 酆 鲍 史 唐 费 廉 岑 薛 雷 贺 倪 汤 滕 殷 罗 毕 郝 邬 安 常 乐 于 时 傅 皮 卞 齐 康 伍 余 元 卜 顾 孟 平 黄 和 穆 萧 尹 姚 邵 湛 汪 祁 毛 禹 狄 米 贝 明 臧 计 伏 成 戴 谈 宋 茅 庞 熊 纪 舒 屈 项 祝 董 梁 杜 阮 蓝 闽 席 季 麻 强 贾 路 娄 危 江 童 颜 郭 梅 盛 林 刁 锺 徐 丘 骆 高 夏 蔡 田 樊 胡 凌 霍 虞 万 支 柯 昝 管 卢 莫 经 房 裘 缪 干 解 应 宗 丁 宣 贲 邓 郁 单 杭 洪 包 诸 左 石 崔 吉 钮 龚 程 嵇 邢 滑 裴 陆 荣 翁 荀 羊 於 惠 甄 麹 家 封 芮 羿 储 靳 汲 邴 糜 松 井 段 富 巫 乌 焦 巴 弓 牧 隗 山 谷 车 侯 宓 蓬 全 郗 班 仰 秋 仲 伊 宫 宁 仇 栾 暴 甘 斜 厉 戎 祖 武 符 刘 景 詹 束 龙 叶 幸 司 韶 郜 黎 蓟 薄 印 宿 白 怀 蒲 邰 从 鄂 索 咸 籍 赖 卓 蔺 屠 蒙 池 乔 阴 郁 胥 能 苍 双 闻 莘 党 翟 谭 贡 劳 逄 姬 申 扶 堵 冉 宰 郦 雍 郤 璩 桑 桂 濮 牛 寿 通 边 扈 燕 冀 郏 浦 尚 农 温 别 庄 晏 柴 瞿 阎 充 慕 连 茹 习 宦 艾 鱼 容 向 古 易 慎 戈 廖 庾 终 暨 居 衡 步 都 耿 满 弘 匡 国 文 寇 广 禄 阙 东 欧 殳 沃 利 蔚 越 夔 隆 师 巩 厍 聂 晁 勾 敖 融 冷 訾 辛 阚 那 简 饶 空 曾 毋 沙 乜 养 鞠 须 丰 巢 关 蒯 相 查 后 荆 红 游 竺 权 逑 盖 益 桓 公 万俟 司马 上官 欧阳 夏侯 诸葛 闻人 东方 赫连 皇甫 尉迟 公羊 澹台 公冶 宗政 濮阳 淳于 单于 太叔 申屠 公孙 仲孙 轩辕 令狐 锺离 宇文 长孙 慕容 鲜于 闾丘 司徒 司空 丌官 司寇 仉 督 子车 颛孙 端木 巫马 公西 漆雕 乐正 壤驷 公良 拓拔 夹谷 宰父 谷梁 晋 楚 阎 法 汝 鄢 涂 钦 段干 百里 东郭 南门 呼延 归 海 羊舌 微生 岳 帅 缑 亢 况 后 有 琴 梁丘 左丘 东门 西门 商 牟 佘 佴 伯 赏 南宫 墨 哈 谯 笪 年 爱 阳 佟";
	private static String strnames[] = null;

	private RandData() {
		strnames = sname.split(" ");
	}

	public static RandData getInstance() {
		if (randData == null) {
			randData = new RandData();
		}
		return randData;
	}

	/*
	 * 
	 * 0~9 48~57 A~Z 65~90 a~z 97~122
	 */
	public String get0_9(int digit) {
		return getstr(48, 57, digit);
	}

	public String getA_Z(int digit) {
		return getstr(65, 90, digit);
	}

	public String geta_z(int digit) {
		return getstr(97, 122, digit);
	}

	public String get0_z(int digit) {
		return getstr(new int[][] { { 48, 57 }, { 65, 90 }, { 97, 122 } }, digit);
	}

	/**
	 * 获取区间的字节
	 * 
	 * @param min
	 * @param max
	 * @param digit
	 * @return
	 */
	private String getstr(int min, int max, int digit) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < digit; i++) {
			while (true) {
				int _int = new Random().nextInt(max * 5);
				if (_int >= min && _int <= max) {
					result.append((char) _int);
					break;
				}
			}
		}
		return result.toString();
	}

	/**
	 * 获取区间组的随机字节
	 * 
	 * @param intascii
	 * @param digit
	 * @return
	 */
	private String getstr(int[][] intascii, int digit) {
		StringBuffer result = new StringBuffer();
		int max = 100;
		for (int i = 0; i < intascii.length; i++) {
			int tmpmax = intascii[i][1];
			if (tmpmax > max) {
				max = tmpmax;
			}
		}

		for (int i = 0; i < digit; i++) {
			while (true) {
				int _int = new Random().nextInt(max * 5);
				boolean flag = false;
				for (int j = 0; j < intascii.length; j++) {
					if (_int >= intascii[j][0] && _int <= intascii[j][1]) {
						result.append((char) _int);
						flag = true;
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		}
		return result.toString();
	}

	/**
	 * 获取随机中文
	 * 
	 * @param digit
	 * @return
	 */
	public String getZh(int digit) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < digit; i++) {
			String tmpcode = "";
			while (true) {
				int _int = new Random().nextInt(100);
				if (_int >= 1 && _int <= 94) {
					tmpcode += String.format("%1$,02d", _int);
				}
				if (tmpcode.length() == 4) {
					break;
				}
			}
			String tmpzh = CodeToChinese(tmpcode);
			if (tmpzh.equals("�")) {
				i--;
				continue;
			}
			result.append(tmpzh);

		}
		return result.toString();
	}

	/**
	 * 获取随机百家性
	 * 
	 * @return
	 */
	public String getZhName() {
		int index = strnames.length;
		return strnames[new Random().nextInt(index)];
	}

	private String bytes2HexString(byte b) {
		return bytes2HexString(new byte[] { b });
	}

	// 汉字转换成区位码
	private String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	// 汉字转换成区位码
	private String getString(String chinese) throws UnsupportedEncodingException {
		byte[] bs;
		String s = "";
		bs = chinese.getBytes("GB2312");
		for (int i = 0; i < bs.length; i++) {
			int a = Integer.parseInt(bytes2HexString(bs[i]), 16);
			s += (a - 0x80 - 0x20) + "";
		}
		return s;
	}

	// 区位码是与汉字一一对应的编码，用四位数字表示， 前两位从01 到94称区码，后两位从01到94称位码。 一个汉字的前一半是
	// ASCⅡ码为“160＋区码”的字符，后一半是ASCⅡ码为“160＋ 位码”的字符。
	//
	// 区位码转换成汉字
	private String CodeToChinese(String code) {
		String Chinese = "";
		try {
			for (int i = 0; i < code.length(); i += 4) {
				byte[] bytes = new byte[2];

				String lowCode = code.substring(i, i + 2);
				int tempLow = Integer.parseInt(lowCode);
				tempLow += 160;
				bytes[0] = (byte) tempLow;

				String highCode = code.substring(i + 2, i + 4);
				int tempHigh = Integer.parseInt(highCode);
				tempHigh += 160;
				bytes[1] = (byte) tempHigh;

				String chara = new String(bytes, "GB2312");
				Chinese += chara;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Chinese;
	}

}
