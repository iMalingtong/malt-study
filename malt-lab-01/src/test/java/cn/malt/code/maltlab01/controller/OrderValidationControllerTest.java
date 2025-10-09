package cn.malt.code.maltlab01.controller;

import cn.malt.code.maltlab01.common.core.Result;
import cn.malt.code.maltlab01.pojo.dto.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderValidationController.class)
class OrderValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createOrder_ValidOrder_ReturnsSuccess() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();

        // When & Then
        mockMvc.perform(post("/order-validation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("订单验证通过，订单号: ORDER20250101001"));
    }

    @Test
    void createOrder_BlankOrderNo_ReturnsValidationError() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();
        orderDTO.setOrderNo(""); // 空订单号

        // When & Then
        mockMvc.perform(post("/order-validation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOrder_NullAddress_ReturnsValidationError() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();
        orderDTO.setAddress(null); // 空地址

        // When & Then
        mockMvc.perform(post("/order-validation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOrder_EmptyProducts_ReturnsValidationError() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();
        orderDTO.setProducts(new ArrayList<>()); // 空产品列表

        // When & Then
        mockMvc.perform(post("/order-validation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOrder_InvalidAddressFields_ReturnsValidationError() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();
        OrderDTO.AddressDTO address = new OrderDTO.AddressDTO();
        address.setReceiver(""); // 空收货人
        address.setPhone("123"); // 无效手机号
        address.setDetail(""); // 空详细地址
        orderDTO.setAddress(address);

        // When & Then
        mockMvc.perform(post("/order-validation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOrder_InvalidProductFields_ReturnsValidationError() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();
        List<OrderDTO.ProductDTO> products = new ArrayList<>();
        OrderDTO.ProductDTO product = new OrderDTO.ProductDTO();
        product.setName(""); // 空商品名称
        product.setDescription("测试商品描述");
        product.setPrice(new BigDecimal("99.99"));
        product.setQuantity(1);
        product.setImage("test.jpg");
        product.setCategory("测试分类");
        products.add(product);
        orderDTO.setProducts(products);

        // When & Then
        mockMvc.perform(post("/order-validation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateOrder_ValidOrder_ReturnsSuccess() throws Exception {
        // Given
        OrderDTO orderDTO = createValidOrderDTO();

        // When & Then
        mockMvc.perform(put("/order-validation/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("订单更新验证通过，订单ID: 1，订单号: ORDER20250101001"));
    }

    /**
     * 创建有效的订单DTO用于测试
     * 
     * @return 有效的订单DTO
     */
    private OrderDTO createValidOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo("ORDER20250101001");

        // 创建地址信息
        OrderDTO.AddressDTO address = new OrderDTO.AddressDTO();
        address.setReceiver("张三");
        address.setPhone("13812345678");
        address.setDetail("北京市朝阳区某某街道123号");
        orderDTO.setAddress(address);

        // 创建商品列表
        List<OrderDTO.ProductDTO> products = new ArrayList<>();
        OrderDTO.ProductDTO product1 = new OrderDTO.ProductDTO();
        product1.setName("测试商品1");
        product1.setDescription("测试商品1的描述信息");
        product1.setPrice(new BigDecimal("99.99"));
        product1.setQuantity(2);
        product1.setImage("product1.jpg");
        product1.setCategory("电子产品");

        OrderDTO.ProductDTO product2 = new OrderDTO.ProductDTO();
        product2.setName("测试商品2");
        product2.setDescription("测试商品2的描述信息");
        product2.setPrice(new BigDecimal("199.99"));
        product2.setQuantity(1);
        product2.setImage("product2.jpg");
        product2.setCategory("家居用品");

        products.add(product1);
        products.add(product2);
        orderDTO.setProducts(products);

        return orderDTO;
    }
}