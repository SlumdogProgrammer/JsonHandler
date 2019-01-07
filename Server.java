import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    ServerSocket server;
    Socket client;
    static long time;
    Server(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("Сервер: Порт " + port + " открыт для прослушивания");
    }
    void Connect() throws IOException {
        client = server.accept();
        System.out.println("Сервер: Подключился новый клиент");
    }
    void ReadJson() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        time=0;
        String inputLine;
        ArrayList<Student>student=new ArrayList<>(10);

        System.out.println("Сервер: принимаем сообщение от клиента");

        while (!(inputLine = reader.readLine()).isEmpty() )
        {
            Gson gson = new Gson();
            long start = System.nanoTime();
            student.add(gson.fromJson(inputLine, Student.class));
            long end=System.nanoTime();
            time +=end-start;

        }

    }
}