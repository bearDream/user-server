package cn.beardream.userserver.aspect;

import cn.beardream.core_common.annotate.LoginCheck;
import cn.beardream.core_common.enums.ReturnCodeEnum;
import cn.beardream.core_common.model.vo.LoginVo;
import cn.beardream.userserver.exception.ParamException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 2017/10/31
 *
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
//@Aspect
//@Component
public class CheckParamAspect {

    Logger logger = LoggerFactory.getLogger("CheckParamAspect.class");

    @Pointcut(("(execution(* cn.beardream.userserver.worker.*.*(..)))"))
    public void pointCut(){}

    @Around("pointCut()")
    public Object doBefore(ProceedingJoinPoint point){
        logger.info("-------------登陆参数校验-------------");

        Method method = ((MethodSignature) point.getSignature()).getMethod();

        logger.info("-----------拦截的方法：{}", method.getName());

        Annotation[][] annss = method.getParameterAnnotations();

        // 确定方法上是否有LoginCheck注解
        int index = 0;
        loop: for (Annotation[] annotations : annss) {
            for(Annotation ann : annotations){
                if(ann instanceof LoginCheck){
                    break loop;
                }
            }
            index ++;
        }

        Object object = null;
        try {
            if(index != annss.length){
                Object[] args = point.getArgs();
                LoginVo loginVo = (LoginVo) args[0];
                logger.info("username:{}", loginVo.getUsername());
                if (loginVo.getUsername() == null || loginVo.getPassword() == null){
                    throw new ParamException(ReturnCodeEnum.ERRORPARAM);
                }
                logger.info("-------------登陆参数不为空-------------");
            }
            object = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("检查登陆参数出错,原因:{}", throwable.getLocalizedMessage());
            throw new RuntimeException(throwable);
        }
        return object;
    }
}
