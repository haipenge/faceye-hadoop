package com.faceye.feature.util;

import java.util.HashMap;
import java.util.Map;

public class StringPool {
	public static final String JPA_COUNT_QL_SUFFIX = "_count";

	public static final String SECURITY_RESOURCE_TYPE_URL = "URL";
	public static final String SECURITY_RESOURCE_TYPE_USER_BLOG_MANAGE_URL="USER_BLOG_MANAGE_URL";
	public static final String SECURITY_ANONYMOUS_USER_NAME = "anonymousUser";
	public static final String SECURITY_GUEST_USERNAME="guest";
	public static final String SECURITY_USER_IN_SESSION = "FACEYE_SECURITY_USER_IN_SESSION";
	public static final String SECURITY_USER_IS_AT_MY_SELEF_PLACE = "SECURITY_USER_IS_AT_MY_SELEF_PLACE";
	
	public static final String BLOG_VIEW_TYPE_VIEW="view";
	public static final String BLOG_VIEW_TYPE_ADMIN="admin";

	public static final String CHARACTER_COMMA = ",";
	public static final String CHARACTER_AND = "&";
	public static final String CHARACTER_SAME = "=";
	public static final String CHARACTER_MIDDLE_LEFT = "[";
	public static final String CHARACTER_MIDDLE_RIGHT = "]";
	public static final String CHARACTER_LARGE_LFET = "{";
	public static final String CHARACTER_LARGE_RIGHT = "}";
	public static final String CHARACTER_COLON = ":";

	public static final String REFLECTION_METHOD_JSON = "json";
	public static final String REFLECTION_METHOD_MAP = "map";

	public static final String TREE_PARENT_ID = "parentid";
	public static final String TREE_PARENT_NAME = "parentname";
	public static final String TREE_NAME = "name";
	public static final String TREE_LABEL = "label";
	public static final String TREE_TEXT = "text";
	public static final String TREE_CLS = "cls";
	public static final String TREE_ICON_CLS = "iconCls";
	public static final String TREE_HREF = "href";
	public static final String TREE_TARGET = "target";
	public static final String TREE_STYLE = "style";
	public static final String TREE_ID = "id";
	public static final String TREE_IS_LEAF = "isLeaf";
	public static final String TREE_LEAF = "leaf";
	public static final String TREE_ORDER="order";
	public static final String TREE_ROOT_ID = "root";
	public static final String TREE_NODE_ID_PARAMETER = "node";

	public static final String ENTITY_ID = "id";
	public static final String ENTITY_IDS = "ids";
	
	public static final String UNDEFINED="undefined";

	/**
	 * navigation category show position
	 */
	public static final String NAV_POSITION_LEFT = "left";
	public static final String NAV_POSITION_RIGHT = "right";
	public static final String NAV_POSITION_HIDDEN = "hidden";
	public static final String NAV_POSITION_CENTER = "center";
	public static final String NAV_POSITION_BOTTOM = "bottom";

	public static final String NAV_URL_MOVE_TYPE_UP = "up";
	public static final String NAV_URL_MOVE_TYPE_DOWN = "down";

	public static final String AJAX_REQUEST = "ajaxRequest";
	public static final String AJAX_ACTION_REQUEST = "ajax-action-request";
	
	public static final String CHARACTER_SPLIT_LINE="_";
	
	//分页
	public static final String PAGE_PAGE_SIZE="pageSize";
	public static final String PAGE_START_INDEX="startIndex";
	public static final String PAGE_START="start";
	public static final String PAGE_LIMIT="limit";

	/**
	 * 时间周期(毫秒)
	 */
	public static final Long TIME_PERIOD_2_HOUR = 2 * 60 * 60 * 1000L;
	public static final Long TIME_PERIOD_DAY = 24 * 60 * 60 * 1000L;
	public static final Long TIME_PERIOD_WEEK = 7 * 24 * 60 * 60 * 1000L;
	public static final Long TIME_PERIOD_MONTH = 30 * 24 * 60 * 60 * 1000L;
	public static final Long TIME_PERIOD_YEAR = 365 * 24 * 60 * 60 * 1000L;

	public static final String TIME_2_HOUR="2HOUR";
	public static final String TIME_DAY="DAY";
	public static final String TIME_WEEK="WEEK";
	public static final String TIME_MONTH="MONTH";
	public static final String TIME_YEAR="YEAR";
	public static final Map<String,Long> PERIOD = new HashMap<String,Long>();
	static {
		PERIOD.put("2HOUR", TIME_PERIOD_2_HOUR);
		PERIOD.put("DAY", TIME_PERIOD_DAY);
		PERIOD.put("WEEK", TIME_PERIOD_WEEK);
		PERIOD.put("MONTH", TIME_PERIOD_MONTH);
		PERIOD.put("YEAR", TIME_PERIOD_YEAR);
	}
	

}
