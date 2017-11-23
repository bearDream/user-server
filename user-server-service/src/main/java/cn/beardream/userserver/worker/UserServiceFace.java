package cn.beardream.userserver.worker;

import cn.beardream.core_common.model.ResponseBody;
import cn.beardream.core_common.model.User;
import cn.beardream.core_common.model.vo.LoginVo;
import cn.beardream.core_common.utils.MD5Utils;
import cn.beardream.core_common.utils.ResponseBodyUtil;
import cn.beardream.userserver.dao.gen.UserDao;
import cn.beardream.userserver.face.IUserServiceFace;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 2017/10/29
 *
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
@Service
public class UserServiceFace implements IUserServiceFace {

    @Autowired
    private UserDao mUserDao;

    @Override
    public ResponseBody login(LoginVo o) {
        User user = mUserDao.login(o.getUsername(), o.getPassword());
        if (user == null){
            return ResponseBodyUtil.error(-1001,"登陆失败","用户名和密码不正确，请检查用户名和密码");
        }
        // 为了方便测试，暂时后端加密密码； 实际应该前端先加密的
        user.setPassword(MD5Utils.GetMD5Code(o.getPassword()));
        return ResponseBodyUtil.success(JSON.toJSONString(user));
    }

    @Override
    public ResponseBody logout(LoginVo o) {
        return ResponseBodyUtil.success("注销成功");
    }

    @Override
    public ResponseBody add(User user) {
        if (user.getUsername() == null || user.getPassword() == null){
            return ResponseBodyUtil.error("用户名或密码不能为空");
        }
        user.setPassword(MD5Utils.GetMD5Code(user.getPassword()));
        Integer rows = mUserDao.insertSelective(user);
        return rows == 1 ? ResponseBodyUtil.success("添加成功") : ResponseBodyUtil.error("添加失败");
    }

    @Override
    public ResponseBody alter(User user) {
        if (user.getUsername() == null){
            return ResponseBodyUtil.error("用户名不能为空");
        }
        Integer rows = mUserDao.updateByUsernameSelective(user);
        return rows == 1 ? ResponseBodyUtil.success("修改成功") : ResponseBodyUtil.error("修改失败");
    }

    @Override
    public ResponseBody loadUserById(Integer userId) {
        if (Objects.isNull(userId)){
            return ResponseBodyUtil.error("userId不能为空");
        }
        User user = mUserDao.selectByPrimaryKey(userId);
        return Objects.isNull(user) ? ResponseBodyUtil.error("userId为" + userId + "的用户不存在") : ResponseBodyUtil.success(JSON.toJSONString(user));
    }
}
