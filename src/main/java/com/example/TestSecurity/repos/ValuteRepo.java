package com.example.TestSecurity.repos;

import com.example.TestSecurity.model.Valute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * @author karpeykin
 * @Date 25.01.2021
 */
public interface ValuteRepo extends CrudRepository<Valute, Long> {
    List<Valute> findByDate(String date);
    Valute findByCharCode(String charCode);
}
