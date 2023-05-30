package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<MemeEntity, String> {
    List<MemeEntity> findAllByOrderByIdDesc();
    MemeEntity findFirstByNameAndUrlAndCaption(String name, String url, String caption);
}
