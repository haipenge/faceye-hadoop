package com.faceye.component.security.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.security.entity.Menu;
import com.faceye.component.security.entity.Role;
import com.faceye.component.security.service.MenuService;
import com.faceye.component.security.service.ResourceService;
import com.faceye.component.security.service.RoleService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;

@Controller
@Scope("prototype")
@RequestMapping("/security/menu")
public class MenuController extends BaseController<Menu, Long, MenuService> {

	@Autowired
	private ResourceService resourceService=null;
	@Autowired
	private RoleService roleService=null;
	@Autowired
	public MenuController(MenuService service) {
		super(service);
	}

	/**
	 * 首页
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		Page<Menu> page = this.service.getPage(searchParams, this.getPage(searchParams),this.getSize(searchParams));
		model.addAttribute("page", page);
		return "security.menu.manager";
	}

	/**
	 * 转向编辑或新增页面
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		if(id!=null){
			Menu entity=this.service.get(id);
			model.addAttribute("menu", entity);
		}
		List<Menu> roots=this.service.getRoots();
		model.addAttribute("roots", roots);
		return "security.menu.update";
	}
	
	/**
	 * 转向新增页面
	 * @todo
	 * @param model
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月27日
	 */
	@RequestMapping(value="/input")
	public String input(Model model){
		List<Menu> roots=this.service.getRoots();
		model.addAttribute("roots", roots);
		return "security.menu.update";
	}
	

	/**
	 * 数据保存
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Map params=HttpUtil.getRequestParams(request);
		this.service.saveMenu(params);
		return "redirect:/security/menu/home";
	}

	/**
	 * 数据删除
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(id!=null){
			this.service.remove(id);
		}
		return "redirect:/security/menu/home";
	}
	/**
	 * 取得数据明细
	 * @todo
	 * @param id
	 * @param model
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月26日
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			Menu entity=this.service.get(id);
			model.addAttribute("menu", entity);
		}
		return "security.menu.detail";
	}
	/**
	 * 准备转向角色-菜单授权页面
	 * @todo
	 * @param roleId
	 * @param model
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月3日
	 */
	@RequestMapping("/prepareAuthMenus/{roleId}")
    public String prepareAuthMenus(@PathVariable Long roleId,Model model){
    	Role role=this.roleService.get(roleId);
    	List<Menu> menus=this.service.getAll();
    	List<Menu> roots=this.service.getRoots();
    	model.addAttribute("role", role);
    	model.addAttribute("menus", menus);
    	model.addAttribute("roots", roots);
    	return "security.menu.auth";
    }
	
	/**
	 * 菜单，角色授权
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月1日
	 */
	@RequestMapping("/authMenus")
	public String authRoles(HttpServletRequest request,Long roleId,Long[] menuIds){
		this.service.saveAuthRoles(roleId, menuIds);
		return "default.msg";
	}

}
