package com.vlasov.demo_api.repos;

import com.vlasov.demo_api.model.History;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Integer> {
    public List<History> findAllByFromUser(int id);
    public List<History> findAllByToUser(int id);
    public List<History> findByDateLessThanEqualAndDateGreaterThanEqual(Date date1, Date date2);
}
