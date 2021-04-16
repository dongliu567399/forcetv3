package mianshi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class UvController {
    String fileName = "";
    String uploadFolder = "";

    @ResponseBody
    @RequestMapping("/UvGet")
    public Integer cale1() {
        String filename1 = uploadFolder + System.getProperty("file.separator") + fileName;
        System.out.println(filename1);
        int map = halder(filename1);
        return map;
    }

    public Integer halder(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader;
        int sum = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString = "";
            while ((tempString = bufferedReader.readLine()) != null) {
                if (tempString.equals("END")) {
                    continue;
                }
                String[] booting3 = tempString.split(",");
                if (booting3[2] != null) {
                    sum++;
                }
            }
            System.out.println("sss------" + sum);
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    @PostMapping("/Uvupload")
    public void upload (HttpServletRequest request, @RequestParam MultipartFile file){
        //upload文件夹所在Tomcat的部署路径（绝对路径）
        uploadFolder = request.getServletContext().getRealPath("upload");
        File folder = new File(uploadFolder);
        System.out.println(folder);
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
