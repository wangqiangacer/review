package com.jacken.redission.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class StockController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;


    @RequestMapping("/reduceStock")
    public  String reduceStock(){
        String lockKey="lockKey";
//        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "wq");
//        if(!result){
//            return "error";
//        }
        String clientId= UUID.randomUUID().toString();
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
        if(!result){
            return "error";
        }
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                int realStock=stock-1;
                stringRedisTemplate.opsForValue().set("stock",realStock+"");
                System.out.println(Thread.currentThread().getName()+"\t 扣减成功，库存剩余"+realStock);
            }else {
                System.out.println("扣减失败，库存不足");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if(clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))){

                stringRedisTemplate.delete(lockKey);
            }
        }


        // stringRedisTemplate.delete(lockKey);

        return "end";
    }



    @RequestMapping("/redissionStock")
    public  String reduceRedissionStock(){
        String lockKey="lockKey";
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                int realStock=stock-1;
                stringRedisTemplate.opsForValue().set("stock",realStock+"");
                System.out.println(Thread.currentThread().getName()+"\t 扣减成功，库存剩余"+realStock);
            }else {
                System.out.println("扣减失败，库存不足");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return "end";
    }
}
