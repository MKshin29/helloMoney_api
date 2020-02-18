package com.vlasov.demo_api.repos;

import com.vlasov.demo_api.model.Balance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BalanceRepository extends CrudRepository<Balance, Integer> {
    List<Balance> findByUserid(int userid);
    Balance getByUserid(int userid);
}
