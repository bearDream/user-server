package cn.beardream.userserver.exception;

import cn.beardream.core_common.enums.ReturnCodeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 2017/10/31
 * 参数不合法异常
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
public class ParamException extends RuntimeException{

    @Getter
    @Setter
    private Integer code;

    public ParamException(ReturnCodeEnum returnCodeEnum){
        super(returnCodeEnum.getMsg());
        this.code = returnCodeEnum.getCode();
    }


}
