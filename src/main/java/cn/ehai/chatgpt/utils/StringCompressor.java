package cn.ehai.chatgpt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringCompressor {
    
    // 压缩字符串
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    // 解压缩字符串
    public static String decompress(String compressedStr) throws IOException {
        if (compressedStr == null || compressedStr.length() == 0) {
            return compressedStr;
        }
        byte[] compressed = Base64.getDecoder().decode(compressedStr);
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        gis.close();
        bis.close();
        out.close();
        return new String(out.toByteArray());
    }
}
