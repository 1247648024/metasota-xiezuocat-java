package com.metastoa;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;

public class SM3Util {

  /**
   *  sm3算法加密
   * @param paramStr  待加密字符串
   * @return 返回加密后，固定长度=32的16进制字符串
   */
  public static String encrypt(String paramStr) {
    // 将返回的hash值转换成16进制字符串
    String resultHexString = "";
    try {
      // 将字符串转换成byte数组
      byte[] srcData = paramStr.getBytes("UTF-8");
      // 调用hash()
      byte[] resultHash = hash(srcData);
      // 将返回的hash值转换成16进制字符串
      resultHexString = ByteUtils.toHexString(resultHash);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return resultHexString;
  }

  /**
   * 加密byte 数组 输出32位固定长度的 byte数组
   *
   * @param srcData  输入参数
   * @return 返回byte数组
   */
  public static byte[] hash(byte[] srcData) {
    SM3Digest digest = new SM3Digest();
    digest.update(srcData, 0, srcData.length);
    byte[] hash = new byte[digest.getDigestSize()];
    digest.doFinal(hash, 0);
    return hash;
  }

}
