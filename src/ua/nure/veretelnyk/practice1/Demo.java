package ua.nure.veretelnyk.practice1;

/*
    Практическое задание №1
    _______________________

    Замечания.
    1. Результат должен быть представлен в виде проекта с именем Practice1.
    2. Корневой пакет для всех классов и прочих пакетов (если они потребуются): ua.nure.your_last_name.practice1, где your_last_name - ваш логин без кода проекта.
    Если логин=ivanovjtp, то your_last_name=ivanov
    3. Дополнительно в корневом пакете расположить класс Demo, который демонстриует работу всех 7 подзадач.
    4. Проект загрузить в репозиторий, проверить, успешность сборки в Jenkins, оптимизировать метрики в Sonar (после того, как будет дан доступ к этим системам).
    5. Каждый класс Part1, Part2, ..., Part7 содержит метод main.


    Вопросы.
    1. Какие категории типов данных существуют в Java?
    2. Перечислите примитивные типы данных.
    3. Тип данных char, что хранит, область определения.
    4. Напишите метод main (два варианта).
    5. Укажите автоматические преобразования между примитивными типами
    6. При каких преобразованиях между примитивными типами возможна утрата информации?
    7. Какие преобразования между типами вы знаете.
    8. Что такое wrappers, autoboxing, autounboxing.
    9. Напишите анонимный массив, массив константу, в чем отличие.
    10. Напишите пример двумерного массива.
*/

public class Demo {
    public static void main(String[] args) {
        System.out.println("======Part1");
        Part1.main(new String[]{});

        System.out.println("======Part2");
        Part2.main(new String[]{"2", "3"});

        System.out.println("======Part3");
        Part3.main(new String[]{"a", "b", "c", "d", "34"});

        System.out.println("======ua.nure.veretelnyk.practice3.Part4");
        Part4.main(new String[]{"25", "15"});

        System.out.println("======Part5");
        Part5.main(new String[]{"1234"});

        System.out.println("======Part6");
        Part6.main(new String[]{"7"});

        System.out.println("======Part7");
        Part7.main(new String[]{});
    }
}
