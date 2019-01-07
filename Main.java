import org.json.JSONException;

import java.io.IOException;

public class Main
{
    public static void main(String args[ ])throws IOException, JSONException {



Student[] std=Generate.generateStudents(100,5);
for (int i=0;i<std.length;i++)
    System.out.println(std[i].toString());


    }
}
