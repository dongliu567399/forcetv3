package mianshi.service;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.jws.WebService;
import java.io.*;

@WebService
public class QualityComplainServices {

    //ftp对象
    private FTPClient ftp;
    //需要连接到的ftp端的ip
    private String ip = "10.46.249.7";
    //连接端口，默认21
    private int port = 21;
    //要连接到的ftp端的名字
    private String name = "DKEDI";
    //要连接到的ftp端的对应得密码
    private String pwd = "P@ssw0rd";


    //调用此方法，输入对应得ip，端口，要连接到的ftp端的名字，要连接到的ftp端的对应得密码。连接到ftp对象，并验证登录进入fto
    public boolean ftp1() {
        ftp = new FTPClient();
        try {
//          ftp.connect(ip, port);
            if(!ftp.isConnected()){
                ftp.connect(ip, port);
            }
            System.out.println(ftp.login(name, pwd));
//          ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }

    }

    public void disconnect() throws Exception {
        if (ftp.isConnected()) {
            ftp.disconnect();
        }
    }

    // 下载文件到本地
    public boolean download(FTPFile file) throws Exception {
        boolean result = true;
        // 本地文件路径
        File f = new File("E:\\crmFiles\\");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        }
        long lRemoteSize = file.getSize();
        try {// 下载过的不在下载了
            OutputStream out = new FileOutputStream(f);
            if (f.length() >= lRemoteSize) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~本地已经存在,下载中止");
                out.flush();
                out.close();
            }
            boolean iss = ftp.retrieveFile(file.getName(), out);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~下载成功\r\n");
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~下载失败\r\n");
            return false;
        }
        return result;
    }

    private InputStreamReader read;
    private BufferedReader reader;

    private String preRead(String filepath) throws Exception {
        File file = new File(filepath);
        String ordertype = null;
        if (file.isFile() && file.exists()) {
            try {
                read = new InputStreamReader(new FileInputStream(file), "GBK");
                reader = new BufferedReader(read);
                StringBuffer FileContent = new StringBuffer();
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    FileContent.append(temp);
                }
                System.out.println("订单内容为------------------>>>>> "+FileContent+" <<<<<------------------");
            } catch (FileNotFoundException e) {
                System.out.println("！！！！！！！！！！！！！！！！！没有找到合适的订单信息！！！！！！！！！！！！！！！");
                e.printStackTrace();
            } finally {
                reader.close();
                read.close();
//				file.delete();
            }
        }
        return ordertype;
    }

    public void gmRead(String remote) throws Exception {
        boolean downloadResult = false;
        try {
            ftp.changeWorkingDirectory(remote);
            System.out.println("远程路径为*************************"+remote);
            FTPFile[] files = ftp.listFiles(remote); // 通过路径得到文件
            System.out.println("文件数量为*************************"+files.length);
            for (int i = 0; i < files.length; i++) {
                FTPFile file = files[i];
                if (file.isFile()) {
                    downloadResult = this.download(file);// 下载文件 到本地读取路径
                    if (downloadResult) {
                        String ordertype = this.preRead("E:\\crmFiles\\");
                    }
					/*//读取文件内容，将内容存数据库
					InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
					BufferedReader br = new BufferedReader(isr);
					String lineTxt = null;
					while ((lineTxt = br.readLine()) != null) {
						lineTxt+=lineTxt;
					}
					System.out.println(lineTxt);
					br.close();*/
                }else{
                    System.out.println("************* 文件不存在 ************");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String threeDAndEightDReports(String orderNum, String FTPUrl, String FileType) {
        //抱怨单号、FTP地址、3D/8D文件类型
        System.out.println("1-------------"+orderNum);
        System.out.println("2-------------"+FTPUrl);
        System.out.println("3-------------"+FileType);
        if(null != orderNum && null != FTPUrl && null != FileType){
            //连接FTP
            boolean flag = this.ftp1();
            if(flag){
                try {
                    //获取文件、解析文件内容，进库操作
                    this.gmRead(FTPUrl);
                    // 关闭连接
                    this.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("！！！！！！！！！！！！！！！！！FTP连接失败！！！！！！！！！！！！！！！！！");
            }

            return "success";
        }else{
            return "fail";
        }
    }
}
