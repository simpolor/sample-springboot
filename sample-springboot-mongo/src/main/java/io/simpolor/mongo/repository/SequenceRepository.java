package io.simpolor.mongo.repository;

import io.simpolor.mongo.exception.SequenceException;

public interface SequenceRepository {

    long getNextSequenceId(String key) throws SequenceException;

}
