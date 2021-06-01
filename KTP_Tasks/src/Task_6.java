import java.util.*;

// Задача №6
public class Task_6 {
    // *Задание №1: Функция, которая ищет анаграмму второй строки, вложенную где-то в первую строку.
    //метод удаления буквы из строки по индексу
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
    // 1.Удаляем из текста все символы кроме букв
    // 2.Копируем строку где нужно найти анаграмму во временную переменную
    // 3.Перебираем текст, если текущая буква содержится во времеенной строке,добавляем ее в анограмму и
    // удаляем ее из временной.
    // 4.Если следующая буква не содерджится во временной, обнуляем анограмму и восстанавливаем временную перемнную
    public static String hiddenAnagram(String bigTextInput, String smallTextInput){
        String bigText="";
        String smallText="";
        String tempText;
        String anagram="";
        String letter;


        for(int i=0; i<bigTextInput.length();i++){
            if(Character.isAlphabetic(bigTextInput.charAt(i))){
                bigText+=bigTextInput.charAt(i);
            }
        }
        bigText=bigText.toLowerCase(Locale.ROOT);

        for(int i=0; i<smallTextInput.length();i++){
            if(Character.isAlphabetic(smallTextInput.charAt(i))){
                smallText+=smallTextInput.charAt(i);
            }
        }
        smallText=smallText.toLowerCase(Locale.ROOT);
        tempText=smallText;

        int i=0;
        while(i<bigText.length() && anagram.length()!=smallText.length()){

            if(Character.isAlphabetic(bigText.charAt(i))) {

                letter =("" + bigText.charAt(i));

                if (tempText.contains(letter)) {
                    anagram += letter;
                    tempText = deleteLetter(tempText, tempText.indexOf(letter));
                } else {
                    i-=anagram.length();
                    anagram = "";
                    tempText = smallText;
                }
            }
            i++;
        }

        return anagram.length()==smallText.length()? anagram:"noutfond";
    }

    // *Задание №2: Функция, которая разбивает текст на массив строк (размер строк n)
    public static ArrayList<String> collect(String text, int size){
        //создаем лист для хранения массива строк
        ArrayList<String> arr=new ArrayList<>();
        //если длина текста больше чем колличество букв, которое должно быть в тексте
        if (text.length() >= size) {
            //добавляем в массив первую подстроку размером от 0 до n
            arr.add(text.substring(0, size));
            //рекурсивно вызываем метод, обрезая строку
            arr.addAll(collect(text.substring(size), size));
            //сортируем массив
            Collections.sort(arr);
        }
        return arr;
    }

    // *Задание №3: Функция, которая возвращает закодированное сообщение.
    public static String nicoCipher(String message,String key){
        int keySize=key.length();       //Длинна ключа для сокращения кода
        String answer="";               //Ответ
        String sortedKeyString;         //Строка с отсортированным по алфовиту ключом

        char[] sortedKeyLetters=key.toCharArray();              //Массив букв ключа, для того, чтобы отсортировать его
        int[] numKey=new int[keySize];                          //Ключ в цифрах
        String[] sortedMessageLetters = new String[keySize];    //Массив отсортированных букв сообщения

        Arrays.sort(sortedKeyLetters); //Сортировка букв ключа
        sortedKeyString=String.copyValueOf(sortedKeyLetters); //Запись массива букв в строчку для получения кода в цифрах

        //Извлекаем ключ из строки
        for(int i=0;i<keySize;i++){
            numKey[i]=sortedKeyString.indexOf(key.charAt(i));
        }

        //Заполненяем массива букв пустыми строчками (До этого было null, и он прибавлял буквы к строке "null")
        for(int i=0;i<keySize;i++){
            sortedMessageLetters[i]="";
        }
        //Назначаем номера буквам данного сообщения
        for(int i=0;i<message.length();i++){
            sortedMessageLetters[numKey[i%keySize]]+=message.charAt(i);
        }

        String tempString;
        String tempLetter;
        int i=0;
        //Пока все строчки из массива не будут пустыми
        while(!String.join("",sortedMessageLetters).equals("")){
            //Берем строку с отсортирванными буквами
            tempString=sortedMessageLetters[i%keySize];
            //Если строка не пустая, берем букву, иначе берем пробел
            if(tempString.length()>0){
                //Извлекаем букву
                tempLetter=tempString.charAt(0)+"";
                //Удаляем букву из строки
                sortedMessageLetters[i%keySize]=deleteLetter(tempString,0); //Метод из 1 задания, удаляет букву по индексу
            }
            else{
                tempLetter=" ";
            }

            //Вставляем извлеченную букву в ответ
            answer+=tempLetter;
            i++;
        }

        return answer;
    }

    // *Задание №4: Функция, которая возвращает массив из двух целых чисел с минимальной разницей,
    // произведение которых равно числу n
    public static int[] twoProduct(int[] numbers, int n){
        int num1=0;
        int num2=0;
        int gap=numbers.length+1;

        //Если длинна массива больше 1
        if(numbers.length>1) {
            //Перебираем массив от второго элемента
            for (int i=1;i<numbers.length-1;i++) {
                //Пересматриваем все элементы, которые стоят левее от взятого числа (справа-налево)
                for (int j=i-1;j>=0;j--) {
                    //Если произведение чисел равно заднанному и разница между ними минимальна, записываем их
                    if (numbers[i]*numbers[j]==n && j-i<gap) {
                        num1 = numbers[j];
                        num2 = numbers[i];
                        gap = j - i;
                    }
                }
            }
        }
        //Если ни одного числа так и не записали, возвращаем пустой массив, иначе возвращаем нужные два числа
        if (num1==0){
            return new int[]{};
        }
        else{
            return new int[]{num1,num2};
        }
    }


    // *Задание №5: Функция, которая проверяет, является ли число точной верхней границей факториала n.
    //2 Метод для рекурсии
    public static int[] isExact(int factorial, int count, int n) {
        //Если факториал меньше числа n, расчитываем факториал следующего числа
        if (factorial < n) {
            return isExact(factorial * (count + 1), count + 1, n);
        }
        return new int[] {factorial, count};
    }

    //1 Основной метод
    public static int[] isExact(int n) {
        //Расчитываем факториал от 1
        int[] res = isExact(1, 1, n);

        if (res[0] == n) return res;
        return new int[] {};
    }

    // Задание №6: Функция, которая возвращает эквивалентную дробь в строковой форме и в наименьших членах.
    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
        if (startBracket != -1) {
            String f = frac.substring(startBracket+1, frac.length()-1);
            frac = frac.substring(0, startBracket);
            for (int i = 0; i < 9 - f.length(); i++)
                frac += f;
        }
        double a = Double.parseDouble(frac);
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001){
            div++;
        }
        return "" + (int)Math.ceil(a * div) + "/" + div;
    }

    // Задание №7: Функция, которая преобразуйте строку в серию слов, имеющих одинаковую длину,
    // заданную первыми 15 цифрами десятичного представления числа Пи
    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0;
        int p;
        int n;
        while (str.length() > 0) {
            p = pi.charAt(piIndex) - 48;
            n = Math.min(p, str.length());
            res += str.substring(0, n);
            str = str.substring(n);
            piIndex++;
            if (str.length() > 0) {
                res += ' ';
            }
            else if (p > n) {
                for (int i = 0; i < p - n; i++) {
                    res += res.charAt(res.length() - 1);
                }
            }
        }
        return res;
    }

    // Задание №8: Функция, которая генерирует строка, в которой нет последовательных строк,
    // и где n определяет длину каждой двоичной строки.
    public static String generateNonconsecutive(int n) {
        String res = "";
        String format = "%" + n + 's';
        int count = 2 << (n-1);
        nextNumber:
        for (int i = 0; i < count; i++) {
            String num = String.format(format, Integer.toBinaryString(i)).replace(' ', '0');
            for (int j = 0; j < n - 1; j++) {
                if (num.charAt(j) == '1' && num.charAt(j + 1) == '1') {
                    continue nextNumber;
                }
            }
            res += num + ' ';
        }
        return res.substring(0, res.length() - 1);
    }

    // Задание №9: Функция, которая возвращает true, если все символы строки встречаются одинаковое количество раз и,
    // если он может удалить только 1 символ из 1 индекса в строке,
    // а остальные символы будут встречаться одинаковое количество раз.
    private static int[] getLetterSet(String str) {
        int[] set = new int[26];
        for (char c : str.toCharArray())
            set[c - 97]++;
        return set;
    }

    public static String isValid(String str) {
        int[] set = getLetterSet(str);
        int[] medium = new int[str.length()];
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0) medium[set[i]]++;
        int cur = 0;
        int max = 0;
        for (int i = 0; i < medium.length; i++)
            if (medium[i] > cur) {
                cur = medium[i];
                max = i;
            }
        boolean index = false;
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0 && max - set[i] != 0) {
                if (index) return "NO";
                index = true;
            }
        return "YES";
    }

    // Задание №10: Функция, которая получает каждую пару чисел из массива,
    // который суммирует до восьми, и возвращает его как массив пар.
    public static int[][] sumsUp(int[] arr) {
        ArrayList<int[]> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(8 - arr[i])) {
                int a = arr[i];
                int b = 8 - a;
                if (a > b) {
                    b = a;
                    a = 8 - b;
                }
                res.add(new int[]{i - map.get(8 - arr[i]), a, b});
            }
            map.put(arr[i], i);
        }
        Collections.sort(res, (o1, o2) -> o1[0] - o2[0]);
        int[][] newRes = new int[res.size()][];
        for (int i = 0; i < res.size(); i++)
            newRes[i] = new int[]{res.get(i)[1], res.get(i)[2]};
        return newRes;
    }
}
