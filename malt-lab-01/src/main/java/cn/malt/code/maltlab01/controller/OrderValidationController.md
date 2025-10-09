# Validation 注解测试指南

本文档说明如何测试 OrderDTO 类中的各种 validation 注解，包括嵌套对象和 List 集合的验证。

## 测试端点

1. 创建订单: `POST /order-validation/create`
2. 更新订单: `PUT /order-validation/update/{id}`

## 测试用例及参数示例

### 1. 正常情况 - 所有参数都符合验证规则

**请求URL**: `POST /order-validation/create`

**请求体**:
```json
{
  "orderNo": "ORDER20250101001",
  "address": {
    "receiver": "张三",
    "phone": "13812345678",
    "detail": "北京市朝阳区某某街道123号"
  },
  "products": [
    {
      "name": "测试商品1",
      "description": "测试商品1的描述信息",
      "price": 99.99,
      "quantity": 2,
      "image": "product1.jpg",
      "category": "电子产品"
    },
    {
      "name": "测试商品2",
      "description": "测试商品2的描述信息",
      "price": 199.99,
      "quantity": 1,
      "image": "product2.jpg",
      "category": "家居用品"
    }
  ]
}
```

**预期结果**: 返回 200 状态码，验证通过

### 2. 订单号为空

**请求体**:
```json
{
  "orderNo": "",
  "address": {
    "receiver": "张三",
    "phone": "13812345678",
    "detail": "北京市朝阳区某某街道123号"
  },
  "products": [
    {
      "name": "测试商品",
      "description": "测试商品的描述信息",
      "price": 99.99,
      "quantity": 1,
      "image": "product.jpg",
      "category": "测试分类"
    }
  ]
}
```

**预期结果**: 返回 400 状态码，错误信息为"订单号不能为空"

### 3. 地址为空

**请求体**:
```json
{
  "orderNo": "ORDER20250101001",
  "address": null,
  "products": [
    {
      "name": "测试商品",
      "description": "测试商品的描述信息",
      "price": 99.99,
      "quantity": 1,
      "image": "product.jpg",
      "category": "测试分类"
    }
  ]
}
```

**预期结果**: 返回 400 状态码，错误信息为"收货地址不能为空"

### 4. 商品列表为空

**请求体**:
```json
{
  "orderNo": "ORDER20250101001",
  "address": {
    "receiver": "张三",
    "phone": "13812345678",
    "detail": "北京市朝阳区某某街道123号"
  },
  "products": []
}
```

**预期结果**: 返回 400 状态码，错误信息为"商品列表不能为空"

### 5. 收货人信息不完整

**请求体**:
```json
{
  "orderNo": "ORDER20250101001",
  "address": {
    "receiver": "",
    "phone": "123",
    "detail": ""
  },
  "products": [
    {
      "name": "测试商品",
      "description": "测试商品的描述信息",
      "price": 99.99,
      "quantity": 1,
      "image": "product.jpg",
      "category": "测试分类"
    }
  ]
}
```

**预期结果**: 返回 400 状态码，错误信息为"收货人不能为空"（或其他第一个验证失败的字段）

### 6. 商品信息不完整

**请求体**:
```json
{
  "orderNo": "ORDER20250101001",
  "address": {
    "receiver": "张三",
    "phone": "13812345678",
    "detail": "北京市朝阳区某某街道123号"
  },
  "products": [
    {
      "name": "",
      "description": "测试商品的描述信息",
      "price": 0,
      "quantity": 0,
      "image": "",
      "category": ""
    }
  ]
}
```

**预期结果**: 返回 400 状态码，错误信息为"商品名称不能为空"（或其他第一个验证失败的字段）

## 运行测试

### 使用 Maven 运行测试

```bash
cd malt-lab-01
mvn test
```

### 单独运行 OrderValidationControllerTest

```bash
cd malt-lab-01
mvn test -Dtest=OrderValidationControllerTest
```

## 验证注解说明

### OrderDTO 类中的注解

1. `@NotBlank(message = "订单号不能为空")` - 验证订单号不为空且去除空格后不为空
2. `@NotNull(message = "收货地址不能为空")` - 验证地址对象不为 null
3. `@Valid` - 对嵌套的 AddressDTO 对象进行验证
4. `@NotEmpty(message = "商品列表不能为空")` - 验证商品列表不为 null 且不为空
5. `@Valid` - 对 List 中的每个 ProductDTO 对象进行验证

### AddressDTO 类中的注解

1. `@NotBlank(message = "收货人不能为空")` - 验证收货人不为空
2. `@NotBlank(message = "手机号不能为空")` - 验证手机号不为空
3. `@Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")` - 验证手机号格式
4. `@NotBlank(message = "详细地址不能为空")` - 验证详细地址不为空

### ProductDTO 类中的注解

1. `@NotBlank(message = "商品名称不能为空")` - 验证商品名称不为空
2. `@Length(min = 2, max = 20, message = "商品名称长度必须在2-20之间")` - 验证商品名称长度
3. `@NotBlank(message = "商品描述不能为空")` - 验证商品描述不为空
4. `@Length(min = 2, max = 50, message = "商品描述长度必须在2-50之间")` - 验证商品描述长度
5. `@NotNull(message = "商品价格不能为空")` - 验证商品价格不为 null
6. `@Min(value = 1, message = "商品价格必须大于0")` - 验证商品价格大于0
7. `@NotNull(message = "商品数量不能为空")` - 验证商品数量不为 null
8. `@Min(value = 1, message = "商品数量必须大于0")` - 验证商品数量大于0
9. `@NotBlank(message = "商品图片不能为空")` - 验证商品图片不为空
10. `@NotBlank(message = "商品分类不能为空")` - 验证商品分类不为空