package ua.nure.veretelnyk;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo {
    private static final InputStream STD_IN = System.in;
    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) throws IOException {
        System.out.println("=========================== PART1");
        Part1.main(args);

        System.out.println("=========================== PART2");
        Part2.main(args);

        System.out.println("=========================== PART3");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "char^String^int^double^stop".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        Part3.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        System.out.println("\n=========================== PART4");
        Part4.main(args);

        System.out.println("=========================== PART5");
        // set the mock input

        Part5.main(args);
        // restore the standard input
        System.setIn(STD_IN);
    }
}

/*
(1) Обязательно посмотреть в лог сборки проекта (Jenkins), вывод должен совпадать с тем выводом,
который получается на вашей локальной машине.
(2) Если приложение считывает информацию из файла, то необходимо указать кодировку, в которой она (информация) записана.
(3) Timeoute сборки проекта в Jenkins 2 минуты. Если при сборке проекта будет вызвана функциональность ожидания консольного ввода, то максимум через 2 минуты проект будет снят с выполнения, а сама сборка помечена как Aborted.
(4) Если Jenkins не собирает проект по причине выброса IllegalAccessError с сообщением "tried to access class XXX" поставьте уровень доступа типа с именем XXX в public.

 */
//FileWriter