package com.faceye.feature.repository.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.faceye.feature.doc.Sequence;
import com.faceye.feature.repository.mongo.SequenceRepository;

@Repository
public class SequenceRepositoryImpl implements SequenceRepository {
	@Autowired
	private MongoOperations mongoOps = null;

	@Override
	public Sequence getSequenceByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		Sequence sequence = this.mongoOps.findOne(query, Sequence.class);
		return sequence;
	}

	@Override
	public Sequence getSequence(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Sequence sequence = this.mongoOps.findById(id, Sequence.class);
		return sequence;
	}

	@Override
	public void save(Sequence sequence) {
		if (sequence != null) {
			this.mongoOps.save(sequence);
		}
	}

	@Override
	public Sequence getSequenceWithNext(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		Update update = new Update().inc("seq", 1);
		Sequence sequence = this.mongoOps.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Sequence.class);
		if (null == sequence) {
			sequence = new Sequence();
			sequence.setId(System.currentTimeMillis());
			sequence.setName(name);
			sequence.setSeq(1L);
			sequence.setStep(new Long(1));
			this.mongoOps.save(sequence);
		}
		return sequence;
	}

}
