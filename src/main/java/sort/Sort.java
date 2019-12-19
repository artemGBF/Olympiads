package sort;

import model.Pupil;
import olymp.Olymp;

import java.util.*;

public class Sort {
    /**
     * Сортировка по фамилии
     *
     * @param pupils
     */
    public static void sortSurname(ArrayList<Pupil> pupils) {
        pupils.sort(new Comparator<Pupil>() {
            @Override
            public int compare(Pupil o1, Pupil o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
    }

    /**
     * Сортировка по балу
     *
     * @param pupils
     */
    public static void sortPoint(ArrayList<Pupil> pupils) {
        pupils.sort(new Comparator<Pupil>() {
            @Override
            public int compare(Pupil o1, Pupil o2) {
                return Integer.compare(o1.getPoint(), o2.getPoint());
            }
        });
    }

    /**
     * Отсортируйте список участников олимпиады:
     * По убыванию набранного балла.
     * При равных значения балла - по фамилии в лексикографическом порядке.
     * При совпадающих баллах и фамилии - по имени в лексикографическом порядке.
     * Для сортировки используйте встроенную функцию сортировки.
     *
     * @param pupils
     */
    public static void sortReversePoint(ArrayList<Pupil> pupils) {
        pupils.sort(new Comparator<Pupil>() {
            @Override
            public int compare(Pupil o1, Pupil o2) {
                if (o1.getPoint() == o2.getPoint()) {
                    if (!o1.getSurname().equals(o2.getSurname()))
                        return o1.getSurname().compareTo(o2.getSurname());
                    return o1.getName().compareTo(o2.getName());
                }
                return -Integer.compare(o1.getPoint(), o2.getPoint());
            }
        });
    }

    /**
     * Выведите в порядке возрастания номера школ, в которых есть хотя бы один победитель олимпиады.
     *
     * @param pupils
     * @return
     */
    public static ArrayList<Integer> sortSchools(ArrayList<Pupil> pupils) {
        ArrayList<Pupil> milko = Olymp.getWinners(pupils);
        ArrayList<Integer> lol = new ArrayList<>();
        Set<Integer> neo = new HashSet<>();
        for (int i = 0; i < milko.size(); i++) {
            neo.add(pupils.get(i).getSchool());
        }
        lol.addAll(neo);
        lol.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        if (lol.get(0) == 0)
            return null;
        return lol;
    }

    /**
     * Выведите в порядке возрастания номера школ, средний балл учащихся которых выше,
     * чем средний балл всех участников олимпиады
     */
    public static ArrayList<Integer> sortIncludeAverPoint(ArrayList<Pupil> pupils) {
        Map<Integer, Double> nep = Olymp.getAverSchool(pupils);
        ArrayList<Integer> pilo = new ArrayList<>();
        for (Map.Entry<Integer, Double> hex : nep.entrySet()) {
            if (hex.getValue() > Olymp.averPointOlymp(pupils))
                pilo.add(hex.getKey());
        }
        pilo.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return pilo;
    }

    /**
     * Выведите в порядке возрастания номера школ, средний балл учащихся которых максимален
     */
    public static Set<Integer> getSchoolMaxAverPoint(ArrayList<Pupil> pupils) {
        ArrayList<Integer> jiga = new ArrayList<>();
        Map<Integer, Double> tai = Olymp.getAverSchool(pupils);
        double jir = 0;
        for (Map.Entry<Integer, Double> pi : tai.entrySet()) {
            if (pi.getValue() > jir)
                jir = pi.getValue();
        }
        Set<Integer> pot = new HashSet<>();
        for (Map.Entry<Integer, Double> pill : tai.entrySet()) {
            if (pill.getValue() == jir)
                pot.add(pill.getKey());

        }
        return pot;
    }

    /**
     * выведите в порядке возрастания номера школ,
     * из которых наибольшее количество участников стало победителями олимпиады.
     */
    public static Set<Integer> getCountofSchoolLeders(ArrayList<Pupil> pupils) {
        Set<Integer> neo = new HashSet<>();
        int max = 0;
        Map<Integer, Integer> has = new HashMap<>();
        ArrayList<Pupil> kilo = Olymp.getWinners(pupils);
        for (int i = 0; i < kilo.size(); i++) {
            if (has.get(kilo.get(i).getSchool()) != null) {
                int count = has.get(kilo.get(i).getSchool());
                has.put(kilo.get(i).getSchool(), ++count);
                if (count > max)
                    max = count;
            } else {
                has.put(kilo.get(i).getSchool(), 1);
                if (1 > max)
                    max = 1;
            }
        }
        for (Map.Entry<Integer, Integer> lop : has.entrySet()) {
            if (lop.getValue() == max)
                neo.add(lop.getKey());
        }
        return neo;

    }
}

