package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StreamRepository extends JpaRepository<Stream,String> {

    Optional<Stream> findByStreamName(String streamName);
}
