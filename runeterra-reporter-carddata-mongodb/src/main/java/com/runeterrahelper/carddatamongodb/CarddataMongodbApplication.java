package com.runeterrahelper.carddatamongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.apache.commons.logging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;

@SpringBootApplication
public class CarddataMongodbApplication {

  private static final Log log = LogFactory.getLog(CarddataMongodbApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CarddataMongodbApplication.class, args);
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "runeterra");

    log.info(mongoOps.findOne(new Query(where("cardCode").is("01DE001")), Card.class, "cards"));
  }
}
