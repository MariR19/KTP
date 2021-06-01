import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;


// Задача №4
public class Task_4 {
    // *Задание №1: Функция, которая возвращает "Бум!", если есть цифра 7, иначе - "в массиве нет 7".
    public static String sevenBoom(int[] arr){
        for (int i=0;i<arr.length;i++){
            if (String.valueOf(arr[i]).contains("7")){
                return "Boom!";
            }
        }
        return "there is no 7 in the array";
    }

    // *Задание №2: Функция, которая возвращает true, если элементы в массиве могут
    // сформировать последовательный список чисел, где каждое число появляется ровно один раз.
    public static boolean cons(int[] arr){
        Arrays.sort(arr);
        for (int i=1;i<arr.length;i++){
                if (arr[i]-arr[i-1]!=1){
                    return false;
                }
            }
        return true;
    }

    // Задание №3: Функция, которая меняет все перепутанные строки местами.
    public static String unmix(String str){
        char temp;
        char[] arrStr = str.toCharArray();
        for (int i=0;i<arrStr.length-1;i+=2){
            temp=arrStr[i];
            arrStr[i]=arrStr[i+1];
            arrStr[i+1]=temp;
        }
        return String.copyValueOf(arrStr);
    }

    // *Задание №4: Функция, которая преобразует предложения, заканчивающиеся несколькими ? или ! в предложение,
    // заканчивающееся только одним, без изменения пунктуации в середине предложений.
    public static String noYelling(String str){
        int k=0;
        int i = str.length()-1;
        while ((str.charAt(i)=='!' || str.charAt(i)=='?') && i>=0){
           k++;
           i--;
        }
        if (k==0){
            k++;
        }
        return str.substring(0,str.length()-k+1);
    }

    // Задание №5: Функция, которая заменяет все x в строке следующими способами:
    // Замените все x на "cks", если не в начале; на "z", если начинается с "x"; на "ecks", если просто буква "х".
   public static String xPronounce(String str){
        String[] arrStr = str.split(" ");
        for (int i=0;i<arrStr.length;i++){
            if (arrStr[i].startsWith("x") && arrStr[i].length()>1){
                arrStr[i]=arrStr[i].replace("x","z");
            }
            else if (arrStr[i].length()==1){
                arrStr[i]=arrStr[i].replace("x","ecks");
            }
            else {
                arrStr[i]=arrStr[i].replace("x","cks");
            }
        }
        return String.join(" ",arrStr);
   }

   // *Задание №6: Функция, которая возвращает наибольший разрыв между отсортированными элементами массива.
    public static int largestGap(int[] arr){
        int max=0;
        Arrays.sort(arr);
        for (int i=0;i<arr.length-1;i++){
            if (arr[i+1]-arr[i]>max){
                max=arr[i+1]-arr[i];
            }
        }
        return max;
    }

    // *Задание №7: Функция, которая вычитает из исходного числа отсортированное это же число.
    public static int myMethod(int x){
        String strX = String.valueOf(x);
        char[] arrX = strX.toCharArray();
        Arrays.sort(arrX);
        return x-Integer.parseInt(String.copyValueOf(arrX));
    }
    
    // Задание №8: Функция, которая возвращает самую распространенную последнюю гласную в предложении в одним символом.
    public static String commonLastVowel(String str){
        String text="";
        for (int i=0;i<str.length();i++){
            if(Character.isAlphabetic(str.charAt(i))){
                text+=str.charAt(i);
            }
        }

        HashMap <Character,Integer> map=new HashMap<>();
        String vowels="eyuioa";
        String answer="";
        char letter=' ';
        int max=0;
        text = text.toLowerCase(Locale.ROOT);
        String[] word=text.split(" ");

        // Заполнение списка гласными
        for (int i=0;i<vowels.length();i++){
            map.put(vowels.charAt(i),0);
        }

        // Считает сколько раз встречается букваы
        for (int i=0;i<word.length;i++){

            letter=word[i].charAt(word[i].length()-1);
            if (vowels.contains(""+letter)) {
                map.put(letter,map.get(letter)+1);
            }
        }

        // Находит максимальное
        for (char i:map.keySet()){
            if (map.get(i)>max){
                max=map.get(i);
                letter=i;
            }
        }
        return ""+letter;
    }

    // Задание №9: Функция, которая неправильно складывает(складывает по цифрам)
    public static int memeSum(int x,int y){
        String strX = String.valueOf(x);
        String strY = String.valueOf(y);
        String sum = "";

        if (strX.length()>strY.length()){
            strY="0".repeat(strX.length()-strY.length())+strY;
        }
        else if (strX.length()<strY.length()){
            strX="0".repeat(strY.length()-strX.length())+strX;
        }

        for (int i=0;i<strX.length();i++){
                sum+=(Integer.parseInt(""+strX.charAt(i)) + Integer.parseInt(""+strY.charAt(i)));
        }
        return Integer.parseInt(sum);
    }

    // Задание №10: Функция, которая удалит все повторяющиеся символы в слове.
    public static String unrepeated(String str){
        String strNew = "";
        for (int i=0;i<str.length();i++){
            if (!strNew.contains(""+str.charAt(i))){
                strNew+=str.charAt(i);
            }
        }
        return strNew;
    }
}

