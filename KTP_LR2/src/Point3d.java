// Точка в трехмерном пространстве (наследуется от Point2d)
public class Point3d extends Point2d {

    // Координата Z
    private double zCoord;

    // Конструктор инициализации объекта класса
    public Point3d(double x,double y,double z){
        super(x,y);
        zCoord=z;
    }
    // Конструктор по умолчанию
    public Point3d(){
        this(0,0,0);
    }

    // Возвращение координаты Z и установка значения координаты Z
    public double getZ(){return zCoord;}
    public void setZ(double val){zCoord=val;}

    // Перезапись метода сравнения
    @Override
    public boolean equals(Object O){
        Point3d P;
        if(O!=null && O.getClass()==getClass()){
           P=(Point3d)O;
           if(getX()==P.getX() && getY()==P.getY() && getZ()==P.getZ()){
               return true;
           }
           else {return false;}
        }
        else {return false;}
    }

    // Метод расчето растояния от точки до точки
    public double distanceTo(Point3d P){
        double x1=getX();
        double y1=getY();
        double z1=getZ();
        double x2=P.getX();
        double y2=P.getY();
        double z2=P.getZ();

        double distance=Math.abs(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2)));

        return distance;
    }


}
