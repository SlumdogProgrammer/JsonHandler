import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Student
{

    private String _Name;
    private String _Surname;
    private int _Age;
    private String _Department;
    private String[] _SelectedSubjects;
    private int[] _Marks;
    private LinkedList<Student> _Student;

    private Student(int depth)
    {
        _Name="Yuri";
        _Surname="Bolotin";
        _Age=0x14;
        _Department="IPOVS";
        _SelectedSubjects=new String[] {"Java"};
        _Marks=new int[] {5};
        LinkedList<Student> =new LinkedList<>()
        for (int i=0;i<depth;i++)

    }

    public Student(String name,String surname,int age,String department,String[] selectedSubjects,int[] marks,int depth)
    {
        _Name=name;
        _Surname=surname;
        _Age=age;
        _Department=department;
        _SelectedSubjects=selectedSubjects;
        _Marks=marks;
        _Student=new LinkedList<>();
        _Student.add(new Student());
    }

    public void Stringer(LinkedList<Student> LS)
    {
        for(int i=0;i<LS.size();i++)
            System.out.println(LS.get(i));
    }
    @Override
    public String toString() {
        Stringer(_Student);
        return "Имя "+_Name+
                " Фамилия "+_Surname+
                " Возраст "+_Age+
                " Факультет "+_Department+
                " Выбранные предметы "+Arrays.toString(_SelectedSubjects)+
                " Оценки "+Arrays.toString(_Marks);}
}
