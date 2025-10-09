# ValidationTestController 测试文档

本文档详细说明 ValidationTestController 中每个方法的测试用例和参数。

## 1. 字符串验证测试

### 方法: testStringValidation

**请求URL**: `POST /validation-test/string-validation`

**请求参数**:
- `name` (String): 用户名称
- `phone` (String): 手机号码

**验证规则**:
- `name`: 不能为空且长度在2-20之间
- `phone`: 必须符合手机号格式（1开头，第二位为3-9，总共11位数字）

### 测试用例

#### 1.1 正常情况 - 所有参数都符合验证规则

**请求参数**:
```
name=张三
phone=13812345678
```

**预期结果**: 返回 200 状态码，响应数据为"字符串校验通过: 张三, 13812345678"

#### 1.2 异常情况 - 名称为空

**请求参数**:
```
name=
phone=13812345678
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 名称不能为空"

#### 1.3 异常情况 - 手机号格式不正确

**请求参数**:
```
name=张三
phone=12345
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 手机号格式不正确"

## 2. 数字验证测试

### 方法: testNumberValidation

**请求URL**: `GET /validation-test/number-validation`

**请求参数**:
- `age` (Integer): 年龄
- `price` (BigDecimal): 价格

**验证规则**:
- `age`: 必须大于0且小于150
- `price`: 必须大于0且小于99999.99

### 测试用例

#### 2.1 正常情况 - 所有参数都符合验证规则

**请求参数**:
```
age=25
price=99.99
```

**预期结果**: 返回 200 状态码，响应数据为"数字校验通过: 年龄=25, 价格=99.99"

#### 2.2 异常情况 - 年龄小于等于0

**请求参数**:
```
age=0
price=99.99
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 年龄必须大于0"

#### 2.3 异常情况 - 年龄大于150

**请求参数**:
```
age=200
price=99.99
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 年龄必须小于150"

#### 2.4 异常情况 - 价格小于等于0

**请求参数**:
```
age=25
price=0
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 价格必须大于0"

#### 2.5 异常情况 - 价格大于99999.99

**请求参数**:
```
age=25
price=100000
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 价格不能超过99999.99"

## 3. DTO验证测试

### 方法: testDtoValidation

**请求URL**: `POST /validation-test/dto-validation`

**请求体**: UserDTO 对象

**UserDTO 字段验证规则**:
- `username`: 不能为空且长度在2-20之间
- `password`: 不能为空且必须包含大小写字母和数字，至少8位
- `age`: 不能为空且必须大于0小于150
- `email`: 不能为空且必须符合邮箱格式
- `phone`: 不能为空且必须符合手机号格式
- `roles`: 不能为空且至少包含一个角色

### 测试用例

#### 3.1 正常情况 - 所有参数都符合验证规则

**请求体**:
```json
{
  "username": "testuser",
  "password": "Test123456",
  "age": 25,
  "email": "test@example.com",
  "phone": "13812345678",
  "roles": ["USER"]
}
```

**预期结果**: 返回 200 状态码，响应数据为"DTO校验通过: testuser"

#### 3.2 异常情况 - DTO字段验证失败

**请求体**:
```json
{
  "username": "",
  "password": "123",
  "age": 0,
  "email": "invalid-email",
  "phone": "12345",
  "roles": []
}
```

**预期结果**: 返回 400 状态码

## 4. 集合验证测试

### 方法: testCollectionValidation

**请求URL**: `POST /validation-test/collection-validation`

**请求参数**:
- `tags` (List<String>): 标签列表

**验证规则**:
- `tags`: 不能为空且标签数量必须在1-5个之间

### 测试用例

#### 4.1 正常情况 - 标签列表符合验证规则

**请求参数**:
```
tags=tag1&tags=tag2
```

**预期结果**: 返回 200 状态码，响应数据为"集合校验通过: [tag1, tag2]"

#### 4.2 异常情况 - 标签列表为空

**请求参数**:
```
(无参数)
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 标签列表不能为空"

#### 4.3 异常情况 - 标签数量超过限制

**请求参数**:
```
tags=tag1&tags=tag2&tags=tag3&tags=tag4&tags=tag5&tags=tag6
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 标签数量必须在1-5个之间"

## 5. 路径变量验证测试

### 方法: testPathVariableValidation

**请求URL**: `GET /validation-test/path-variable/{id}`

**路径参数**:
- `id` (Long): 用户ID

**验证规则**:
- `id`: 必须大于0

### 测试用例

#### 5.1 正常情况 - ID符合验证规则

**请求URL**:
```
/validation-test/path-variable/1
```

**预期结果**: 返回 200 状态码，响应数据为"路径变量校验通过: 1"

#### 5.2 异常情况 - ID小于等于0

**请求URL**:
```
/validation-test/path-variable/0
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: ID必须大于0"

## 6. 布尔值验证测试

### 方法: testBooleanValidation

**请求URL**: `GET /validation-test/boolean-validation`

**请求参数**:
- `status` (Boolean): 状态

**验证规则**:
- `status`: 不能为空

### 测试用例

#### 6.1 正常情况 - 状态不为空

**请求参数**:
```
status=true
```

**预期结果**: 返回 200 状态码，响应数据为"布尔值校验通过: true"

#### 6.2 异常情况 - 状态为空

**请求参数**:
```
(无参数)
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 状态不能为空"

## 7. 邮箱验证测试

### 方法: testEmailValidation

**请求URL**: `GET /validation-test/email-validation`

**请求参数**:
- `email` (String): 邮箱地址

**验证规则**:
- `email`: 必须符合邮箱格式

### 测试用例

#### 7.1 正常情况 - 邮箱格式正确

**请求参数**:
```
email=test@example.com
```

**预期结果**: 返回 200 状态码，响应数据为"邮箱校验通过: test@example.com"

#### 7.2 异常情况 - 邮箱格式不正确

**请求参数**:
```
email=invalid-email
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 邮箱格式不正确"

## 8. 日期时间验证测试

### 方法: testDateTimeValidation

**请求URL**: `GET /validation-test/datetime-validation`

**请求参数**:
- `appointmentTime` (LocalDateTime): 预约时间

**验证规则**:
- `appointmentTime`: 必须是未来时间

### 测试用例

#### 8.1 正常情况 - 预约时间为未来时间

**请求参数**:
```
appointmentTime=2025-12-31T10:30:00
```

**预期结果**: 返回 200 状态码，响应数据为"日期时间校验通过: 2025-12-31T10:30"

#### 8.2 异常情况 - 预约时间为过去时间

**请求参数**:
```
appointmentTime=2020-01-01T10:30:00
```

**预期结果**: 返回 400 状态码，错误信息为"参数校验失败: 预约时间必须是未来时间"

## 运行测试

### 使用 Maven 运行测试

```bash
cd malt-lab-01
mvn test
```

### 单独运行 ValidationTestControllerTest

```bash
cd malt-lab-01
mvn test -Dtest=ValidationTestControllerTest
```