package com.faceye.component.security.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import com.faceye.feature.util.Json;
import com.faceye.feature.util.StringPool;
/**
 * @author:haipeng
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com
 * @Create 2009-4-10
 * @Package com.faceye.components.security.util
 * @Description:树形结构工具类
 */
public class TreeUtils
{
	private Logger logger=LoggerFactory.getLogger(TreeUtils.class);
	private List trees = null;

	public TreeUtils(List arg0)
	{
		this.trees = this.transTrees(arg0);
	}
/**
 * 转化树结构数据．
 * @param arg0
 * @return
 */
	private List transTrees(List arg0)
	{
		List result = new ArrayList();
		if (CollectionUtils.isNotEmpty(arg0))
		{
			for (int i = 0; i < arg0.size(); i++)
			{
				Object o = arg0.get(i);
				Class clazz = o.getClass();
				if (ClassUtils.hasMethod(clazz, StringPool.REFLECTION_METHOD_MAP, null))
				{
					result.add(ReflectionUtils.invokeMethod(ClassUtils.getMethodIfAvailable(clazz, StringPool.REFLECTION_METHOD_MAP, null), o));
				}
			}
		}
		result = this.addPropertyForExtTree(result);
		result = this.addIsLeafToTreeMap(result);
		return result;
	}

	/**
	 * 取得已经转化好的trees
	 * 
	 * @return
	 */
	public List getTransferedTrees()
	{
		return this.trees;
	}

	/**
	 * 添加isLeaf 到tree map.
	 * 
	 * @param trees
	 * @return
	 */
	private List addIsLeafToTreeMap(List trees)
	{
		List result = new ArrayList();
		if (CollectionUtils.isNotEmpty(trees))
		{
			for (int i = 0; i < trees.size(); i++)
			{
				Map item = (Map) trees.get(i);
				if (this.isHaveChidren(trees, item.get(StringPool.TREE_ID).toString()))
				{
					item.put(StringPool.TREE_IS_LEAF, Boolean.FALSE);
					item.put(StringPool.TREE_LEAF, Boolean.FALSE);
				} else
				{
					item.put(StringPool.TREE_IS_LEAF, Boolean.TRUE);
					item.put(StringPool.TREE_LEAF, Boolean.TRUE);
				}
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * 为ext tree添加text属性 为yui tree 的name或label属性.
	 * 
	 * @param trees
	 * @return
	 */
	private List addPropertyForExtTree(List trees)
	{
		List result = new ArrayList();
		if (CollectionUtils.isNotEmpty(trees))
		{
			for (int i = 0; i < trees.size(); i++)
			{
				Map item = (Map) trees.get(i);
				if (!item.containsKey(StringPool.TREE_TEXT))
				{
					if (item.containsKey(StringPool.TREE_NAME))
					{
						item.put(StringPool.TREE_TEXT, item.get(StringPool.TREE_NAME));
					} else if (item.containsKey(StringPool.TREE_LABEL))
					{
						item.put(StringPool.TREE_TEXT, item.get(StringPool.TREE_LABEL));
					}
				}
				result.add(item);
			}
		}
		return result;
	}

	public String json(List arg0)
	{
		if (CollectionUtils.isEmpty(arg0))
		{
			return null;
		} else
		{
			String json = "";
				json = Json.toJson(arg0);
			return json;
		}
	}

	/**
	 * 取得所有父节点
	 */
	public List getAllParents(List arg0)
	{
		if (CollectionUtils.isEmpty(arg0))
		{
			arg0 = this.trees;
		}
		List result = new ArrayList();
		for (int i = 0; i < arg0.size(); i++)
		{
			Map item = (Map) arg0.get(i);
			if (!this.isHaveParent(arg0, item.get("id").toString()))
			{
				result.add(item);
			}
		}
		return result;
	}

	public boolean isHaveChidren(List trees, String treeId)
	{
		boolean result = false;
		if (CollectionUtils.isEmpty(trees) || StringUtils.isEmpty(treeId))
		{
			return result;
		}
		Iterator it = trees.iterator();
		while (it.hasNext())
		{
			Map item = (Map) it.next();
			if (item.get(StringPool.TREE_PARENT_ID) != null)
			{
				if (item.get(StringPool.TREE_PARENT_ID).toString().equals(treeId))
				{
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * 是否有父节点
	 */

	private boolean isHaveParent(List arg0, String id)
	{
		boolean result = false;
		if (StringUtils.isEmpty(id))
		{
			return result;
		}
		if (CollectionUtils.isEmpty(arg0))
		{
			arg0 = this.trees;
		}
		Map tree = this.getTree(arg0, id);
		if (tree.get(StringPool.TREE_PARENT_ID) == null)
		{
			result = false;
		} else
		{
			Map parentTree = this.getTree(arg0, tree.get(StringPool.TREE_PARENT_ID).toString());
			if (parentTree == null)
			{
				result = false;
			} else
			{
				if (arg0.contains(parentTree))
				{
					result = true;
				} else
				{
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * 取得一个节点.
	 * 
	 * @param arg0
	 * @param id
	 * @return
	 */
	private Map getTree(List arg0, String id)
	{
		if (CollectionUtils.isEmpty(arg0))
		{
			arg0 = this.trees;
		}
		if (CollectionUtils.isNotEmpty(arg0))
		{
			for (int i = 0; i < arg0.size(); i++)
			{
				Map item = (Map) arg0.get(i);
				if (item.get(StringPool.TREE_ID).toString().equals(id))
				{
					return item;
				}
			}
		}
		return null;
	}
/**
 * 取得当前节点下的所有子节点,返回的集合中包括当前节点本身
 * @param source
 * @param treeid
 * @param result
 * @return
 */
	public List getAllChildrenTrees(List source, String treeid, List result)
	{
		List directChildrenTrees = this.getDirectChildrenTrees(source, treeid);
		Map currentTree = this.getTree(source, treeid);
		result.add(currentTree);
		Iterator it = directChildrenTrees.iterator();
		while (it.hasNext())
		{
			Map item = (Map) it.next();
			if (this.isHaveParent(source, item.get(StringPool.TREE_ID).toString()))
			{
				this.getAllChildrenTrees(source, item.get(StringPool.TREE_ID).toString(), result);
			} else
			{
				result.add(item);
			}
		}
		return result;
	}
/**
 * 取得某一节点的所有父节点
 * @param source
 * @param treeid
 * @param result
 * @return
 */
	public List getAllParentTrees(List source, String treeid, List result)
	{
		Map tree = this.getTree(source, treeid);
		result.add(tree);
		if (tree.get(StringPool.TREE_PARENT_ID) != null)
		{
			if (this.getTree(source, tree.get(StringPool.TREE_PARENT_ID).toString()) != null)
			{
				this.getAllParentTrees(source, tree.get(StringPool.TREE_PARENT_ID).toString(), result);
			}
		}
		return result;
	}
/**
 * 如果一个节点有子节点,反回本节点的所有直接子节点
 * @param source
 * @param treeid
 * @return
 */
	public List getDirectChildrenTrees(List source, String treeid)
	{
		if (CollectionUtils.isEmpty(source))
		{
			source = this.trees;
		}
		List result = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext())
		{
			Map item = (Map) it.next();
			if (item.get(StringPool.TREE_PARENT_ID) != null)
			{
				if (item.get(StringPool.TREE_PARENT_ID).toString().equals(treeid))
				{
					result.add(item);
				}
			}
		}
		return result;
	}
/**
 * 取得某一点的直接子节点
 * @param source
 * @param treeid
 * @return
 */
	public Map getDirectParentTree(List source, String treeid)
	{
		Map reuslt = null;
		Map tree = this.getTree(source, treeid);
		if (tree.get(StringPool.TREE_PARENT_ID) != null)
		{
			reuslt = this.getTree(source, tree.get(StringPool.TREE_PARENT_ID).toString());
		}
		return reuslt;
	}
	/**
	 * 取得所有根节点，
	 * @param source
	 * @return
	 */
	public List getRoots(List source)
	{
		List result = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext())
		{
			Map item = (Map) it.next();
			if (!this.isHaveParent(source, item.get(StringPool.TREE_ID).toString()))
			{
				result.add(item);
			}
		}
		return result;
	}
}
