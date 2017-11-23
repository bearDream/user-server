package cn.beardream.userserver.face;

import cn.beardream.core_common.model.ResponseBody;
import cn.beardream.core_common.model.User;
import cn.beardream.core_common.model.vo.LoginVo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 2017/10/29
 *
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
public interface IUserServiceFace {

    ResponseBody login(LoginVo o);

    ResponseBody logout(LoginVo o);

    ResponseBody add(User user);

    ResponseBody alter(User user);

    ResponseBody loadUserById(Integer userId);
}
