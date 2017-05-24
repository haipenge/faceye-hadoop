package com.faceye.feature.service;

import com.faceye.feature.doc.Sequence;

/**
 * 序列服务，当前为mogo提供序列服务
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年8月10日
 */
public interface SequenceService {
	public Long getNextSequence(String name);

	public Sequence getSequence(Long id);

	public Sequence getSequenceByName(String name);

	public void save(Sequence sequence);
}
