package com.faceye.feature.util.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import com.faceye.feature.util.http.HttpUtil;

public class PageTag extends SimpleTagSupport {
	public Page page = null;
	public Map params = null;
	public String url = null;
	// 要排除不加入URL中的参数，主要用于URLRewrite中的格式化参数，多个参数间以“,"分隔
	public String without = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getWithout() {
		return without;
	}

	public void setWithout(String without) {
		this.without = without;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (null != page && CollectionUtils.isNotEmpty(page.getContent())) {
			StringBuffer sb = new StringBuffer();
			// int startIndex = page.get
			// int pageSize = page.getPageSize().intValue();
			long totalCount = page.getTotalElements();
			int size = page.getSize();

			// int nextIndex = page.getNextIndex().intValue();
			// int previousIndex = page.getPreviousIndex().intValue();
			int totalPage = page.getTotalPages();
			// 0,1,2,3...
			int currentPage = page.getNumber();
			currentPage=currentPage+1;
			int start = 1;
			int end = totalPage;
			// sb.append("<div class=\"default-page-container\">");
			sb.append("<ul class=\"pagination\">");
			if (currentPage - 4 >= 0) {
				start = currentPage - 4;
			}
			if (currentPage + 4 < totalPage) {
				end = currentPage + 4;
			}
			String url = "";
			if (currentPage >= 5) {
				url = this.recombineUrl(1, size);
				// sb.append("<span class=\"blue\"><a href=\"" + url + "\">1</a></span>");
//				sb.append(" <li><a href=\"" + url + "\">&laquo;</a></li>");
				sb.append(" <li><a href=\"" + url + "\">1</a></li>");
				if (currentPage >= 6) {
					// sb.append("<span class=\"force-font-size-11\">...</span>");
					sb.append(" <li><a>...</a></li>");
				}
			} else {
				if (currentPage > 1) {
					url = this.recombineUrl(1, size);
					// sb.append("<span class=\"force-font-size-11\"><a href=\"" + url
					// + "\" class=\"yellowgreen\" style=\"font-size:normal;\">&lt;&lt;</a></span>");
					sb.append(" <li><a href=\"" + url + "\">&laquo;</a></li>");
				}
			}

			for (int i = start; i <= end; i++) {
				url = "";
				if (i == currentPage) {
					// focus-bg
					// sb.append("<span class=\"focus-bg\">");
					// sb.append(i + 1);
					// sb.append("</span>");
					sb.append(" <li class=\"active\"><a href=\"#\">" + (i) + "<span class=\"sr-only\">(current)</span></a></li>");
				} else {
					url += this.recombineUrl(i, size);
					// sb.append("<span><a href=\"");
					// sb.append(url);
					// sb.append("\" class=\"blue\">");
					// sb.append(i + 1);
					// sb.append("</a></span>");

					sb.append(" <li><a href=\"" + url + "\">" + (i) + "</a></li>");
				}
			}
			// 显示最后一页
			if (totalPage > end && (currentPage + 5) < totalPage) {
				url = this.recombineUrl(totalPage, size);
				// sb.append("<span class=\"blue\">...</span>");
				// sb.append("<span class=\"blue\"><a href=\"" + url + "\">" + totalPage + "</a></span>");
				sb.append(" <li><a>...</a></li>");
				sb.append(" <li><a href=\"" + url + "\">" + totalPage + "</a></li>");
			}

			if (currentPage < totalPage ) {
				url = this.recombineUrl(totalPage, size);
				// sb.append("<span class=\"force-font-size-11\"><a href=\"" + url
				// + "\" class=\"yellowgreen\"  style=\"font-size:normal;\">&gt;&gt;</a></span>");
				sb.append(" <li><a href=\"" + url + "\">&raquo;</a></li>");
			}
			sb.append("</ul>");
			out.print(sb.toString());
		}

	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	/**
	 * 重组URL
	 * 
	 * @return
	 */
	private String recombineUrl(int pageNumber, int size) {
		Map map = HttpUtil.getRequestParams(params);
		String resultUrl = this.getUrl();
		JspContext jspContext = getJspContext();
		PageContext pageContext = (PageContext) jspContext;
		// ServletContext tagServlet = pageContext.getServletContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String contextPath = request.getContextPath();
		if (null == map) {
			map = new HashMap();
		}
		map.put("page", pageNumber);
		if (size != 0) {
			map.put("size", size);
		}

		if (MapUtils.isNotEmpty(map)) {
			if (!resultUrl.endsWith("?") && !resultUrl.contains("&")) {
				resultUrl += "?";
			}

			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				if (this.isParamsMapKeyInWithout(key)) {
					continue;
				}
				String value = MapUtils.getString(map, key);
				if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
					resultUrl += key + "=" + value;
					resultUrl += "&";
				}
			}
			if (resultUrl.endsWith("&")) {
				resultUrl = resultUrl.substring(0, resultUrl.lastIndexOf("&"));
			}
		}
		if (resultUrl.startsWith("/")) {
			resultUrl = contextPath + resultUrl;
		}
		return resultUrl;
	}

	/**
	 * 参数 名是否在排除列表之内
	 * 
	 * @return
	 */
	private boolean isParamsMapKeyInWithout(String paramKey) {
		boolean res = Boolean.FALSE;
		if (StringUtils.isNotEmpty(this.getWithout()) && StringUtils.isNotEmpty(paramKey)) {
			String[] withouts = this.getWithout().split(",");
			if (withouts != null && withouts.length > 0) {
				for (int i = 0; i < withouts.length; i++) {
					String key = withouts[i];
					if (StringUtils.equals(key.toLowerCase(), paramKey.toLowerCase())) {
						res = Boolean.TRUE;
						break;
					}
				}
			}
		}
		return res;
	}

}
