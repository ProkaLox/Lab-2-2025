package functions;

import java.awt.*;

public class TabulatedFunction {
    private FunctionPoint Points[];
    private int length;

    //Конструктор по количествую элементов
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        this.Points =  new FunctionPoint[pointsCount];
        this.length = Math.max(pointsCount,2); //длина должжна быть больше 2

        if (leftX > rightX) { //Проверка на соответствие левой и правой границы
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }

        double delta = (rightX - leftX) / (length-1); //Создание точек через равные по х промежутки
        for (int i = 0; i < pointsCount; i++){
            double x = leftX + i*delta;
            this.Points[i] = new FunctionPoint(x,0);
        }
    }
    //Конструктор по значениям
    public TabulatedFunction(double leftX, double rightX, double[] values){
        if(values.length < 2) { //длина должжна быть больше 2
            double[] valuesExtended = {values[0], values[0]};//Добавляю копию точки в массив
            values = valuesExtended;
        }
        this.Points =  new FunctionPoint[values.length];
        this.length = values.length;

        if (leftX > rightX) { //Проверка на соответствие левой и правой границы
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }

        double delta = (rightX - leftX) / (length-1);
        for (int i = 0; i < length; i++){ //Создание точек через равные по х промежутки
            double x = leftX + i*delta;
            this.Points[i] = new FunctionPoint(x, values[i]);
        }
    }

    //Геттеры
    public double getLeftDomainBorder(){
        return this.Points[0].getX();
    }
    public double getRightDomainBorder(){
        return Points[length-1].getX();
    }
    public int getPointsCount(){
        return this.length;
    }
    public FunctionPoint getPoint(int index){
        return new FunctionPoint(Points[index]);
    }

    public double getFunctionValue(double x){
        FunctionPoint p1 = new FunctionPoint();
        FunctionPoint p2 = p1;

        if (x<this.getLeftDomainBorder() || x>this.getRightDomainBorder())
            return Double.NaN; //x в пределах области функции
        for(int i = 0; i<length-1; i++){
            if (Points[i+1].getX()>=x) { //Ищщем нужный отрезок между точками
                p1 = Points[i];
                p2 = Points[i+1];
                //Считаем значение по формуле
                return p1.getY() + (x - p1.getX())* (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            }
        }
        return Double.NaN;
    }

    public double getPointX(int index){
        if(index<0 || index > length-1) return Double.NaN; //Проверка индекса
        return this.Points[index].getX();
    }
    public double getPointY(int index){
        if(index<0 || index > length-1) return Double.NaN; //Проверка индекса
        return this.Points[index].getY();
    }

    //Сеттеры
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index > length-1 || point == null) return; //Проверка индекса
        //Проверка попадания в интерваллы
        if (point.getX() <= Points[index - 1].getX() || ( index < length -1 && point.getX() >= Points[index + 1].getX())) return;
        Points[index] = new FunctionPoint(point);
    }

    public void setPointX(int index, double x){
        if (index < 0 || index > length-1) return; //Проверка индексов
        //Проверка попадания в интерваллы
        if ( (index > 0 && x <= Points[index - 1].getX()) || ( index < length -1 && x >= Points[index + 1].getX())) return;
        Points[index].setX(x);
    }

    public void setPointY(int index, double y){
        if (index < 0 || index > length-1) return; //Проверка индексов
        Points[index].setY(y);
    }

    public void deletePoint(int index){
        if (length <= 2 || index < 0 || index > length -1) return; //Проверка индекса и длины

        for (int i = index; i < length-1; i++) //Сдвигаем элементы влево
            Points[i] = Points[i+1];
        Points[length-1] = null; //Заменяем последний на ноль
        length--; //Уменьшаем переменную длины
    }

    public void addPoint(FunctionPoint point){
        if (point == null) return;

        int index = 0; //Ищщем место для новой точки по х
        while (index < length && point.getX() > Points[index].getX()){
            index++;
        }
        if(Points[index].getX() == point.getX()){ //Если совпадает с другой точкой
            return;
        }

        if (length == Points.length){ //Если массив первоначального размера, увеличиваем его
            FunctionPoint[] extendedPoints = new FunctionPoint[Points.length + 1];
            for (int i = 0; i< length; i++)
                extendedPoints[i] = Points[i];

            Points = extendedPoints;
        }

        for (int i=length; i> index; i--){ //Сдвиг элементов вправо
            Points[i] = Points[i-1];
        }
        Points[index] = new FunctionPoint(point); //Добавление элемента и увеличение переменной длины
        length++;
    }
}