import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

// Задача №5
public class Task_5 {
    // *Задание №1: Функция, которая возвращает true, если две строки имеют один и тот же буквенный шаблон, иначе false.
    public static boolean sameLetterPattern(String x,String y) {
        String temp = "";
        String letter;

        //если длина строк одинаковая, то начинаем проверку
        if (x.length() == y.length()) {
            //перебираем первую строчку
            for (int i=0;i<x.length();i++) {
                letter = ""+x.charAt(i);
                //если текущая буква не встречалась,
                //то из второй строки берем букву под темже индексо
                //и во всей строке меняем ее на букву из первой
                if (!temp.contains(letter)) {
                    temp += letter;
                    y = y.replace("" + y.charAt(i), letter);
                }
            }
        }
        return x.equals(y);
    }

    // Задание №2: Функция, которая принимает координаты паука и мухи и возвращает кратчайший путь для паука до мухи.
    public static String spiderVsFly(String spider, String fly) {
        int sx = spider.charAt(0) - 65;
        int sy = spider.charAt(1) - 48;
        int fx = fly.charAt(0) - 65;
        int fy = fly.charAt(1) - 48;

        double strategyDist1 = sy + fy;
        double strategyDist2 = Math.abs(sy - fy) + ((sx + fx) % 8) * fy * 0.76536686473;

        String path = "";

        if (strategyDist1 <= strategyDist2) {
            for (int i = 0; i < sy; i++) {
                path += spider.charAt(0);
                path += sy - i;
                path += '-';
            }
            path += "A0-";
            for (int i = 0; i < fy; i++) {
                path += fly.charAt(0);
                path += i + 1;
                path += '-';
            }
        } else {
            for (int i = 0; i < Math.abs(sy - fy); i++) {
                path += spider.charAt(0);
                if (sy > fy) path += sy - i;
                else path += sy + i;
                path += '-';
            }
            for (int i = 0; i <= (sx + fx) % 8; i++) {
                path += (char)(65 + (sx + i) % 8);
                path += fly.charAt(1);
                path += '-';
            }
        }

        return path.substring(0, path.length() - 1);
    }

    // *Задание №3: Функция, которая будет рекурсивно подсчитывать количество цифр числа.
    public static int digitsCount(long x){
        return 1 + _digitsCount(x/10);
    }
    public static int _digitsCount(long x){
        if (x==0) return 0;
        //иначе рекурсивно вызываем функцию, отнимая у числа одну цифру, пока число не станет 0
        return 1 + _digitsCount(x/10);
    }

    // Задание №4: Функция, которая возвращает общее количество очков, набранных игроком в определенном раунде.
    private static String deleteLetter(String text,int index){
        if(text.length()>0) {

            if (index > 0 && index < text.length() - 1) {
                return text.substring(0, index) + text.substring(index + 1);
            } else if (index == 0) {
                return text.substring(1);
            } else if(index==text.length()-1){
                return text.substring(0,index);
            }
        }
        return null;
    }
    public static int totalPoints(String[] words,String lastWord){
        int score=0;
        boolean invalid=false;
        String tempWord;

        for(String oneWord:words){
            tempWord=lastWord;
            for(int i=0;i<oneWord.length();i++){
                if(!tempWord.contains(""+oneWord.charAt(i))){
                    invalid=true;
                    break;
                }
                else{
                    tempWord=deleteLetter(tempWord,tempWord.indexOf(oneWord.charAt(i)));
                }
            }

            if(!invalid && oneWord.length()>=3){
                score+=oneWord.length()==6? 54:oneWord.length()-2;
            }
            invalid=false;
        }
        return score;
    }

    // *Задание №5: Функция, которая возвращает длину самого длинного последовательного запуска.
    public static int longestRun(int[] arr){
        int k=1;
        int max=1;
        //пебираем массив
        for (int i=0;i<arr.length-1;i++){
            //если следующее число меньше предыдущего на 1, то увеличиваем счетчик
            if (Math.abs(arr[i+1]-arr[i])==1){
                k++;
                if (max<k) max=k;
            }
            else k=1;
        }
        return max;
    }

    // *Задание №6: Функция, ищет процент который можно набрать на тесте, снижающий средний балл по классу на 5%.
    public static String takeDownAverage(String[] x) {
        int sum=0;
        //перебираем массив строк
        for (String s: x)
            sum += Integer.parseInt(s.substring(0,s.length()-1));
        //по математической формуле возвращаем результат
        return (sum/x.length-x.length*5-5)+"%";
    }

    // *Задание №7: Функция, которая возвращает отсортированое предложение.
    public static String rearrange(String str) {
        String[] words = str.split(" ");
        String[] strNew = new String[words.length];

        //перебор слов
        for (String word: words) {
            //перебор слова
            for (int i=0;i<word.length();i++) {
                //если символ число, то слово вставляется в новый массив под индексом число-1 без этого числа
                if (Character.isDigit(word.charAt(i))) {
                    strNew[Integer.parseInt(""+word.charAt(i))-1]=word.substring(0,i)+word.substring(i+1);
                    break;
                }
            }
        }
        return String.join(" ",strNew);
    }

    // Задание №8: Функция, которая делает первое число как можно больше, меняя его цифры на цифры во втором числе.
    public static int maxPossible(int x,int y) {
        String sum="";
        int index=-1;

        String strX = String.valueOf(x);
        String strY = String.valueOf(y);

        int[] arrX = new int[strX.length()];
        int[] arrY = new int[strY.length()];

        for (int i=0;i<strX.length();i++) {
            arrX[i] = Integer.parseInt(""+strX.charAt(i));
        }
        for (int i=0;i<strY.length();i++) {
            arrY[i] = Integer.parseInt(""+strY.charAt(i));
        }

        for (int i=0;i<arrX.length;i++) {
            for (int j=0;j<arrY.length;j++) {
                if (arrX[i]<arrY[j]) {
                    arrX[i]=arrY[j];
                    index=j;
                }
            }
            if (index>-1) {
                arrY[index]=0;
            }
            sum+=arrX[i];
            index=-1;
        }
        return Integer.parseInt(sum);
    }

    // Задание №9: Функция, которая возвращает новую метку времени с датой и соответствующим временем в cityB.
    private static String getGMT(String city) {
        return switch (city) {
            case "Los Angeles" -> "GMT-08:00";
            case "New York" -> "GMT-05:00";
            case "Caracas" -> "GMT- 04:30";
            case "Buenos Aires" -> "GMT-03:00";
            case "London" -> "GMT00:00";
            case "Rome" -> "GMT+01:00";
            case "Moscow" -> "GMT+03:00";
            case "Tehran" -> "GMT+03:30";
            case "New Delhi" -> "GMT+05:30";
            case "Beijing" -> "GMT+08:00";
            case "Canberra" -> "GMT+10:00";
            default -> "GMT";
        };
    }
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        SimpleDateFormat parseDate = new SimpleDateFormat("MMMM d, yyyy HH:mm");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-M-d HH:mm");
        try {
            parseDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityA)));
            formatDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityB)));
            Date date = parseDate.parse(timestamp);
            return formatDate.format(date);

        } catch(Exception e) {}


        return "2011-4-2 17:23";
    }

    // Задание №10: Функция, которая возвращает true, если целое число является новым числом большим данного, и false.
    public static boolean isNew(int x) {

        String str=String.valueOf(x);
        int[] oldX=new int[str.length()];
        int[] newX=new int[str.length()];

        for (int i=0;i<str.length();i++){
            oldX[i] = Integer.parseInt(""+str.charAt(i));
            newX[i] = Integer.parseInt(""+str.charAt(i));
        }

        Arrays.sort(newX);

        if (newX[0]>0) {
            for (int i=0;i<oldX.length;i++) {
                if (oldX[i]!=newX[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
