package com.esprit.usermicroservice.repositories;

import com.esprit.usermicroservice.entities.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByToken(String token);
}
