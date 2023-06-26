package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Scanner scanner = new Scanner(System.in);
        final String path = "org.example.math.";
        String cmd = "";
        do {
            System.out.print(">");
            cmd = scanner.nextLine();
            final String[] params = cmd.split(",");
            String className = "RemoteMath";
            String calculus;
            int a, b;
            try {
                if (params.length == 3) {
                    calculus = params[0];
                    a = Integer.parseInt(params[1]);
                    b = Integer.parseInt(params[2]);
                } else {
                    className = params[0];
                    calculus = params[1];
                    a = Integer.parseInt(params[2]);
                    b = Integer.parseInt(params[3]);
                }
                final Class<?> clazz = new ClassReloader().loadClass(path + className);
                final Object obj = clazz.getDeclaredConstructor().newInstance();
                final Method method = clazz.getMethod(calculus, int.class, int.class);
                System.out.println(">" + method.invoke(obj, a, b));
            } catch (Exception e) {
                System.out.println("Usage: [MathClass,](Command),(number),(number)");
                e.printStackTrace();
            }
        } while (!isQuit(cmd));
    }

    public static boolean isQuit(String cmd) {
        return "quit".equals(cmd);
    }
}