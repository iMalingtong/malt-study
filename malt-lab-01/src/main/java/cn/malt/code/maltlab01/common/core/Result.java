package cn.malt.code.maltlab01.common.core;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author 蜡笔不小新Rm
 */
@Data
@AllArgsConstructor
public class Result {

    /**
     * 代码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result }
     */
    public static Result success(Object data) {
        return new Result("200", "成功", data);
    }

    /**
     * 成功
     *
     * @return {@link Result }
     */
    public static Result success() {
        return new Result("200", "成功", null);
    }

    /**
     * 错误
     *
     * @param code 代码
     * @param msg  消息
     * @return {@link Result }
     */
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }
}
