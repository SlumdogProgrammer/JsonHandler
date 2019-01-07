import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    Socket server;

    Client(String ip, int port) throws IOException {
        System.out.println("Клиент: подключаемся к серверу");
        server = new Socket(ip, port);
    }

    void WriteJson(int count,int depth) throws JSONException, IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

        Student[] student = Generate.generateStudents(count,depth);
        Gson json=new Gson();
        StringBuilder jsonString=new StringBuilder();
        for(int i=0;i<count;i++)
        {
           // JSONObject jsonObject =new  JSONObject();
           // jsonObject.put("1JSON",student[i]);
            jsonString.append(json.toJson(student[i])+"\n");
            //studentS[i]=   (Student) jsonObject.get("1JSON");
            //jsonString.append(jsonObject+"\n");
        }

        /*for(int i=0;i<10;i++)
            System.out.println(studentS[i]);*/


        System.out.println("Клиент: отправляем сообщение на сервер");
        writer.write(jsonString+"\n");
        writer.flush();

    }
}