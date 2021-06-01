import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        // Задача №1
        System.out.println("Задача №1");
        System.out.println(Task_1.convert(5));
        System.out.println(Task_1.points(13,12));
        System.out.println(Task_1.footballPoints(3,4,2));
        System.out.println(Task_1.divisibleByFive(5));
        System.out.println(Task_1.and(true,false));
        System.out.println(Task_1.howManyWall(54,1,43));
        System.out.println(Task_1.squaed(5));
        System.out.println(Task_1.profitableGamble(0.2, 50, 9));
        System.out.println(Task_1.frames(1,1));
        System.out.println(Task_1.mod(5,2));

        // Задача №2
        System.out.println("\nЗадача №2");
        System.out.println(Task_2.oppositeHouse(1,3));
        System.out.println(Task_2.nameShuffle("Donald Trump"));
        System.out.println(Task_2.discount(89, 20));
        System.out.println(Task_2.differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(Task_2.equal(3, 4, 3));
        System.out.println(Task_2.reverse("Hello World"));
        System.out.println(Task_2.programmers(147, 33, 526));
        System.out.println(Task_2.getXO("ooxx"));
        System.out.println(Task_2.bomb("There is a bomb."));
        System.out.println(Task_2.sameAscii("EdAbIt", "EDABIT"));

        // Задача №3
        // Для задания 1
        HashMap<String,Integer> arr = new HashMap<>();
        arr.put("Nice", 942208);
        arr.put("Abu Dhabi", 1482816);
        arr.put("Naples", 2186853);
        arr.put("Vatican City", 572);

        System.out.println("\nЗадача №3");
        System.out.println(Task_3.millionsRounding(arr));
        double[] arr1 = Task_3.otherSides(1);
        for (double i: arr1){
            System.out.print(i+"  ");
        }
        System.out.println("\n"+Task_3.rps("rock", "paper"));
        System.out.println(Task_3.warOfNumbers(new int[]{2, 8, 7, 5}));
        System.out.println(Task_3.reverseCase("Happy Birthday"));
        System.out.println(Task_3.inatorInator("Shrink"));
        System.out.println(Task_3.doesBrickFit(1, 1, 1, 1, 1));
        System.out.println(Task_3.totalDistance(70.0, 7.0, 0, false));
        System.out.println(Task_3.mean(new int[]{1, 0, 4, 5, 2, 4, 1, 2, 3, 3, 3}));
        System.out.println(Task_3.parityAnalysis(243));

        // Задача №4
        System.out.println("\nЗадача №4");
        System.out.println(Task_4.sevenBoom(new int[]{2, 55, 60, 97, 86}));
        System.out.println(Task_4.cons(new int[]{5, 1, 4, 3, 2}));
        System.out.println(Task_4.unmix("hTsii  s aimex dpus rtni.g"));
        System.out.println(Task_4.noYelling("I just!!! can!!! not!!! believe!!! it!!!"));
        System.out.println(Task_4.xPronounce("Inside the x box was a xylophone"));
        System.out.println(Task_4.largestGap(new int[]{9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5}));
        System.out.println(Task_4.myMethod(7977));
        System.out.println(Task_4.commonLastVowel("Watch the characters dance!"));
        System.out.println(Task_4.memeSum(1222, 30277));
        System.out.println(Task_4.unrepeated("teshahset"));

        // Задача №5
        System.out.println("\nЗадача №5");
        System.out.println(Task_5.sameLetterPattern("ABAB", "CDCD"));
        System.out.println(Task_5.spiderVsFly("H3", "E2"));
        System.out.println(Task_5.digitsCount(1289396387328L));
        System.out.println(Task_5.totalPoints(new String[]{"cat", "create", "sat"},"caster"));
        System.out.println(Task_5.longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println(Task_5.takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(Task_5.rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s."));
        System.out.println(Task_5.maxPossible(523, 76));
        System.out.println(Task_5.timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(Task_5.isNew(123));

        // Задача №6
        System.out.println("\nЗадача №6");
        System.out.println(Task_6.hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(Task_6.collect("intercontinentalisationalism", 6));
        System.out.println(Task_6.nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(Arrays.toString(Task_6.twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5 }, 45)));
        System.out.println(Arrays.toString(Task_6.isExact(6)));
        System.out.println(Task_6.fractions("0.(6)"));
        System.out.println(Task_6.pilish_string("33314444"));
        System.out.println(Task_6.generateNonconsecutive(2));
        System.out.println(Task_6.isValid("aabbcd"));
        System.out.println(Arrays.deepToString(Task_6.sumsUp(new int[]{1, 2, 3, 4, 5})));
    }
}
