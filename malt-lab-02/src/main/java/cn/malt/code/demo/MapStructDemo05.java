package cn.malt.code.demo;


import cn.malt.code.mapstruct.ProductMapStruct;
import cn.malt.code.pojo.dto.ProductDTO;
import cn.malt.code.pojo.vo.ProductVO;

import java.math.BigDecimal;

/**
 * 类型转换
 * MapStruct
 *
 * @author 蜡笔不小新Rm
 */
public class MapStructDemo05 {

    public static void main(String[] args) {
        System.out.println("========== 测试1：字符串 → 数值（String to BigDecimal）==========");
        testStringToNumber();

        System.out.println("\n========== 测试2：数值 → 字符串（BigDecimal to String）==========");
        testNumberToString();

        System.out.println("\n========== 测试3：不同格式测试 ==========");
        testDifferentFormats();
    }

    /**
     * 测试：字符串转数值
     */
    private static void testStringToNumber() {
        // 创建商品VO（价格为字符串格式）
        ProductVO productVO = ProductVO.builder()
                .id(1L)
                .name("苹果 iPhone 15 Pro")
                .priceStr("$999.99")      // 带美元符号
                .discountStr("$899.50")   // 折扣价
                .build();

        System.out.println("原始VO数据：");
        System.out.println("  priceStr: " + productVO.getPriceStr() + " (类型: String)");
        System.out.println("  discountStr: " + productVO.getDiscountStr() + " (类型: String)");

        // 使用MapStruct转换
        long startTime = System.currentTimeMillis();
        ProductDTO productDTO = ProductMapStruct.INSTANCE.toDTO(productVO);
        long endTime = System.currentTimeMillis();

        System.out.println("\n转换后DTO数据：");
        System.out.println("  price: " + productDTO.getPrice() + " (类型: BigDecimal)");
        System.out.println("  discount: " + productDTO.getDiscount() + " (类型: BigDecimal)");
        System.out.println("  耗时: " + (endTime - startTime) + "ms");
    }

    /**
     * 测试：数值转字符串
     */
    private static void testNumberToString() {
        // 创建商品DTO（价格为数值类型）
        ProductDTO productDTO = ProductDTO.builder()
                .id(2L)
                .name("华为 Mate 60 Pro")
                .price(new BigDecimal("6999.00"))
                .discount(new BigDecimal("6499.99"))
                .build();

        System.out.println("原始DTO数据：");
        System.out.println("  price: " + productDTO.getPrice() + " (类型: BigDecimal)");
        System.out.println("  discount: " + productDTO.getDiscount() + " (类型: BigDecimal)");

        // 使用MapStruct转换
        long startTime = System.currentTimeMillis();
        ProductVO productVO = ProductMapStruct.INSTANCE.toVO(productDTO);
        long endTime = System.currentTimeMillis();

        System.out.println("\n转换后VO数据：");
        System.out.println("  priceStr: " + productVO.getPriceStr() + " (类型: String)");
        System.out.println("  discountStr: " + productVO.getDiscountStr() + " (类型: String)");
        System.out.println("  耗时: " + (endTime - startTime) + "ms");
    }

    /**
     * 测试：不同价格格式
     */
    private static void testDifferentFormats() {
        // 测试不同格式的价格字符串
        String[] testPrices = {
                "$1234.56",      // 标准格式
                "$12345.99",     // 大额
                "$0.99",         // 小额
                "$10000.00"      // 整千
        };

        System.out.println("测试不同价格格式的转换：");
        for (String priceStr : testPrices) {
            ProductVO vo = ProductVO.builder()
                    .priceStr(priceStr)
                    .build();

            ProductDTO dto = ProductMapStruct.INSTANCE.toDTO(vo);
            ProductVO backToVO = ProductMapStruct.INSTANCE.toVO(dto);

            System.out.printf("  原始: %-15s → BigDecimal: %-10s → 格式化: %s%n",
                    priceStr,
                    dto.getPrice(),
                    backToVO.getPriceStr());
        }
    }

}
