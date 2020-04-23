package tom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class uploadDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);  //绑定10086端口
        Socket accept = serverSocket.accept(); //侦听要连接的请求

        InetAddress inetAddress = accept.getInetAddress();
        System.out.println(inetAddress);

        //写入流
        InputStream inputStream = accept.getInputStream();

        FileOutputStream fos = new FileOutputStream("D:\\ShareProject\\projectTest\\src\\tom\\Reposent.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }
        accept.getOutputStream().write("上传成功".getBytes());

        fos.close();
        accept.close();
        serverSocket.close();
    }
}
