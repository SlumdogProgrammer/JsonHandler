import java.util.Random;

public class Generate
{
    public static Student[] Students;
    public static int Depth;
    static Student[] generateStudents(int count,int depth)
    {
        Students=new Student[count];
        Random random=new Random();
        for(int i=0;i<count;i++)
            Students[i]=
            (new Student
                    (
                        generateString(1)[0],
                        generateString(1)[0],
                        18 + random.nextInt(7),
                        generateString(1)[0],
                        generateString( depth ) ,
                        generateMarks( depth )
                    )
            );
        return Students;
    }

    static String[] generateString(int count)
    {
        String[] strings=new String[count];
        for(int i=0;i<count;i++)
        {
            Random random=new Random();
            int k=7+random.nextInt(7);
            StringBuilder buff = new StringBuilder();
            for(int j=0;j<k;j++)
            {
                buff.append((char) (1040+random.nextInt(32)));
            }
            strings[i]=buff.toString();
        }
        return strings;
    }

    static int[] generateMarks(int count)
    {
        Random random=new Random();
        int[] marks=new int[count];
        for (int i=0;i<count;i++)
            marks[i]=2+random.nextInt(3);
        return marks;
    }
}
