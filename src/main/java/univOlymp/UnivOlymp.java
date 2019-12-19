package univOlymp;

import model.Pupil;
import student.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UnivOlymp {
    public static ArrayList<Student> loadData(String filename) throws FileNotFoundException {
        ArrayList<Student> lst = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                String a = br.readLine();
                String[] s = a.split(" ");
                Student h;
                h = new Student(s[0], s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));
                lst.add(h);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            System.out.println("input students");
        }
        return lst;
    }

    /**
     * Bыведите номера школ, из которых был хотя бы один участник олимпиады, в порядке убывания количества участников олимпиады из этих школ.
     * Если из двух школ было одинаковое число участников, то их номера выводятся в порядке возрастания номера школы.
     */
    public static ArrayList<Integer> getSchoolLeaders(ArrayList<Student> lst) {
        Map<Integer, Integer> s = op(lst);
        ArrayList<Map.Entry<Integer, Integer>> yui = new ArrayList<>(s.entrySet());
        yui.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<Integer, Integer> uiop = new LinkedHashMap<>();
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < yui.size(); i++)
            uiop.put(yui.get(i).getKey(), yui.get(i).getValue());

        for (Map.Entry<Integer, Integer> li : uiop.entrySet()) {
            l.add(li.getKey());
        }
        return l;
    }

    /**
     * Выведите номера школ, из которых был хотя бы один участник олимпиады, в порядке убывания среднего балла участников олимпиады из этих школ.
     * Если для двух школ средний балл участников совпадает, то их номера выводятся в порядке возрастания номера школы.
     */
    public static ArrayList<Integer> getSchoolByAverPoint(ArrayList<Student> lst) {
        Map<Integer, Integer[]> k = new HashMap<>();
        for (int i = 0; i < lst.size(); i++) {
            Integer[] w = k.get(lst.get(i).getUniver());
            if (w != null) {
                Integer[] mass = new Integer[2];
                mass[0] = 1 + k.get(lst.get(i).getUniver())[0];
                mass[1] = lst.get(i).getPoint() + k.get(lst.get(i).getUniver())[1];
                k.put(lst.get(i).getUniver(), mass);
            } else {
                Integer[] mass = new Integer[2];
                mass[0] = 1;
                mass[1] = lst.get(i).getPoint();
                k.put(lst.get(i).getUniver(), mass);
            }
        }
        Map<Integer, Double> j = new HashMap<>();
        for (Map.Entry<Integer, Integer[]> st : k.entrySet()) {
            j.put(st.getKey(), (1.0 * st.getValue()[1]) / (st.getValue()[0]));
        }
        ArrayList<Map.Entry<Integer, Double>> yui = new ArrayList<>(j.entrySet());
        yui.sort(new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<Integer, Double> uiop = new LinkedHashMap<>();
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < yui.size(); i++)
            uiop.put(yui.get(i).getKey(), yui.get(i).getValue());

        for (Map.Entry<Integer, Double> li : uiop.entrySet()) {
            l.add(li.getKey());
        }
        return l;
    }

    private static Map<Integer, Integer> op(ArrayList<Student> lst) {
        Map<Integer, Integer> s = new HashMap<>();
        for (Student aLst : lst) {
            if (s.get(aLst.getUniver()) != null) {
                int count = s.get(aLst.getUniver());
                s.put(aLst.getUniver(), ++count);
            } else
                s.put(aLst.getUniver(), 1);
        }
        return s;
    }


}
