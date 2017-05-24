package com.faceye.feature.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Http {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static CloseableHttpClient httpClient = null;
	private static int BUFFER_SIZE = 8 * 1024;
	private Map<String, String> headers = new HashMap<String, String>(0);
	private String useragent = "";
	private static Http INSTANCE = null;
	private PoolingHttpClientConnectionManager cm = null;
	
	private final static String USER_AGENT="Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20100101 Firefox/8.0";

	private Http() {
		cm = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加到200
		cm.setMaxTotal(200);
		// 将每个路由基础的连接增加到20
		cm.setDefaultMaxPerRoute(20);
		// 将目标主机的最大连接数增加到50
		// HttpHost localhost = new HttpHost("www.baidu.com", 80);
		// cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public synchronized static Http getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Http();
		}
		return INSTANCE;
	}

	public synchronized CloseableHttpClient getClient() {
		return httpClient;
	}

	public String get(String url, String charset) {
		String res = "";
		HttpGet httpget = null;
		CloseableHttpResponse response = null;
		InputStream in = null;
		byte[] content = null;
		try {
			httpget = this.initHttpGet(url);
			HttpContext context = HttpClientContext.create();
			response = httpClient.execute(httpget, context);
			HttpEntity entity = response.getEntity();
			if (StringUtils.isEmpty(charset)) {
				charset = ContentType.getOrDefault(entity).getCharset().displayName();
			}
			in = entity.getContent();
			Header[] heads = response.getAllHeaders();
			initHeaders(heads);
			long contentLength = Long.MAX_VALUE;
			if (entity.getContentLength() != -1) {
				contentLength = entity.getContentLength();
			}
			byte[] buffer = new byte[BUFFER_SIZE];
			int bufferFilled = 0;
			int totalRead = 0;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((bufferFilled = in.read(buffer, 0, buffer.length)) != -1 && totalRead + bufferFilled <= contentLength) {
				totalRead += bufferFilled;
				out.write(buffer, 0, bufferFilled);
			}
			content = out.toByteArray();
			if (content != null) {
				String contentEncoding = MapUtils.getString(headers, "Content-Encoding");
				if (StringUtils.isNotEmpty(contentEncoding)) {
					if (StringUtils.equals("gzip", contentEncoding.toLowerCase())
							|| StringUtils.equals("x-gzip", contentEncoding.toLowerCase())) {
						content = GZIPUtils.unzipBestEffort(content);
					} else if (StringUtils.equals("deflate", contentEncoding.toLowerCase())) {
						content = DeflateUtils.deflate(content);
					}
				}
			}
		} catch (ClientProtocolException ex) {
			logger.error(">>FaceYe error,url is :" + httpget.getURI().toASCIIString());
		} catch (IOException ex) {
			logger.error(">>FaceYe error,url is :" + httpget.getURI().toASCIIString());
		} catch (HttpException e) {
			logger.error(">>Get Url Content Throws Exception :" + e.toString() + ",url is:" + httpget.getURI().toString());
		} catch (Exception e) {
			logger.error(">>Get Url Content Throws Exception :" + e.toString() + ",url is:" + httpget.getURI().toString());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(">>--->" + e.getMessage());
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(">>--->" + e.getMessage());
				}
			}
//			this.destroy();
			if(null!=content){
				try {
					res=new String(content,charset);
				} catch (UnsupportedEncodingException e) {
					logger.error(">>--->"+e.toString());
				}
			}
		}
		return res;
	}

	private void initRequestConfig(HttpGet httpget) {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectionRequestTimeout(30000)
				.setConnectTimeout(30000).build();
		httpget.setConfig(requestConfig);
	}

	private HttpGet initHttpGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		this.initRequestConfig(httpGet);
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("User-Agent",USER_AGENT);
		// 用逗号分隔显示可以同时接受多种编码
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		return httpGet;
	}

	/**
	 * 初始化post请求
	 * @todo
	 * @param url
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年12月29日
	 */
	private HttpPost initHttpPost(String url) {
		HttpPost post = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectionRequestTimeout(30000)
				.setConnectTimeout(30000).build();
		post.setConfig(requestConfig);
		// 用逗号分隔显示可以同时接受多种编码
		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		post.setHeader("User-Agent",USER_AGENT);
		post.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		post.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		post.setHeader("Keep-Alive", "300");
		return post;
	}

	/**
	 * 关闭使用后的链接
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月6日
	 */
	public void destroy() {
		if (cm != null) {
			cm.close();
		}
//		cm = null;
//		httpClient = null;
//		INSTANCE = null;
	}

	private void initHeaders(Header[] heads) throws Exception {
		if (MapUtils.isNotEmpty(headers)) {
			headers.clear();
		}
		if (heads != null && heads.length > 0) {
			for (int i = 0; i < heads.length; i++) {
				Header header = heads[i];
				String name = header.getName();
				String value = header.getValue();
				headers.put(name, value);
			}
		}
	}

	/**
	 * POST提交
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年12月29日
	 */
	public String post(String url, String charset, Map<String,String> params) {
		String res = "";
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;
		InputStream in = null;
		byte[] content = null;
		try {
			httpPost=this.initHttpPost(url);
			List<NameValuePair> nvps = map2NameValuePair(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			HttpContext context = HttpClientContext.create();
			response = httpClient.execute(httpPost, context);
			
			HttpEntity entity = response.getEntity();
			if (StringUtils.isEmpty(charset)) {
				charset = ContentType.getOrDefault(entity).getCharset().displayName();
			}
			in = entity.getContent();
			Header[] heads = response.getAllHeaders();
			initHeaders(heads);
			long contentLength = Long.MAX_VALUE;
			if (entity.getContentLength() != -1) {
				contentLength = entity.getContentLength();
			}
			byte[] buffer = new byte[BUFFER_SIZE];
			int bufferFilled = 0;
			int totalRead = 0;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((bufferFilled = in.read(buffer, 0, buffer.length)) != -1 && totalRead + bufferFilled <= contentLength) {
				totalRead += bufferFilled;
				out.write(buffer, 0, bufferFilled);
			}
			content = out.toByteArray();
			if (content != null) {
				String contentEncoding = MapUtils.getString(headers, "Content-Encoding");
				if (StringUtils.isNotEmpty(contentEncoding)) {
					if (StringUtils.equals("gzip", contentEncoding.toLowerCase())
							|| StringUtils.equals("x-gzip", contentEncoding.toLowerCase())) {
						content = GZIPUtils.unzipBestEffort(content);
					} else if (StringUtils.equals("deflate", contentEncoding.toLowerCase())) {
						content = DeflateUtils.deflate(content);
					}
				}
			}
		} catch (ClientProtocolException ex) {
			logger.error(">>FaceYe error,url is :" + httpPost.getURI().toASCIIString());
		} catch (IOException ex) {
			logger.error(">>FaceYe error,url is :" + httpPost.getURI().toASCIIString());
		} catch (HttpException e) {
			logger.error(">>Get Url Content Throws Exception :" + e.toString() + ",url is:" + httpPost.getURI().toString());
		} catch (Exception e) {
			logger.error(">>Get Url Content Throws Exception :" + e.toString() + ",url is:" + httpPost.getURI().toString());
		} finally {
			if(null!=content){
				try {
					res=new String(content,charset);
				} catch (UnsupportedEncodingException e) {
					logger.error(">>FaceYe throws Exception: --->"+e.toString());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(">>--->" + e.getMessage());
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(">>--->" + e.getMessage());
				}
			}
			if(httpPost !=null){
				httpPost.abort();
			}
//			this.destroy();
		}
		return res;
	}
	
	/**
	 * 组装参数
	 * 
	 * @param params
	 * @return
	 */
	private  List<NameValuePair> map2NameValuePair(Map<String, String> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (MapUtils.isNotEmpty(params)) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = MapUtils.getString(params, key);
				//if (null!=value &&StringUtils.isNotEmpty(value)&&StringUtils.isNotBlank(value)) {
				if (null!=value) {
					NameValuePair nvp = new BasicNameValuePair(key, value);
					logger.debug(">>HttpCall params:->" + key + "    =   " + value);
					nvps.add(nvp);
				}
			}
		}
		return nvps;
	}
}
