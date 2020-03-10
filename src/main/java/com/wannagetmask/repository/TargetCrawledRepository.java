package com.wannagetmask.repository;

import com.wannagetmask.domain.TargetCrawled;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TargetCrawledRepository extends MongoRepository<TargetCrawled, String> {

}
