package cn.malt.code.maltlab01.controller;

import cn.malt.code.maltlab01.common.core.Result;
import cn.malt.code.maltlab01.pojo.dto.OrderDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 订单DTO验证测试控制器
 * 用于测试嵌套对象和List集合的验证功能
 */
@RestController
@RequestMapping("/order-validation")
@Validated
public class OrderValidationController {

    /**
     * 测试订单创建 - 包含嵌套对象和List集合的完整验证
     * 
     * @param orderDTO 订单信息
     * @return 验证结果
     */
    @PostMapping("/create")
    public Result createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return Result.success("订单验证通过，订单号: " + orderDTO.getOrderNo());
    }

    /**
     * 测试订单更新 - 与创建类似，用于验证相同的数据结构
     * 
     * @param id 订单ID
     * @param orderDTO 订单信息
     * @return 验证结果
     */
    @PutMapping("/update/{id}")
    public Result updateOrder(@PathVariable Long id, 
                              @Valid @RequestBody OrderDTO orderDTO) {
        return Result.success("订单更新验证通过，订单ID: " + id + "，订单号: " + orderDTO.getOrderNo());
    }
}