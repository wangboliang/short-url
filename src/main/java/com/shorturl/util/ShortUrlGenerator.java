package com.shorturl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShortUrlGenerator {

    public static final int DEFAULT_LENGTH = 8;

    /**
     * 短链生成算法
     *
     * @param url    长链
     * @param length 长度
     */
    public static String generateShortUrlId(String url, int length) {
        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // 对传入网址进行 MD5 加密
        String hex = getMD5Encrypted(url);

        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
        String sTempSubString = hex.substring(1 * 8, 1 * 8 + 8);

        // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
        StringBuilder outChars = new StringBuilder();
        length = Math.min(length, DEFAULT_LENGTH);
        for (int j = 0; j < length; j++) {
            // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            long index = 0x0000003D & lHexLong;
            // 把取得的字符相加
            outChars.append(chars[(int) index]);
            // 每次循环按位右移 5 位
            lHexLong = lHexLong >> 5;
        }
        return outChars.toString();
    }
    
    public static String getMD5Encrypted(String key) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(key.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException ignored) {

        }
        return "";
    }

}
