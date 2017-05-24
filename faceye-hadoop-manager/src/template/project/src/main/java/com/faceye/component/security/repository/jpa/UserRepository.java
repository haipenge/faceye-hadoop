package com.faceye.component.security.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.faceye.component.security.entity.User;
import com.faceye.feature.repository.jpa.BaseRepository;
/**
 * User 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
@Repository
public interface UserRepository extends BaseRepository<User,Long> {
	/**
	 * 根据用户名查找用户
	 * @todo
	 * @param username
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月21日
	 */
	public User getUserByUsername(String username);
	
	
	/**
	 * 根据电子邮件查询用户
	 * @todo
	 * @param email
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月21日
	 */
	public User getUserByEmail(String email);
	
	
	
	
}/**@generate-repository-source@**/
