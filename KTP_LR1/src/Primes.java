public class Primes {

    // Метод принимает целочисленное значение и возвращает true, если число простое (делится только на себя)
    public static boolean isPrime(int n){
      for (int i=2;i<n;i++){
         if (n%i==0)
             return false;
      }
      return true;
    }

    // Метод переберает числа от 2 до 100 и выводит простые числа
    public static void mainPrime(){
        System.out.println("Простые числа:");

        for (int i=2;i<=100;i++){
            if (isPrime(i))
                System.out.println(i);
        }
    }
}

