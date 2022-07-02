package com.anomander.noSql.task1.service.impl;

import com.anomander.noSql.task1.model.UserCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class AggregationService {

    @Autowired
    MongoTemplate mongoTemplate;

    public int countUsersWithBalanceGreaterThan(Double balance) {
        MatchOperation filterUsers = match(new Criteria("balance").gt(balance));
        CountOperation countUsers = count().as("count");

        Aggregation aggregation = newAggregation(filterUsers, countUsers);

        AggregationResults<UserCount> result = mongoTemplate
                .aggregate(aggregation, "userAccount", UserCount.class);

        return result.getUniqueMappedResult().getCount();
    }

    public int countMoneySpentByUsers() {
        LookupOperation lookupOperation = lookup("event", "eventId", "_id", "eventDetails");
        GroupOperation groupOperation = group("userId").sum("eventDetails.ticketPrice").as("totalSpent");
        Aggregation aggregation = newAggregation(lookupOperation, groupOperation);

        System.out.println("aggregation" + aggregation);
        return 1;
    }
}
