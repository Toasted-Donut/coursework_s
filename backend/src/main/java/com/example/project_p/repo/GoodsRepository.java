package com.example.project_p.repo;

import com.example.project_p.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
    Optional<Goods> findFirstByName(String name);

    @Query(value = "SELECT * FROM appDB.goods where category_id = 0", nativeQuery = true)
    List<Goods> findByZeroCategory();
}
