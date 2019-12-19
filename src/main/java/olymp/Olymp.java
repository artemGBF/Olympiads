package olymp;

import model.Pupil;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Olymp {
    /**
     * В олимпиаде по информатике принимало участие несколько человек.
     * Информация о результатах олимпиады записана в файле, каждая строка которого имеет вид:
     * фамилия имя класс балл.
     *
     * Фамилия и имя — текстовые строки, не содержащие пробелов.
     * Класс - одно из трех чисел 9, 10, 11. Балл - целое число от 0 до 106.
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Pupil> loadData(String filename) throws FileNotFoundException {
        ArrayList<Pupil> lst = new ArrayList<Pupil>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                String s = br.readLine();
                String[] s1 = s.split(" ");
                Pupil a;
                if (s1.length==5)
                     a = new Pupil(s1[0], s1[1], Integer.parseInt(s1[2]), Integer.parseInt(s1[3]), Integer.parseInt(s1[4]));
                else
                     a = new Pupil(s1[0], s1[1], Integer.parseInt(s1[2]), Integer.parseInt(s1[3]));
                lst.add(a);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            System.out.println("input pupils");
        }
        return lst;
    }

    /**
     * Определите количество баллов, которое набрал победитель в каждом классе.
     * Гарантируется, что в каждом классе был хотя бы один участник.
     */
    public static Map<Integer, Integer> getScoreWinners(ArrayList<Pupil> lst) {
        Map<Integer, Integer> winners = new HashMap<>();
        for (int i = 0; i < lst.size(); i++) {
            Pupil a = lst.get(i);
            Integer fromMap = winners.get(a.getGroup());
            if (fromMap != null) {
                if (a.getPoint() > fromMap) {
                    winners.put(a.getGroup(), a.getPoint());
                }
            } else
                winners.put(a.getGroup(), a.getPoint());
        }
        return winners;
    }

    /**
     * Победители олимпиады без деления на классы
     */
    public static ArrayList<Pupil> getWinners(ArrayList<Pupil> lst) {
        ArrayList<Pupil> neo = new ArrayList<>();
        Map<Integer, Integer> lop =getScoreWinners(lst);
        int point=0;
        for (Map.Entry<Integer,Integer> muka : lop.entrySet()) {
            if (muka.getValue()>point)
                point = muka.getValue();
        }
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getPoint()==point)
                neo.add(lst.get(i));
        }
        return neo;
    }

    /**
     * Победители по классам
     */
    public static Map<Integer, ArrayList<Pupil>> getWinnerClasses(ArrayList<Pupil> lst) {
        Map<Integer, ArrayList<Pupil>> neo = new HashMap<>();
        ArrayList<Pupil> winners = getWinners(lst);
        getPupilsFromGroup(winners);
        return neo;
    }

    /**
     * Число победителей в каждом классе
     */
    public static Map<Integer, Integer> getCountOfWinners(ArrayList<Pupil> lst) {
        Map<Integer, Integer> neo = new HashMap<>();
        for (int i = 0; i < lst.size(); i++) {
            Pupil a = lst.get(i);
            if (neo.get(a.getGroup()) != null) {
                if (a.getPoint() == getScoreWinners(lst).get(a.getGroup())) {
                    int count = neo.get(a.getGroup());
                    neo.put(a.getGroup(), ++count);
                }
            }
            else
                if (a.getPoint() == getScoreWinners(lst).get(a.getGroup()))
                    neo.put(a.getGroup(), 1);
        }
        return neo;
    }

    /**
     * Баллы призеров по классам
     */
    public static  Map<Integer,Integer> getScoreSemiWinners(ArrayList<Pupil> lst){
        Map<Integer, Integer> neo = new HashMap<>();
        Map<Integer,Integer> pilo =getScoreWinners(lst);
        for (Pupil a : lst) {
            if (neo.get(a.getGroup()) != null) {
                if (a.getPoint() > neo.get(a.getGroup()) && a.getPoint() < pilo.get(a.getGroup())) {
                    neo.put(a.getGroup(), a.getPoint());
                }
            } else if (a.getPoint() < pilo.get(a.getGroup()))
                neo.put(a.getGroup(), a.getPoint());
        }
        return neo;
    }

    /**
     * Выведите фамилию и имя участника олимпиады, набравшего наибольший балл, но не ставшего победителем.
     * Если таких школьников несколько - выведите их количество.
     */
    public static Object getCountScoreSemiWinners(ArrayList<Pupil> lst){
        Map<Integer,Integer> pilo =getScoreSemiWinners(lst);
        int[] answer = new int[2];
        answer[0]=0; //point of semi-winner
        answer[1]=0; //count
        Pupil d = null;
        for(Map.Entry<Integer, Integer> neo: pilo.entrySet()){
            if (neo.getValue()>answer[0])
                answer[0] = neo.getValue();
        }
        for (Pupil aLst : lst) {
            if (aLst.getPoint() == answer[0]) {
                answer[1]++;
                d = aLst;
            }
        }
        if (d!=null)
            return d;
        else
            return answer;
    }

    /**
     * Определите школы, из которых в олимпиаде принимало участие больше всего участников.
     * Выведите номера этих школ в порядке возрастания.
     */
    public static Map<Integer, Integer> getSchoolLeaders (ArrayList<Pupil> lst){
        Map<Integer, Integer> neo = new HashMap<>();
        for (int i = 0; i < lst.size(); i++) {
            Pupil a = lst.get(i);
            if (neo.get(a.getSchool())!=null){
                int count = neo.get(a.getSchool());
                neo.put(a.getSchool(), ++count);
            }
            else
                neo.put(a.getSchool(), 1);
        }
        return neo;
    }
    /**
     * Ученикки из класса
     */
    public static Map<Integer, ArrayList<Pupil>> getPupilsFromGroup (ArrayList<Pupil> lst){
        Map<Integer, ArrayList<Pupil>> neo = new HashMap<>();
        for (Pupil aLst : lst) {
            ArrayList<Pupil> classStudents = neo.get(aLst.getGroup());
            if (classStudents == null)
                classStudents = new ArrayList<>();
            classStudents.add(aLst);
            neo.put(aLst.getGroup(), classStudents);
        }
        return neo;
    }

    /**
     * Средний бал по классам
     */
    public static Map<Integer, Double> getAverClass(ArrayList<Pupil> lst){
        Map<Integer, Double> neo= new HashMap<>();
        Map<Integer, ArrayList<Pupil>> leto = getPupilsFromGroup(lst);
        for (Map.Entry<Integer, ArrayList<Pupil>> help: leto.entrySet()){
            ArrayList<Pupil> hex = help.getValue();
            double count=0;
            for (int i = 0; i < hex.size(); i++) {
                count+=hex.get(i).getPoint();
            }
            double rez = count/hex.size();
            neo.put(help.getKey(), rez);
        }
        return neo;
    }

    /**Ученики по школам
     */
    public static Map<Integer, ArrayList<Pupil>> getPupilsFromSchool (ArrayList<Pupil> lst){
        Map<Integer, ArrayList<Pupil>> neo = new HashMap<>();
        for (Pupil aLst : lst) {
            ArrayList<Pupil> classStudents = neo.get(aLst.getSchool());
            if (classStudents == null)
                classStudents = new ArrayList<>();
            classStudents.add(aLst);
            neo.put(aLst.getSchool(), classStudents);
        }
        return neo;
    }

    /**
     * Cредний бал по школам
     */
    public static Map<Integer, Double> getAverSchool(ArrayList<Pupil> lst){
        Map<Integer, Double> neo= new HashMap<>();
        Map<Integer, ArrayList<Pupil>> leto = getPupilsFromSchool(lst);
        for (Map.Entry<Integer, ArrayList<Pupil>> help: leto.entrySet()){
            ArrayList<Pupil> hex = help.getValue();
            double count=0;
            for (int i = 0; i < hex.size(); i++) {
                count+=hex.get(i).getPoint();
            }
            double rez = count/hex.size();
            neo.put(help.getKey(), rez);
        }
        return neo;
    }
    public static double averPointOlymp(ArrayList<Pupil> pupils){
        double count=0;
        double sum =0;
        for (Pupil pupil : pupils) {
            sum += pupil.getPoint();
            count++;
        }
        return sum/count;
    }

    /**
     * выведите номера школ, из которых был хотя бы один участник олимпиады, в порядке убывания количества участников олимпиады из этих школ.
     * Если из двух школ было одинаковое число участников, то их номера выводятся в порядке возрастания номера школы.
     * Решение должно иметь сложность O(N+K) (без учета сложности сортировки списка), где N — общее количество участников олимпиады, K — количество школ.
     * Решение должно использовать O(K) памяти.
     */


}

