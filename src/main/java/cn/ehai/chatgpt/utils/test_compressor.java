package cn.ehai.chatgpt.utils;


public abstract class test_compressor {



    public static void tttt() throws Exception {
        String originalString = "十个字符串十个字符串";
        System.out.println(originalString.length());
        String compressedString = StringCompressor.compress(originalString);
//        String decompressedString = StringCompressor.decompress(compressedString);
//        System.out.println(originalString.equals(decompressedString)); // true
        System.out.println(compressedString);
    }

    public static void main(String[] args) throws Exception {
        tttt();
    }

}
