import java.util.Scanner;  // Импорт пакета для ввода

public class Main {

    // Метод для запуска программы
    public static void main(String[] args) {
        // Создаем объект класса сканер для считывания введенного пользователем текста
        Scanner in = new Scanner(System.in);

        System.out.println("Введите 1 чтобы вывести простые числа\nВведите 2 чтобы проверить на палиндромы");
        String num = in.nextLine();

        // Анализируем ответ пользователя, используя оператор switch
        switch (num){
            case "1":
                Primes.mainPrime();
                break;
            case "2":
                Palindrome.mainPalindrome();
                break;
            default:
                System.out.println("ОШИБКА!!!\nПеразапустите и введите одно из указанных значений!");
                break;
        }

    }
}
