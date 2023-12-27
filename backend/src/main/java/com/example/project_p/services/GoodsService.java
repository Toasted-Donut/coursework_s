package com.example.project_p.services;

import com.example.project_p.models.Goods;
import com.example.project_p.services.base.BaseCRUDService;

import java.util.List;


public interface GoodsService extends BaseCRUDService<Goods,Long> {
    Goods findByName(String name);
    List<Goods> findByZeroCategory();
}
