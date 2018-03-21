package ua.nure.veretelnyk.practice1;

/*
    Практическое задание №1
    _______________________

    Задание 6
    -------------------------------------------------------
    Название класса: ua.nure.your_last_name.Practice1.Part6
    -------------------------------------------------------

    Написать класс, который создает массив из n элементов и заполняет его восходящим рядом простых чисел (2, 3, 5, 7, ...).
    Число n передавать как параметр командной строки

    Пример:
    -------------------------------------------------------
    Параметры командной строки: 7
    Консольный вывод: 2 3 5 7 11 13 17
    -------------------------------------------------------
*/
public class Part6 {

    private static boolean isPrime(int n){
        if (n == 2)
            return true;
        if (n%2 == 0)
            return false;
        for(int i = 3; i <= Math.sqrt(n); i+=2){
            if (n%i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int[] simple = new int[size];
        int selection = 0;
        for(int i = 2; selection < size; ++i){
            if(isPrime(i)){
                simple[selection] = i;
                selection++;
            }
        }
        for (int n : simple){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}