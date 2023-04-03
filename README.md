# metasota-xiezuocat-java
对智能纠错、智能改写、AI写作、单点登录签名算法进行封装
## 一、maven引入
```xml
<dependency>
    <groupId>com.xiezuocat</groupId>
    <artifactId>metasota-xiezuocat-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## 二、调用示例
### 1、智能纠错
#### 调用示例
```java
Xiezuocat xiezuocat = new Xiezuocat();
xiezuocat.setSecretKey("XXX");
String result = xiezuocat.check("{\"texts\":[\"哈哈哈。我天今吃了一顿饭\",\"我想念十分赵忠祥。嘿嘿嘿。\"]}");
System.out.println(result);
```
#### 返回结果
```json
{
  "errCode" : 0,
  "errMsg" : "",
  "data" : null,
  "alerts" : [ [ {
    "alertType" : 4,
    "alertMessage" : "建议用“今天”替换“天今”。",
    "sourceText" : "天今",
    "replaceText" : "今天",
    "start" : 5,
    "end" : 6,
    "errorType" : 3,
    "advancedTip" : true
  }, {
    "alertType" : 2,
    "alertMessage" : "根据段落，句子应已完结，句尾建议添加句号",
    "sourceText" : "饭",
    "replaceText" : "。",
    "start" : 11,
    "end" : 11,
    "errorType" : 2,
    "advancedTip" : false
  } ], [ {
    "alertType" : 4,
    "alertMessage" : "建议用“十分想念”替换“想念十分”。",
    "sourceText" : "想念十分",
    "replaceText" : "十分想念",
    "start" : 1,
    "end" : 4,
    "errorType" : 3,
    "advancedTip" : false
  } ] ],
  "checkLimitInfo" : {
    "checkWordCountLeftToday" : "997295",
    "totalCheckWordCountLeft" : "997295",
    "expireDate" : "2024-02-02 00:00:00"
  }
}
```
### 2、智能改写
##### 调用示例
```java
Xiezuocat xiezuocat = new Xiezuocat();
xiezuocat.setSecretKey("XXX");
String result = xiezuocat.rewrite("{\"items\":[\"一般\"], \"level\": \"middle\"}");
```
##### 返回结果
```json
{
  "errcode" : 0,
  "errmsg" : null,
  "items" : [ "普通" ],
  "stat" : "997268"
}
```

### 3、AI写作
#### 创建生成任务
##### 调用示例
```java
Xiezuocat xiezuocat = new Xiezuocat();
xiezuocat.setSecretKey("XXX");
JSONObject postData = new JSONObject();
postData.put("type", "Step");
postData.put("title", "飞机");
postData.put("length", "default");
String result = xiezuocat.generate(JSON.toJSONString(postData));
System.out.println(result);
```
##### 返回结果
```json
{
  "errCode" : 0,
  "errMsg" : "success",
  "data" : {
    "docId" : "3f8c9722-8683-4f93-8745-26cef524ee70"
  }
}
```

#### 获取生成结果
##### 调用示例
```java
String docId = "xxxxx"; // 此处docId为第一步生成的结果
String result = xiezuocat.getGenerateResult(docId);
System.out.println(result);
```
##### 返回结果
```json
{
  "errCode" : 0,
  "errMsg" : "success",
  "data" : {
    "status" : "FINISHED",
    "result" : "飞机\n飞机，是指在地面起飞、下降或到达预定地点的交通工具。飞机是人类第一个发明的交通工具，它是人类在太空中的飞行器。在近代，由于各种原因造成飞机在航空上的使用非常有限。在二十世纪初，欧洲和美国人对航空技术有了更深刻的认识和了解。德国人埃米尔·赫希曼于1853年首先发明了世界上第一架喷气式飞机--“喷气飞机”（Airbus），并提出了“飞行概念”及“飞行规则”。\n飞机简介\n普通飞机是靠自身携带或者外部电源驱动，而飞机是依靠外部动力起飞、降落甚至飞行。普通飞机只有机翼，不具有机翼上的动力装置；普通飞机可以飞得很高，但是在降落时会遇到很大困难；普通飞机不能像汽车那样在地面上进行长时间起降，而普通的汽车是在地面上行驶；也不能象直升机一样在空中进行快速升降和转弯。但是它可以向天空中起飞、降落或者飞行，并且有许多优点和缺点。如：速度快、安全性高，续航时间长，价格便宜，便于携带等等。\n航空分类\n飞机的分类根据使用飞机的目的、用途、性能，可以分为民用航空客机、军用航空轰炸机、军用运输机和战略弹道导弹运载器四类。民用航空客机：包括民航客机和军用客机两种，其结构与一般民用客机基本相同，主要有螺旋桨飞机和喷气式飞机两种。军用军机：是以军事目的为主，如轰炸、反恐等。战略弹道导弹运载器：主要有洲际导弹运载器（洲际弹道导弹的发射平台称为航母）、常规运载火箭运载器（如航天飞机及其搭载的各种科学实验仪器）、战略导弹发射平台（包括常规运载火箭）以及各种新型运载器。\n飞行规则\n飞机的飞行规则主要是指航空器飞行过程中应当遵守的规定。根据国际民航组织（ICAO）制定的《航空器飞行规则》，航空公司应严格遵守以下要求：\n1.对飞机进行定期检查；\n2.在使用期间，不得因飞机故障或其他原因，使飞机停止运行或处于应急情况下；\n3.在发现有危险时及时采取措施，防止事故发生。\n发展历程\n飞机，人类最早的交通工具之一。从20世纪初开始，飞机的制造和飞行技术在世界范围内都得到了飞速发展。\n",
    "wordCount" : "799",
    "restCount" : "138374"
  }
}
```
### 4、单点登录签名算法
##### 调用示例
```java
Xiezuocat xiezuocat = new Xiezuocat();
xiezuocat.setSecretKey("XX");
String result = xiezuocat.getSSOSignature("xxx", "xx", "a");
```
##### 返回结果
```json
eyJzaWduIjoiMjNkZDA2ZDJjOWUyN2M1MGY2OWQyMTU2MGY5ZWZhY2I2NTRiMTg4MWRkNjZhNjE3ZTViYzJmZGUxMTJkZjA2NiIsInVpZCI6ImEiLCJhcHBJZCI6Inh4eCIsInRpbWVzdGFtcCI6IjE2ODA1MDIwMzQ1MzYifQ==
```
