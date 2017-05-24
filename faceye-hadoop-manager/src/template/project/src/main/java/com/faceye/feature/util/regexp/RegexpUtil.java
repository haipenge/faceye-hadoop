package com.faceye.feature.util.regexp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegexpUtil {
	private static Logger logger=LoggerFactory.getLogger(RegexpUtil.class);
	public static List<Map<String, String>> match(String content, String regexp) throws Exception {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		//pattern.DOTALL,跨行
		Pattern patern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher matcher = patern.matcher(content);
		while (matcher.find()) {
			Map<String, String> map = new HashMap<String, String>(0);
			int group = matcher.groupCount();
			if (group > 0) {
				for (int i = 1; i <= group; i++) {
					String match=matcher.group(i);
					map.put("" + i, match);
				}
			}
			res.add(map);
		}
		return res;
	}
	
	
	public static boolean isMatch(String content,String regexp){
		boolean res=false;
		Pattern patern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
		Matcher matcher = patern.matcher(content);
		res= matcher.find();
		return res;
		
	}
	
	
	public static void print(List<Map<String,String>> matches){
		if(CollectionUtils.isNotEmpty(matches)){
			int index=0;
			for(Map<String,String>map:matches){
				Iterator<String> keyIterator=map.keySet().iterator();
				while(keyIterator.hasNext()){
					String key=keyIterator.next();
					String value=map.get(key);
					logger.debug(">>Facey:"+(++index)+"------>"+key+" = "+value);
				}
			}
		}else{
			logger.debug(">>FaceYe:have not got any result.");
		}
	}
	
	
	
	
	
}
