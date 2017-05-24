package com.faceye.component.security.service;

import com.faceye.component.security.entity.User;
import com.faceye.feature.service.BaseService;

public interface UserService extends BaseService<User,Long>{

	
	/**
	 * 为用户授权
	 * @todo
	 * @param userId
	 * @param roleIds
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月23日
	 */
	public void saveUserAuthRoles(Long userId,Long[] roleIds);
	
	/**
	 * 根据用户名取得用户
	 * @todo
	 * @param username
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月27日
	 */
	public User getUserByUsername(String username);
	/**
	 * 根据电子邮件取得用户
	 * @todo
	 * @param email
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月27日
	 */
    public User getUserByEmail(String email);
	
	
}/**@generate-service-source@**/
