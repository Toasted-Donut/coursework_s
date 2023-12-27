package com.example.project_p.controls;

import com.example.project_p.models.Category;
import com.example.project_p.models.Goods;
import com.example.project_p.requests.AddRequest;
import com.example.project_p.requests.FindRequest;
import com.example.project_p.services.CategoryService;
import com.example.project_p.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final CategoryService categoryService;

    @Autowired
    public GoodsController(GoodsService goodsService, CategoryService categoryService){
        this.goodsService = goodsService;
        this.categoryService = categoryService;
    }
    //find by ID
    @GetMapping(params = "id",produces = "application/json")
    public ResponseEntity<Optional<Goods>> getGoodsByID(@RequestParam Long id){
        Optional<Goods> goods = Optional.ofNullable(goodsService.findById(id));
        return ResponseEntity.ok(goods);
    }
    //find all
    @GetMapping(produces = "application/json")
    public ResponseEntity<Optional<List<Goods>>> getGoods(){
        Optional<List<Goods>> goods = Optional.ofNullable(goodsService.findAll());
        return ResponseEntity.ok(goods);
    }
    //find all with zero category (to be marked)
    @GetMapping(value = "/tbm",produces = "application/json")
    public  ResponseEntity<Optional<List<Goods>>> getGoodsUndef(){
        Optional<List<Goods>> goods = Optional.ofNullable(goodsService.findByZeroCategory());
        return ResponseEntity.ok(goods);
    }
    //find by name
    @PostMapping(value = "/",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Optional<Goods>> getGoodsByName(@RequestBody FindRequest request){
        Optional<Goods> goods = Optional.ofNullable(goodsService.findByName(request.name));
        return ResponseEntity.ok(goods);
    }
    //add goods
    @PostMapping(value = "/add",consumes = "application/json")
    public ResponseEntity<Goods> addGoods(@RequestBody AddRequest request){
        Category category = categoryService.findById(request.category_id);
        if(category==null)return ResponseEntity.badRequest().build();
        Goods goods = new Goods();
        goods.setName(request.name);
        goods.setCategory(category);
        Goods savedGoods = goodsService.save(goods);
        return ResponseEntity.created(URI.create("/"+savedGoods.getId())).body(savedGoods);
    }
    //update by ID (category)
    @PutMapping(value = "/upd")
    public ResponseEntity<Goods> updateByID(@RequestParam Long id, @RequestParam Long category_id){
        Category category = categoryService.findById(category_id);
        Goods goods = goodsService.findById(id);
        if(category==null) return ResponseEntity.badRequest().build();
        if(goods==null) return ResponseEntity.badRequest().build();
        goods.setCategory(category);
        Goods updatedGoods = goodsService.save(goods);
        return ResponseEntity.created(URI.create("/"+updatedGoods.getId())).body(updatedGoods);
    }
    //delete by id
    @DeleteMapping(params = "id",produces = "application/json")
    public ResponseEntity<Goods> deleteByID(@RequestParam Long id){
        goodsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
