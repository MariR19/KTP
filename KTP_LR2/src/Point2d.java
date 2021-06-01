// Точка в двумерном пространстве
public class Point2d {

    // Координаты X и Y (можно через protected)
    private double xCoord;
    private double yCoord;

    // Конструктор инициализации
    public Point2d ( double x, double y) {
        xCoord = x;
        yCoord = y;
    }
    // Конструктор по умолчанию
    public Point2d () {
        this(0,0);
    }

    // Возвращение координаты X и координаты Y
    public double getX () {
        return xCoord;
    }
    public double getY () {
        return yCoord;
    }

    // Установка значения координаты X и координаты Y
    public void setX ( double val) {
        xCoord = val;
    }
    public void setY ( double val) {
        yCoord = val;
    }



}