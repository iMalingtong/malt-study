package cn.malt.code.maltlab01.controller;

import cn.malt.code.maltlab01.common.core.Result;
import cn.malt.code.maltlab01.pojo.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ValidationTestController.class)
class ValidationTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testStringValidation_ValidParams_ReturnsSuccess() throws Exception {
        mockMvc.perform(get("/validation-test/string-validation")
                .param("name", "张三")
                .param("phone", "13812345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("字符串校验通过: 张三, 13812345678"));
    }

    @Test
    void testStringValidation_BlankName_ReturnsValidationError() throws Exception {
        mockMvc.perform(get("/validation-test/string-validation")
                .param("name", "")
                .param("phone", "13812345678"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: 名称不能为空"));
    }

    @Test
    void testStringValidation_InvalidPhone_ReturnsValidationError() throws Exception {
        mockMvc.perform(get("/validation-test/string-validation")
                .param("name", "张三")
                .param("phone", "12345"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: 手机号格式不正确"));
    }

    @Test
    void testNumberValidation_ValidParams_ReturnsSuccess() throws Exception {
        mockMvc.perform(get("/validation-test/number-validation")
                .param("age", "25")
                .param("price", "99.99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("数字校验通过: 年龄=25, 价格=99.99"));
    }

    @Test
    void testNumberValidation_InvalidAge_ReturnsValidationError() throws Exception {
        mockMvc.perform(get("/validation-test/number-validation")
                .param("age", "0")
                .param("price", "99.99"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: 年龄必须大于0"));
    }

    @Test
    void testNumberValidation_InvalidPrice_ReturnsValidationError() throws Exception {
        mockMvc.perform(get("/validation-test/number-validation")
                .param("age", "25")
                .param("price", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: 价格必须大于0"));
    }

    @Test
    void testDtoValidation_ValidDto_ReturnsSuccess() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setPassword("Test123456");
        userDTO.setAge(25);
        userDTO.setEmail("test@example.com");
        userDTO.setPhone("13812345678");
        userDTO.setRoles(Arrays.asList("USER"));

        mockMvc.perform(post("/validation-test/dto-validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("DTO校验通过: testuser"));
    }

    @Test
    void testDtoValidation_InvalidDto_ReturnsValidationError() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(""); // Empty username
        userDTO.setPassword("123"); // Invalid password
        userDTO.setAge(0); // Invalid age
        userDTO.setEmail("invalid-email"); // Invalid email
        userDTO.setPhone("12345"); // Invalid phone
        userDTO.setRoles(Arrays.asList()); // Empty roles

        mockMvc.perform(post("/validation-test/dto-validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCollectionValidation_ValidParams_ReturnsSuccess() throws Exception {
        mockMvc.perform(post("/validation-test/collection-validation")
                .param("tags", "tag1", "tag2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("集合校验通过: [tag1, tag2]"));
    }

    @Test
    void testCollectionValidation_EmptyCollection_ReturnsValidationError() throws Exception {
        mockMvc.perform(post("/validation-test/collection-validation"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: 标签列表不能为空"));
    }

    @Test
    void testPathVariableValidation_ValidId_ReturnsSuccess() throws Exception {
        mockMvc.perform(get("/validation-test/path-variable/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("路径变量校验通过: 1"));
    }

    @Test
    void testPathVariableValidation_InvalidId_ReturnsValidationError() throws Exception {
        mockMvc.perform(get("/validation-test/path-variable/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: ID必须大于0"));
    }

    @Test
    void testEmailValidation_ValidEmail_ReturnsSuccess() throws Exception {
        mockMvc.perform(get("/validation-test/email-validation")
                .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("邮箱校验通过: test@example.com"));
    }

    @Test
    void testEmailValidation_InvalidEmail_ReturnsValidationError() throws Exception {
        mockMvc.perform(get("/validation-test/email-validation")
                .param("email", "invalid-email"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("参数校验失败: 邮箱格式不正确"));
    }

    @Test
    void testBooleanValidation_ValidStatus_ReturnsSuccess() throws Exception {
        mockMvc.perform(get("/validation-test/boolean-validation")
                .param("status", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("布尔值校验通过: true"));
    }

    @Test
    void testDateTimeValidation_ValidFutureTime_ReturnsSuccess() throws Exception {
        LocalDateTime futureTime = LocalDateTime.now().plusDays(1);
        String formattedTime = futureTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        mockMvc.perform(get("/validation-test/datetime-validation")
                .param("appointmentTime", formattedTime))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("成功"))
                .andExpect(jsonPath("$.data").value("日期时间校验通过: " + futureTime.toString().replace("T", " ")));
    }
}