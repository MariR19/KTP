public class Main {

    // Точка входа в программу
    public static void main(String[] args) {

        // Инициализация точек
        Point3d A = new Point3d(10,15,7);
        Point3d B = new Point3d(7,0,-2);
        Point3d C = new Point3d(1,11,11.1);

        if(A.equals(B) || B.equals(C) || C.equals(A)){
            System.out.println("ОШИБКА!!!\nТочки с одинаковыми координатами.");
        }
        else {
            double S = computeArea(A,B,C);
            System.out.printf("Площадь треугольника S = %.3f %n",S);
        }
    }


    // Метод расчета площади треугольника
    public static double computeArea(Point3d A,Point3d B,Point3d C){

        double AB = A.distanceTo(B);
        double BC = B.distanceTo(C);
        double CA = C.distanceTo(A);

        double p=(AB+BC+CA)/2;
        double S=Math.sqrt(p*(p-AB)*(p-BC)*(p-CA));

        return S;
    }
}
