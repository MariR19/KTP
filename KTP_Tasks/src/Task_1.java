// Задача №1
public class Task_1 {
    // Задание №1: Функция, которая принимает целое число минут и преобразует его в секунды.
    public static int convert(int x){
        return x*60;
    }

    // Задание №2: Функция, которая подсчитывает очки за баскетбольный матч, учитывая количество забитых 2-х и 3-х очков.
    public static int points(int x,int y){
        return x*2+y*3;
    }

    // Задание №3: Функция, которая принимает количество побед, ничьих и поражений и вычисляет количество очков.
    public static int footballPoints(int x,int y,int z){
        return x*3+y*1+z*0;
    }

    // Задание №4: Функция, которая возвращает true, если целое число делится на 5, и false в противном случае.
    public static boolean divisibleByFive(int x){
        return x%5==0;
    }

    // Задание №5: Оператор && принимает два логических значения и возвращает true, если оба значения истинны.
    public static boolean and(boolean a,boolean b){
        return a&&b;
    }

    // Задание №6: Функция, которая возвращает количество полных стен, которые я могу покрасить.
    // n - это количество квадратных метров, w и h - это ширина и высота одной стены в метрах.
    public static int howManyWall(int n,int w,int h){
        return n/(w*h);
    }

    // Задание №7: Функция, которая принимает число и уножает его на себя.
    public static int squaed(int a) {
        return a * a;
    }

    // Задание №8: Функция, возвращает true, если prob * prize > pay; в противном случае возвращает false.
    public static boolean profitableGamble(double prob,int prize,int pay){
        return prob * prize > pay;
    }

    // Задание №9: Метод, который возвращает количество кадров, показанных за заданное количество минут для FPS.
    public static int frames(int minute,int FPS){
        return minute*60*FPS;
    }

    // Задание №10: Функция, которая будет работать как оператор модуля % без использования оператора модуля.
    public static int mod(int x,int y){
        return x-(x/y*y);
    }
}
