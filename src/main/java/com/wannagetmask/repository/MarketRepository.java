package com.wannagetmask.repository;

import com.wannagetmask.domain.Market;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketRepository extends MongoRepository<Market, String> {
}
