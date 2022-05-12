# web3j-rlp-playground

## 快速开始
参见单元测试 [HelloWorld.scala](./src/test/scala/HelloWorld.scala)。

## 温馨提示

### 1. java 类型和 RLP 类型的映射关系

```java
public class Hello {
	public String x;
	public World[] worlds;
}

public class World {
	public String[] y;
}
```

如上 `Hello` 类型会按以下 RLP 类型（`org.web3j.rlp.RlpType`）编码

```java
RlpList {
  values: {
    RlpString, // x
    RlpList {  // worlds
      values: {
        RlpList {
          values: RlpString, // y
        },
      },
    },
  },
}
```

### 2. RlpDecoder 的返回结果是 RlpList

`[String, String]` 编码后的解码结果为 `[[String, String]]`。
