package com.faceye.component.security.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
/**
 * Resource ORM 实体
 * 数据库表:security_resource
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月21日
 */
@Entity
@Table(name="security_resource")
public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

   /**
    * 说明:名称
    * 属性名: name
    * 类型: String
    * 数据库字段:name
    * @author haipenge
    */
    @Column(name="name")
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
   /**
    * 说明:地址
    * 属性名: url
    * 类型: String
    * 数据库字段:url
    * @author haipenge
    */
    @Column(name="url")
	private  String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

	
   /**
    * 说明:角色
    * 属性名: roles
    * 类型: Set<Role>
    * 数据库字段:role_id
    * @author haipenge
    */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "security_role_resource", joinColumns = { @JoinColumn(name = "resource_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
    @Column(name="role_id")
	private  Set<Role> roles=new HashSet<Role>(0);
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Collection<ConfigAttribute> getAttributes(){
		Collection<ConfigAttribute> attributes=new HashSet<ConfigAttribute>();
		if(null!=this.getRoles()){
			Iterator it=this.getRoles().iterator();
			while(it.hasNext()){
				Role role=(Role) it.next();
				ConfigAttribute ca=new SecurityConfig(role.getRoleAuth());
				attributes.add(ca);
			}
		}
		return attributes;
	}
	
	@OneToOne(optional = true, cascade = CascadeType.REMOVE, mappedBy = "resource")
	private Menu menu=null;
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	
	
}/**@generate-entity-source@**/
	
