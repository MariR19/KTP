import java.util.Locale;


// Задача №2
public class Task_2 {
    // Задание №1: Функция, которая принимает номер дома и длину улицы и возвращает номер дома на другой стороне.
    public static int oppositeHouse(int home,int n){
        return n*2-(home-1);
    }

    // Задание №2: Метод, который принимает строку (имя и фамилию) и возвращает строку с заменой имени и фамилии.
    public static String nameShuffle(String name){
        int index = name.indexOf(" ");
        String firstName = name.substring(0,index);
        String lastName = name.substring(index+1);
        return lastName+" "+firstName;
    }

    // Задание №3: Функция, которая принимает (исходную цену и процент скидки) и возвращает конечную цену после скидки.
    public static double discount(int price,int percent){
        return price*(100.0-percent)/100.0;
    }

    // Задание №4: Функция, которая принимает массив и возвращает разницу между наибольшим и наименьшим числами.
    public static int differenceMaxMin(int[] mas){
        int max=mas[0];
        int min=mas[0];
        for (int i=0;i<mas.length;i++){
            if (mas[i]>max){
                max=mas[i];
            }
            if (mas[i]<min) {
                min = mas[i];
            }
        }
        return max-min;
    }

    // Задание №5: Функция, которая принимает (a, b, c) и возвращает количество целых чисел, имеющих одинаковое значение.
    public static int equal(int a,int b,int c){
        int k;
        if (a==b && a==c){
            k=3;
        }
        else if (a==b || a==c || b==c){
            k=2;
        }
        else {
            k=0;
        }
        return k;
    }

    // Задание №6: Метод, который принимает строку в качестве аргумента и возвращает ее в обратном порядке.
    public static String reverse(String str){
        String rts = "";
        for (int i=str.length()-1;i>=0;i--){
            rts+=str.charAt(i);
        }
        return rts;
    }

    // Задание №7: Функция, которая принимает три числа и возвращает разницу между самым max и min.
    public static int programmers(int x,int y,int z){
        int[] mas = new int[]{x,y,z};
        return differenceMaxMin(mas);
    }

    // Задание №8: Функция, которая возвращает либо true, либо false, если строка имеет одинаковое количество x и o.
    public static boolean getXO(String str){
        str = str.toLowerCase(Locale.ROOT);
        int kx=0;
        int ko=0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)=='x'){
                kx++;
            }
            else if (str.charAt(i)=='o') {
                ko++;
            }
        }
        return kx==ko;
    }

    // Задание №9: Функция, которая находит слово "бомба". Ответит "ПРИГНИСЬ!", если да, иначе "Расслабься, бомбы нет".
    public static String bomb(String str){
        str = str.toLowerCase(Locale.ROOT);
        if (str.contains("bomb")){
            return "DUCK!";
        }
        else {
            return "Relax, there's no bomb.";
        }
    }

    // Задание №10: Функция, которая возвращает true, если сумма ASCII 1ой̆ строки равна сумме 2ой строки, иначе false.
    public static boolean sameAscii(String a1,String a2){
        int sum1=0;
        int sum2=0;
        for (int i=0;i<a1.length();i++){
            sum1+=a1.charAt(i);
        }
        for (int i=0;i<a2.length();i++){
            sum2+=a2.charAt(i);
        }
        return sum1==sum2;
    }
}
