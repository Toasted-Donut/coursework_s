package com.example.project_p.services.impl;

import com.example.project_p.models.Goods;
import com.example.project_p.repo.GoodsRepository;
import com.example.project_p.services.GoodsService;
import com.example.project_p.services.utils.UpdateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository){this.goodsRepository = goodsRepository;}

    @Override
    public Goods save(Goods item) {
        return goodsRepository.save(item);
    }

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public Goods updateById(Long id, Goods item) {
        Goods oldItem = findById(id);
        if (oldItem == null){
            return null;
        }
        UpdateFields.updateField(item,oldItem);
        return save(oldItem);
    }

    @Override
    public List<Goods> saveAll(List<Goods> items) {
        return goodsRepository.saveAll(items);
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public Goods findByName(String name) {
        return goodsRepository.findFirstByName(name).orElse(null);
    }

    @Override
    public List<Goods> findByZeroCategory() {
        return goodsRepository.findByZeroCategory();
    }
}
