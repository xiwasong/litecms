package cn.hn.java.cms.api.admin.install;

import org.springframework.stereotype.Service;

import cn.hn.java.core.db.BaseDao;
import cn.hn.java.core.db.multiple.DataSource1;

@Service
public class InstallService extends BaseDao<DataSource1>{

	public void createTables(){
		this.update("create table tb_user( name varchar(20) )");
		this.update("insert into tb_user(name) values('test')");
	}
}
