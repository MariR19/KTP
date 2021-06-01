import java.util.HashMap;


// Задача №3
public class Task_3 {
    // Задание №1: Функция, которая возвращает массив, в котором все население округлено до ближайшего миллиона.
    public static HashMap<String,Integer> millionsRounding(HashMap<String,Integer> arr){
        int people;
        int temp;

        for (String i: arr.keySet()){
            people = arr.get(i);
            temp = people%1000000;
            people = people/1000000*1000000;

            if(temp>=500000){
                people+=1000000;
            }
            arr.put(i,people);
        }
        return arr;
    }
    // Задание №2: Функция, которая возвращает остальные стороны, зная короткую сторону треугольника (30°,60°,90°).
    public static double[] otherSides(int a){
        double b = (double) Math.round((2*a)*100)/100;
        double c = (double) Math.round((a*Math.sqrt(3))*100)/100;
        return new double[]{b,c};
        }
    // Задание №3: Функция, которая имитирует игру "камень, ножницы, бумага".
    public static String rps(String player1,String player2){
        if ((player1.equals("rock") && player2.equals("scissors")) || (player1.equals("scissors") && player2.equals("paper")) || (player1.equals("paper") && player2.equals("rock"))){
            return "Player 1 wins";
        }
        else if ((player2.equals("rock") && player1.equals("scissors")) || (player2.equals("scissors") && player1.equals("paper")) || (player2.equals("paper") && player1.equals("rock"))){
            return "Player 2 wins";
        }
        else {
            return "TIE";
        }
    }
    // Задание №4: Функция, которая возвращает разницу между суммой четных и нечетных чисел массива.
    public static int warOfNumbers(int[] arr){
        int even=0;
        int odd=0;
        for (int i=0;i<arr.length;i++){
            if (arr[i]%2==0){
                even+=arr[i];
            }
            else {
                odd+=arr[i];
            }
        }
        return Math.abs(even-odd);
    }
    // Задание №5: Функция для обратного обращения. Все буквы в нижнем регистре должны быть прописными, и наоборот.
    public static String reverseCase(String str){
        String str1="";
        for (int i=0;i<str.length();i++){
            if (Character.isLowerCase(str.charAt(i))){
                str1+=Character.toUpperCase(str.charAt(i));
            }
            else if (Character.isUpperCase(str.charAt(i))){
                str1+=Character.toLowerCase(str.charAt(i));
            }
            else {
                str1+=str.charAt(i);
            }
        }
        return str1;
    }
    // Задание №6: Функция, которая принимает слово и добавляет inator или -inator и длину исходного слова с "000".
    public static String inatorInator(String word){
        String wordNew = "";
        char end = word.charAt(word.length()-1);
        if (end=='a' || end=='e' || end=='i' || end=='o' || end=='u' || end=='y'){
            wordNew = word+"-inator "+word.length()+"000";
        }
        else {
            wordNew = word+"inator "+word.length()+"000";
        }
        return wordNew;
    }
    // Задание №7: Функция, которая возвращает true, если кирпич может поместиться в отверстие.
    // высота(a), ширина(b) и глубина(c) кирпича; ширина(w) и высота(h) отверстия
    public static boolean doesBrickFit(int a,int b, int c, int w,int h){
        int[] abc = new int[]{a,b,c};
        for (int i=0;i<abc.length;i++){
            for (int j=0;j<abc.length;j++){
                if (i!=j && ((abc[i]<=w && abc[j]<=h) || (abc[i]<=h && abc[j]<=w))){
                    return true;
                }
            }
        }
        return false;
    }
    // Задание №8: Функция, которая возвращает максимальное расстояние, которое может проехать автомобиль.
    public static double totalDistance(double fuel,double consumption,int passengers,boolean AC){
        if (passengers!=0){
            consumption = consumption+passengers*consumption*0.05;
        }
        if (AC){
            consumption = consumption+consumption*0.1;
        }
        return (double) Math.round((fuel/consumption*100)*100)/100;
    }
    // Задание №9: Функция, которая принимает массив чисел и возвращает среднее значение всех этих чисел.
    public static double mean(int[] arr){
        double sum=0;
        for (int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        return (double) Math.round((sum/arr.length)*100)/100;
    }
    // Задание №10: Функция, которая возвращает true, если сумма цифр имеет ту же четность, что и все число.
    public static boolean parityAnalysis(int x){
        int y=0;
        String str = String.valueOf(x);
        for (int i=0;i<str.length();i++){
            y+=Integer.valueOf(str.charAt(i));
        }
        return ((x%2!=0 && y%2!=0) || (x%2==0 && y%2==0));
    }
}

