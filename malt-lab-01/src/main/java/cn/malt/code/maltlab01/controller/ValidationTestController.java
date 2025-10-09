package cn.malt.code.maltlab01.controller;

import cn.malt.code.maltlab01.common.core.Result;
import cn.malt.code.maltlab01.pojo.dto.UserDTO;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 专门用于测试各种 validation 注解校验的控制器
 */
@RestController
@RequestMapping("/validation-test")
@Validated
public class ValidationTestController {

    /**
     * 测试 @NotBlank, @Length, @Pattern 等字符串校验注解
     */
    @PostMapping("/string-validation")
    public Result testStringValidation(
            @NotBlank(message = "名称不能为空")
            @Length(min = 2, max = 20, message = "名称长度必须在2-20之间")
            @RequestParam String name,
            
            @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
            @RequestParam String phone) {
        return Result.success("字符串校验通过: " + name + ", " + phone);
    }

    /**
     * 测试数字范围校验注解
     */
    @GetMapping("/number-validation")
    public Result testNumberValidation(
            @Min(value = 1, message = "年龄必须大于0")
            @Max(value = 150, message = "年龄必须小于150")
            @RequestParam Integer age,
            
            @DecimalMin(value = "0.01", message = "价格必须大于0")
            @DecimalMax(value = "99999.99", message = "价格不能超过99999.99")
            @RequestParam BigDecimal price) {
        return Result.success("数字校验通过: 年龄=" + age + ", 价格=" + price);
    }

    /**
     * 测试日期时间校验
     */
    @GetMapping("/datetime-validation")
    public Result testDateTimeValidation(
            @Future(message = "预约时间必须是未来时间")
            @RequestParam LocalDateTime appointmentTime) {
        return Result.success("日期时间校验通过: " + appointmentTime);
    }

    /**
     * 测试 DTO 对象校验
     */
    @PostMapping("/dto-validation")
    public Result testDtoValidation(@Valid @RequestBody UserDTO userDTO) {
        return Result.success("DTO校验通过: " + userDTO.getUsername());
    }

    /**
     * 测试集合校验
     */
    @PostMapping("/collection-validation")
    public Result testCollectionValidation(
            @NotEmpty(message = "标签列表不能为空")
            @Size(min = 1, max = 5, message = "标签数量必须在1-5个之间")
            @RequestParam List<String> tags) {
        return Result.success("集合校验通过: " + tags);
    }

    /**
     * 测试路径变量校验
     */
    @GetMapping("/path-variable/{id}")
    public Result testPathVariableValidation(
            @PathVariable
            @Min(value = 1, message = "ID必须大于0")
            Long id) {
        return Result.success("路径变量校验通过: " + id);
    }

    /**
     * 测试布尔值校验（使用自定义验证）
     */
    @GetMapping("/boolean-validation")
    public Result testBooleanValidation(
            @NotNull(message = "状态不能为空")
            @RequestParam Boolean status) {
        return Result.success("布尔值校验通过: " + status);
    }

    /**
     * 测试邮箱格式校验
     */
    @GetMapping("/email-validation")
    public Result testEmailValidation(
            @Email(message = "邮箱格式不正确")
            @RequestParam String email) {
        return Result.success("邮箱校验通过: " + email);
    }
}