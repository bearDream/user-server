package cn.beardream.userserver.remote;

import cn.beardream.core_common.model.ResponseBody;
import cn.beardream.core_common.model.User;
import cn.beardream.core_common.model.vo.LoginVo;

/**
 * 2017/10/24
 *
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
public interface IUserService {

    /**
     * 登陆接口
     * @param o
     * @return
     */
    ResponseBody login(LoginVo o);

    /**
     * 注销接口
     * @return
     */
    ResponseBody logout(LoginVo o);

    /**
     * 注册接口
     * @return
     */
    ResponseBody add(User user);

    /**
     * 修改接口
     * @return
     */
    ResponseBody alter(User user);

}
