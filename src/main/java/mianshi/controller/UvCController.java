package mianshi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class UvCController {
    String fileName = "";
    String uploadFolder = "";
    Map<String, Integer> result = new HashMap<>();
    @ResponseBody
    @RequestMapping("/UVCget")
    public Map<String, Integer> cale1(String seat, String num4) {
        System.out.println(seat);
        System.out.println(num4);
        String filename1 = uploadFolder + System.getProperty("file.separator") + fileName;
        Map<String, Integer> map = halder(filename1, seat, num4);
        return map;
    }

    public Map<String, Integer> halder(String fileName, String seat, String num4) {
        File file = new File(fileName);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(i);
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString = "";
            //创建一个map     KEY 是存 用户唯一码  value 存出现的次数
            Map<String, Integer> map = new HashMap<>();
            while ((tempString = bufferedReader.readLine()) != null) {
                if (tempString.equals("END")) {
                    continue;
                }
                String[] booting3 = tempString.split(",");
                String[] time = booting3[1].split(":");
                String[] num = num4.split(",");//前台传过来的时间段 接收 然后分隔，判断
                if (seat.equals("1")) {
                    for (int i = 0; i < num.length; i++) {//3
                        if (i < 10) {
                            num[i] = "0" + num[i];
                        }
                        if (time[1].equals(num[i]) || time[1].equals(num[i]) || time[1].equals(num[i]) || time[1].equals(num[i])) {
                            if (booting3[0].equals("booting_2.gif") || booting3[0].equals("booting_2.png") ||
                                    booting3[0].equals("booting_2.jpg") || booting3[0].equals("hd_booting_2.jpg") || booting3[0].equals("hd_booting_2.png")) {
                                if (booting3[2] != null) {
                                    //如果map  key中不存在这个用户唯一码   就创建出来 默礽出现次数是0
                                    if (!map.containsKey(booting3[2])) {
                                        map.put(booting3[2], 0);
                                    }
                                    //如果已经存在   加1次  这样就算出每个用户唯一码出现的次数
                                    if (map.containsKey(booting3[2])) {
                                        int a = map.get(booting3[2]);
                                        map.put(booting3[2], a + 1);
                                    }
                                }
                            }
                        }
                        if (seat.equals("2")) {
                            if (time[1].equals(num[i]) || time[1].equals(num[i]) || time[1].equals(num[i]) || time[1].equals(num[i])) {
                                if (booting3[0].equals("booting_3.gif") || booting3[0].equals("booting_3.png") ||
                                        booting3[0].equals("booting_3.jpg") || booting3[0].equals("hd_booting_3.jpg") || booting3[0].equals("hd_booting_3.png")) {
                                    if (booting3[2] != null) {
                                        //如果map  key中不存在这个用户唯一码   就创建出来 默礽出现次数是0
                                        if (!map.containsKey(booting3[2])) {
                                            map.put(booting3[2], 0);
                                        }
                                        //如果已经存在   加1次  这样就算出每个用户唯一码出现的次数
                                        if (map.containsKey(booting3[2])) {
                                            int a = map.get(booting3[2]);
                                            map.put(booting3[2], a + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < list.size(); i++) {
                result.put(list.get(i) + "+", 0);
            }
            for (String key : map.keySet()) {
//                System.out.println("---key" + key + "  Value :  " + map.get(key));
                int a = map.get(key);
                for (int i = 1; i <= a; i++) {
                    if (i > 20) {
                        break;
                    }
                    int b = result.get(i + "+");
                    result.put(i + "+", b + 1);
                }
            }
            for (String key : result.keySet()) {
                System.out.println("---key" + key + "  value :  " + result.get(key));
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @PostMapping("/UVCupload")
    public void upload(HttpServletRequest request, @RequestParam MultipartFile file) {
        //upload文件夹所在Tomcat的部署路径（绝对路径）
        uploadFolder = request.getServletContext().getRealPath("upload");
        File folder = new File(uploadFolder);
        //如果没有存在该文件夹就创建出来
        if (folder.exists() == false) {
            folder.mkdirs();
        }
        //拿到上传文件的原始文件名
        fileName = file.getOriginalFilename();

        //截取原文件名。后面的文件格式
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        //使用UUID进行文件重命名，保证文件不会因为重名而覆盖
        fileName = UUID.randomUUID().toString() + prefix;
        File file1 = new File(folder, fileName);
        try {
            //文件数据存在文件内
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
