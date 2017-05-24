package com.faceye.component.@component.name@.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.feature.repository.BaseRepository;
/**
 * @entity.name@ 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface @entity.name@Repository extends BaseRepository<@entity.name@,Long> {
	
	
}/**@generate-repository-source@**/
