package cn.hn.java.cms.api.admin.user.bean;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

@Data
public class User {

	@NotBlank(message="名称不能为空")
	private String name;
}
