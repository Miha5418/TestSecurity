package com.example.TestSecurity.repos;

import com.example.TestSecurity.model.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepo extends CrudRepository<History, Long> {

    List<History> findByValuteFromLikeAndValuteToLikeAndDateLike(String valuteFrom, String valuteTo, String date);
}
