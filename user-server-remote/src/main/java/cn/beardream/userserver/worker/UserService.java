package cn.beardream.userserver.worker;

import cn.beardream.core_common.annotate.LoginCheck;
import cn.beardream.core_common.model.ResponseBody;
import cn.beardream.core_common.model.User;
import cn.beardream.core_common.model.vo.LoginVo;
import cn.beardream.userserver.face.IUserServiceFace;
import cn.beardream.userserver.remote.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/25
 *
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
@RestController
@RequestMapping
public class UserService implements IUserService {

    @Autowired
    private IUserServiceFace mIUserServiceFace;

    @Override
    @PostMapping(value = "/login")
    public ResponseBody login(@Validated LoginVo o) {
        return mIUserServiceFace.login(o);
    }

    @Override
    @PostMapping(value = "/logout")
    public ResponseBody logout(LoginVo o) {
        return mIUserServiceFace.logout(o);
    }

    @Override
    @PostMapping
    public ResponseBody add(User user) {
        return mIUserServiceFace.add(user);
    }

    @Override
    @PutMapping
    public ResponseBody alter(User user) {
        return mIUserServiceFace.alter(user);
    }
}
