package program;

import model.Pupil;
import olymp.Olymp;
import student.Student;
import univOlymp.UnivOlymp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



//полгая херня!!!!!!!!1
public class Program {
    public static void main(String[] args) {
        /*try
        {
            File in = new File(args[0]);
            File out = new File(in.getParentFile().getAbsolutePath() + "\\s_" + in.getName());
            ArrayList<Pupil> pupils = Olymp.loadData(args[0]);
            Sort.sortReversePoint(pupils);
            System.out.println(out.getAbsolutePath());
            DataWriter dw = new DataWriter(out.getAbsolutePath(), pupils);
            dw.printData();
            Set<Integer> n = Sort.getCountofSchoolLeders(pupils);
            System.out.println(n);

        } catch (FileNotFoundException e) {
            System.out.println("file is not found");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*Map<Integer, String> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        map.put(1, "fff");
        map.put(2, "fddff");
        map.put(0, "ert");
        map.put(4, "eddrt");
        System.out.println(map);*/
        /*try {
            ArrayList<Student> stud = UnivOlymp.loadData("tui.txt");
            ArrayList<Integer> l = UnivOlymp.getSchoolByAverPoint(stud);
            System.out.println(l);
        } catch (FileNotFoundException e) {
            System.out.println("no file");
        }*/

       /* Date date = new Date();
        System.out.println(date);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(format.format(date));

        String from = "15.12.1998";
        try {
            Date parse = format.parse(from);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        try {


            ArrayList<Pupil> list = Olymp.loadData("tui.txt");
            Object o = Olymp.getCountScoreSemiWinners(list);
           if(o instanceof Integer){
               Integer a = (Integer)o;
               System.out.println(a);
           }
           else{
               Pupil h = (Pupil)o;
               System.out.println(h);
           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
