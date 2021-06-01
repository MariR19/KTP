import java.awt.geom.Rectangle2D;


// Класс-наследник строит фрактал Манделброта
public class Mandelbrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    // Перезаписанный метод определяет область комплексной плоскости
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x=-2;
        range.y=-1.5;
        range.width=3;
        range.height=3;
    }

    // Перезаписанный метод для построения фрактала Манделброта
    @Override
    public int numIterations(double x, double y) {
        int i = 0;

        double num = 0;
        double num_i = 0;

        while (i < MAX_ITERATIONS && num * num + num_i * num_i < 4) {
            double new_num = num * num - num_i * num_i + x;
            double new_num_i = 2 * num * num_i + y;
            num = new_num;
            num_i = new_num_i;
            i += 1;
        }

        if (i == MAX_ITERATIONS) {
            return -1;
        }
        return i;
    }

    // Перезаписанный метод для получения названия фрактала
    @Override
    public String toString(){
        return "Mandelbrot";
    }
}
