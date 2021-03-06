package mianshi.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class BootingController {
    String fileName = "";
    String uploadFolder = "";

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        if (username.equals("dyy") && password.equals("123")) {
            return "booting";
        } else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return "11111";
    }


    @ResponseBody
    @RequestMapping("/cale1")
    public Set<Map.Entry<String,Integer>> cale1(String type) {
        String filename1 = uploadFolder + System.getProperty("file.separator") + fileName;
        System.out.println(filename1);
        Map<String, Integer> map = halder(filename1, type);
        Map<String, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
        sortedMap.entrySet().forEach(System.out::println);
        return sortedMap.entrySet();
    }

    public Map<String, Integer> halder(String fileName, String type) {
        Map<String, Integer> result = new HashMap<>();
        File file = new File(fileName);
        BufferedReader bufferedReader;
        try {
            String tempString = "";
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((tempString = bufferedReader.readLine()) != null) {
                try {
                    if (tempString.equals("END")) {
                        continue;
                    }
                    String[] booting3 = tempString.split(",");
                    String[] time = booting3[1].split(":");
                    if (booting3[0].equals(type + ".gif") || booting3[0].equals(type + ".png") ||
                            booting3[0].equals(type + ".jpg") || booting3[0].equals("hd_" + type + ".jpg") || booting3[0].equals("hd_" + type + ".png")) {
                        if (!result.containsKey(time[1])) {
                            result.put(time[1], 0);
                        }
                        if (result.containsKey(time[1])) {
                            int a = result.get(time[1]);
                            result.put(time[1], a + 1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/upload")
    public void upload(HttpServletRequest request, @RequestParam MultipartFile file) {
        //upload???????????????Tomcat?????????????????????????????????
        uploadFolder = request.getServletContext().getRealPath("upload");
        File folder = new File(uploadFolder);
        System.out.println(folder);
        //?????????????????????????????????????????????
        if (folder.exists() == false) {
            folder.mkdirs();
        }
        //????????????????????????????????????
        fileName = file.getOriginalFilename();

        //??????????????????????????????????????????
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        //??????UUID???????????????????????????????????????????????????????????????
        fileName = UUID.randomUUID().toString() + prefix;
        File file1 = new File(folder, fileName);
        try {
            //???????????????????????????
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}