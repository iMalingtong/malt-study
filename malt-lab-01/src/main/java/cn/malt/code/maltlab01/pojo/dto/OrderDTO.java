package cn.malt.code.maltlab01.pojo.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author 蜡笔不小新Rm
 */
@Data
public class OrderDTO {

    @NotBlank(message = "订单号不能为空")
    private String orderNo;

    @NotNull(message = "收货地址不能为空")
    @Valid  // 嵌套对象需要@Valid
    private AddressDTO address;

    @NotEmpty(message = "商品列表不能为空")
    @Valid  // List中的对象也需要@Valid
    private List<ProductDTO> products;
    
    @Data
    public static class AddressDTO {

        @NotBlank(message = "收货人不能为空")
        private String receiver;

        @NotBlank(message = "手机号不能为空")
        @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
        private String phone;

        @NotBlank(message = "详细地址不能为空")
        private String detail;
    }

    @Data
    public static class ProductDTO {
        @NotBlank(message = "商品名称不能为空")
        @Length(min = 2, max = 20, message = "商品名称长度必须在2-20之间")
        private String name;

        @NotBlank(message = "商品描述不能为空")
        @Length(min = 2, max = 50, message = "商品描述长度必须在2-50之间")
        private String description;

        @NotNull(message = "商品价格不能为空")
        @Min(value = 1, message = "商品价格必须大于0")
        private BigDecimal price;

        @NotNull(message = "商品数量不能为空")
        @Min(value = 1, message = "商品数量必须大于0")
        private Integer quantity;

        @NotBlank(message = "商品图片不能为空")
        private String image;

        @NotBlank(message = "商品分类不能为空")
        private String category;
    }
}