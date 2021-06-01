import java.util.Scanner;  // Импорт пакета для ввода

public class Palindrome {

    // Метод принимает строку и разворачивает её
    public static String backwards(String str){
        String backStr="";

        for (int i=str.length()-1;i>=0;i--)
            backStr+=str.charAt(i);

        return backStr;
    }

    // Метод принимает строку и возвращает true, если строка равна перевернутой строке
    public static boolean isPalindrome(String str){
        String backStr=backwards(str);

        if (str.equals(backStr))
            return true;
        else
            return false;
    }

    // Метод считывает строку со словами и анализирует их на палиндромность
    public static void mainPalindrome(){
        Scanner in = new Scanner(System.in);

        System.out.println("Введите слова:");
        String line = in.nextLine()+' ';

        String temp="";

        for (int i=0;i<line.length();i++){
            if (line.charAt(i)==' '){
                if (isPalindrome(temp))
                    System.out.println("Слово \""+temp+"\" является палиндромом");
                else
                    System.out.println("Слово \""+temp+"\" не является палиндромом");
                temp="";
            }
            else
                temp+=line.charAt(i);
        }
    }
}
