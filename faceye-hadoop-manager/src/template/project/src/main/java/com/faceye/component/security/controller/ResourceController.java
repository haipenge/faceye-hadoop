package com.faceye.component.security.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.security.entity.Resource;
import com.faceye.component.security.service.ResourceService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;

@Controller
@Scope("prototype")
@RequestMapping("/security/resource")
public class ResourceController extends BaseController<Resource, Long, ResourceService> {

	@Autowired
	public ResourceController(ResourceService service) {
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
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<Resource> page = this.service.getPage(searchParams, this.getPage(searchParams), this.getSize(searchParams));
		model.addAttribute("page", page);
		return "security.resource.manager";
	}

	/**
	 * 转向编辑或新增页面
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		if (id != null) {
			Resource entity = this.service.get(id);
			model.addAttribute("resource", entity);
		}
		return "security.resource.update";
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
	@RequestMapping(value = "/input")
	public String input(Model model) {
		return "security.resource.update";
	}

	/**
	 * 数据保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Resource entity, RedirectAttributes redirectAttributes) {
		Resource resource = null;
		if (entity != null && entity.getId() != null) {
			resource = this.service.get(entity.getId());
			resource.setName(entity.getName());
			resource.setUrl(entity.getUrl());
		} else {
			resource = entity;
		}
		this.service.save(resource);
		return "redirect:/security/resource/home";
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
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/security/resource/home";
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
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Resource entity = this.service.get(id);
			model.addAttribute("resource", entity);
		}
		return "security.resource.detail";
	}

}
