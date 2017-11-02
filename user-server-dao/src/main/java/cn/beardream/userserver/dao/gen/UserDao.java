package cn.beardream.userserver.dao.gen;

import cn.beardream.core_common.model.User;
import cn.beardream.userserver.dao.ext.ExtUserDao;

public interface UserDao extends ExtUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}