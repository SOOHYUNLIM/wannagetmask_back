package com.wannagetmask.repository;

import com.wannagetmask.domain.Target;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TargetCrawledRepository extends MongoRepository<Target, String> {

}
