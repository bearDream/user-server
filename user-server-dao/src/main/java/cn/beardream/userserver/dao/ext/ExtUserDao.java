package cn.beardream.userserver.dao.ext;

import cn.beardream.core_common.model.User;
import org.apache.ibatis.annotations.Param;

public interface ExtUserDao {

    User login(@Param("username") String username, @Param("password") String password);

    int updateByUsernameSelective(User user);

}