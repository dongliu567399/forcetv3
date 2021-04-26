package mianshi;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.data.redis.core.*;

import javax.servlet.MultipartConfigElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@MapperScan("mianshi.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MianshiApplication {

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public void query() {
//
//        String filepath = "C:\\Users\\DELL\\Desktop\\aa\\成都-双流区.log";//文件地址
//        File file = new File(filepath);
//        BufferedReader bufferedReader;
//        String tempString = "";
//        try {
//            bufferedReader = new BufferedReader(new FileReader(file));
//            while ((tempString = bufferedReader.readLine()) != null) {
//                System.out.println(tempString);
////                tempString = "aaaa / ," + tempString + "/," + d + "/,booting1";
////                System.out.println(tempString);
//                redisTemplate.opsForValue().set("aa", tempString);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(MianshiApplication.class, args);
    }
//        MianshiApplication m=new MianshiApplication();
//        m.query();
        //两个素材 一个uv 一共点击6次 1-5 2-4 3-3
//        String filepath = "C:\\Users\\DELL\\Desktop\\aa\\成都-双流区.log";//文件地址
//        File file = new File(filepath);
//        BufferedReader bufferedReader;
//        List<String> list = new ArrayList<>();
//        list.add("aaaaaaa");
//        list.add("bbbbbbb");
//        try {
//            Random r = new Random();
//            int r1=r.nextInt(100) + 1;

//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat();
//            sdf.applyPattern("yyyy,MM,dd");
//            String d = sdf.format(date);
//            String tempString = "";
//            bufferedReader = new BufferedReader(new FileReader(file));
//            while ((tempString = bufferedReader.readLine()) != null) {
//                tempString = "aaaa / ," + tempString + "/," + d + "/,booting1";
//                System.out.println(tempString);
//            }
//            for (int j = 0; j < 6; j++) {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//    /**
//     * 写入缓存
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存设置时效时间
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, timeUnit);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 批量删除对应的value
//     *
//     * @param keys
//     */
//    public void remove(final String... keys) {
//        for (String key : keys) {
//            remove(key);
//        }
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param pattern
//     */
//    public void removePattern(final String pattern) {
//        Set<Serializable> keys = redisTemplate.keys(pattern);
//        if (keys.size() > 0) {
//            redisTemplate.delete(keys);
//        }
//    }
//
//    /**
//     * 删除对应的value
//     *
//     * @param key
//     */
//    public void remove(final String key) {
//        if (exists(key)) {
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     *
//     * @param key
//     * @return
//     */
//    public boolean exists(final String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 读取缓存
//     *
//     * @param key
//     * @return
//     */
//    public Object get(final String key) {
//        Object result = null;
//        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//        result = operations.get(key);
//        return result;
//    }
//
//    /**
//     * 哈希 添加
//     *
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    public void hmSet(String key, Object hashKey, Object value) {
//        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//    }
//
//    /**
//     * 哈希获取数据
//     *
//     * @param key
//     * @param hashKey
//     * @return
//     */
//    public Object hmGet(String key, Object hashKey) {
//        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//        return hash.get(key, hashKey);
//    }
//
//    /**
//     * 列表添加
//     *
//     * @param k
//     * @param v
//     */
//    public void lPush(String k, Object v) {
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        list.rightPush(k, v);
//    }
//
//    /**
//     * 列表获取
//     *
//     * @param k
//     * @param l
//     * @param l1
//     * @return
//     */
//    public List<Object> lRange(String k, long l, long l1) {
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        return list.range(k, l, l1);
//    }
//
//    /**
//     * 集合添加
//     *
//     * @param key
//     * @param value
//     */
//    public void add(String key, Object value) {
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        set.add(key, value);
//    }
//
//    /**
//     * 集合获取
//     *
//     * @param key
//     * @return
//     */
//    public Set<Object> setMembers(String key) {
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        return set.members(key);
//    }
//
//    /**
//     * 有序集合添加
//     *
//     * @param key
//     * @param value
//     * @param scoure
//     */
//    public void zAdd(String key, Object value, double scoure) {
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        zset.add(key, value, scoure);
//    }
//
//    /**
//     * 有序集合获取
//     *
//     * @param key
//     * @param scoure
//     * @param scoure1
//     * @return
//     */
//    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        return zset.rangeByScore(key, scoure, scoure1);
//    }
}
