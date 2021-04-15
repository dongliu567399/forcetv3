package mianshi;


import jdk.nashorn.internal.parser.Parser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@MapperScan("mianshi.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MianshiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MianshiApplication.class, args);}


//        String filepath = "C:\\Users\\DELL\\Desktop\\23.log";//文件地址
//        File file = new File(filepath);
//        List<Integer> list=new ArrayList<>();
//        for (int i = 1; i <=20 ; i++) {
//            list.add(i);
//        }
//        BufferedReader bufferedReader;
//        try {
//            bufferedReader = new BufferedReader(new FileReader(file));
//            String tempString = "";
//            //创建一个map     KEY 是存 用户唯一码  value 存出现的次数
//            Map<String, Integer> map = new HashMap<>();
//            while ((tempString = bufferedReader.readLine()) != null) {
//                if (tempString.equals("END")) {
//                    continue;
//                }
//                String[] booting3 = tempString.split(",");
//                if (booting3[2] != null) {
//                    //如果map  key中不存在这个用户唯一码   就创建出来 默礽出现次数是0
//                    if (!map.containsKey(booting3[2])) {
//                        map.put(booting3[2], 0);
//                    }
//                    //如果已经存在   加1次  这样就算出每个用户唯一码出现的次数
//                    if (map.containsKey(booting3[2])) {
//                        int a = map.get(booting3[2]);
//                        map.put(booting3[2], a + 1);
//                    }
//                }
//            }
//            Map <String,Integer> result= new HashMap<>();
//            for (int i = 0; i <list.size() ; i++) {
//                result.put(list.get(i)+"+",0);
//            }
//            for(String key:map.keySet()){
//                int a=map.get(key);
//                for (int i = 1; i <=a ; i++) {
//                    if(i>20){
//                        break;
//                    }
//                    int b=result.get(i+"+");
//                    result.put(i+"+",b+1);
//                }
//            }
//            for(String key:result.keySet()){
//                System.out.println("---key"+key+"  value :  "+result.get(key));
//            }
//
//            bufferedReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
/*


    public static Integer getMaxValue(Map<String, Integer> map) {
        if (map == null)
            return null;
        int length =map.size();
        Collection<Integer> c = map.values();
        Object[] obj = c.toArray();
        Arrays.sort(obj);
        return Integer.parseInt(obj[length-1].toString());
    }
}

 */
}
