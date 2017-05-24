package com.faceye.feature.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.feature.doc.Sequence;
import com.faceye.feature.repository.mongo.SequenceRepository;
import com.faceye.feature.service.SequenceService;

@Service
public class SequenceServiceImpl  implements SequenceService {
	
	
	private Logger logger=LoggerFactory.getLogger(getClass());
    
	@Autowired
	private SequenceRepository sequenceRepository=null;
	/**
	 * 针对Mongo的序列服务实现
	 */
	@Override
	synchronized public Long getNextSequence(String name) {
		Long res = null;
		if (StringUtils.isEmpty(name)) {
			logger.error(">>FaceYe -- > ,sequence name is empty now.");
		}
		Sequence sequence = this.sequenceRepository.getSequenceWithNext(name);
		res=sequence.getSeq();
		return res;
	}
	
	@Override
	public Sequence getSequence(Long id) {
		return this.sequenceRepository.getSequence(id);
	}
	
	@Override
	public void save(Sequence sequence) {
		this.sequenceRepository.save(sequence);
	}

	@Override
	public Sequence getSequenceByName(String name) {
		Sequence sequence=this.sequenceRepository.getSequenceByName(name);
		if(sequence==null){
			sequence = new Sequence();
			sequence.setId(System.currentTimeMillis());
			sequence.setName(name);
			sequence.setSeq(1L);
			sequence.setStep(new Long(1));
			this.sequenceRepository.save(sequence);
		}
		return sequence;
	}

}
