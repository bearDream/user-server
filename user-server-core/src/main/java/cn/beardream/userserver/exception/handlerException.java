package cn.beardream.userserver.exception;

import cn.beardream.core_common.model.ResponseBody;
import cn.beardream.core_common.utils.ResponseBodyUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 2017/10/31
 *
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
@ControllerAdvice
public class handlerException {

    /**
     * 如果有业务异常也可以在这里抛出
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @org.springframework.web.bind.annotation.ResponseBody
    private ResponseBody exception(Exception e){
        return ResponseBodyUtil.error(e.getLocalizedMessage());
    }
}
