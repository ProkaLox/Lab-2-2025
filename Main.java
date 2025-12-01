import functions.FunctionPoint;
import functions.TabulatedFunction;


public class Main{
    public static  void main(String[] args){
        // Демонстрация функции sin(x)
        System.out.println("-_-_-_-Ф-ия: y = x^2-_-_-_-");
        TabulatedFunction sqrFn = new TabulatedFunction(0, 10, 5);
        for (int i = 0; i < sqrFn.getPointsCount(); i++){
            double x = sqrFn.getPointX(i);
            sqrFn.setPointY(i, Math.pow(sqrFn.getPointX(i),2));
        }

        System.out.println("Исходная ф-ия:");
        for (int i = 0; i < sqrFn.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": x=" + sqrFn.getPointX(i) + " y=" + sqrFn.getPointY(i));
        }

        // Демонстрация добавления точки
        System.out.println("\n_-_-_-Добавление точки (3, 15)-_-_-_");
        sqrFn.addPoint(new FunctionPoint(3, 15));
        System.out.println("После добавления:");
        for (int i = 0; i < sqrFn.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": x=" + sqrFn.getPointX(i) + " y=" + sqrFn.getPointY(i));
        }

        // Демонстрация удаления точки
        System.out.println("\n-_-_-_-Удаление точки с индексом 4-_-_-_-");
        sqrFn.deletePoint(4);
        System.out.println("После удаления:");
        for (int i = 0; i < sqrFn.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": x=" + sqrFn.getPointX(i) + " y=" + sqrFn.getPointY(i));
        }

        // Демонстрация изменения точки
        System.out.println("\n-_-_-_-Изменение точки с индексом 1-_-_-_-");
        sqrFn.setPoint(1, new FunctionPoint(1.5, 8));
        System.out.println("После изменения:");
        for (int i = 0; i < sqrFn.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": x=" + sqrFn.getPointX(i) + " y=" + sqrFn.getPointY(i));
        }

        // Демонстрация интерполяции
        System.out.println("\n-_-_-_-Интерполяция значений-_-_-_-");
        System.out.println(3.5 + "^2 = " + sqrFn.getFunctionValue(3.5));
        System.out.println(6.5 + "^2 = " + sqrFn.getFunctionValue(6.5));
        System.out.println(8 + "^2 = " + sqrFn.getFunctionValue(8));

        // Демонстрация границ области определения
        System.out.println("\n-_-_-_-Границы области определения-_-_-_-");
        System.out.println("Левая граница: " + sqrFn.getLeftDomainBorder());
        System.out.println("Правая граница: " + sqrFn.getRightDomainBorder());

        // Демонстрация работы с отдельными координатами
        System.out.println("\n-_-_-_-Изменение координат по отдельности-_-_-_-");
        System.out.print("До изменения: ");
        System.out.println("Точка 0: " + sqrFn.getPointX(0) + "; " + sqrFn.getPointY(0));

        sqrFn.setPointX(0, 1);
        sqrFn.setPointY(0, 3);

        System.out.print("После изменения: ");
        System.out.println("Точка 0: " + sqrFn.getPointX(0) + "; " + sqrFn.getPointY(0));

        // Итоговое состояние функции
        System.out.println("\n-_-_-_-Итоговое состояние функции-_-_-_-");
        for (int i = 0; i < sqrFn.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": x=" + sqrFn.getPointX(i) + " y=" + sqrFn.getPointY(i));
        }
        System.out.println("Всего точек: " + sqrFn.getPointsCount());
    }
}