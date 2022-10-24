package com.metastoa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SM3SignatureUtil {

  /**
   * 获取SM3签名
   *
   * @param data
   * @param key
   * @return
   */
  public static String signatureSM3(Map<String, Object> data, String key) {
    String signatureSM3Str = signatureSM3Str(data);
    String concat = signatureSM3Str.concat("secretKey=").concat(key);
    return SM3Util.encrypt(concat);
  }

  /**
   * 递归获取拼接除secretKey之外的所有待签名字符串
   *
   * @param data 待签名数据
   * @return 签名
   */
  @SuppressWarnings("unchecked")
  public static String signatureSM3Str(Map<String, Object> data) {
    Set<String> keySet = data.keySet();
    String[] keyArray = keySet.toArray(new String[keySet.size()]);
    Arrays.sort(keyArray);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < keyArray.length; i++) {
      String key = keyArray[i];
      Object obj = data.get(key);
      if (!Objects.isNull(obj) && !StringUtils.isEmpty(obj.toString())) // 参数值为空，则不参与签名
      {
        if(obj instanceof JSONObject) { // 递归调用
          String jsonString = JSON.toJSONString(obj);
          Map<String, Object> childMap = JSON.parseObject(jsonString, Map.class);
          builder.append(key).append("=").append("{").append(signatureSM3Str(childMap)).append("}");
        } else if (obj instanceof JSONArray) { // 如果是JSONARRAY 不参与签名计算
          continue;
        } else {
          builder.append(key).append("=").append(obj.toString().trim());
        }
      }
    }
    return builder.toString();
  }
}
