package cn.hn.java.cms.api.admin.user;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.hn.java.cms.api.admin.user.bean.User;
import cn.hn.java.core.db.BaseDao;
import cn.hn.java.core.db.multiple.DataSource1;

@Service
public class UserService extends BaseDao<DataSource1>{
	
	public List<User> getUsers(User u){
		return this.list("getUsers", User.class, u);
	}
}