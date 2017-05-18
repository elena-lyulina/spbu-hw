package spbu.sem2.hw7.task1;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * prints code that approximates to the source code.
 */
public class ClassPrinter {
    int[] arr;
    PrintWriter out;
    Class clazz;

    /** constructor.
     * @param clazz class you want to describe
     * @param file file in which prints code of class
     * @throws IOException
     */
    public ClassPrinter(Class clazz, File file) throws IOException {
        out = new PrintWriter(file);
        this.clazz = clazz;
    }

    /** prints proximate code to class "clazz". */
    public void print() throws IOException {
        printPackage();
        printName();
        printFields();
        printConstructors();
        printMethods();
        out.close();
    }

    /** prints package. */
    private void printPackage() throws IOException {
        String pack = clazz.getPackage().toString();
        if (pack.length() > 0) {
            out.print(pack);
            addSC();
            out.println();
        }
    }

    /** prints name of class with its superclass and interfaces. */
    private void printName() {
        printAnnotation(clazz.getAnnotations());
        int modifiers = clazz.getModifiers();
        out.print(getModifiers(modifiers));
        out.print("class " + clazz.getSimpleName());

        String superClass = clazz.getSuperclass().getSimpleName().toString();
        if (!superClass.equals("Object"))
            out.print(" extends " + superClass);

        Class[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            out.print(" implements ");
            for (int i = 0; i < interfaces.length; i++) {
                out.print(interfaces[i].getSimpleName());
            }
        }
        out.println(" {");
    }

    /** prints fields with annotation. */
    private void printFields() {
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                printAnnotation(fields[i].getAnnotations());
                addTab(1);
                out.print(getModifiers(fields[i].getModifiers()) + getType(fields[i].getType()) + " " + fields[i].getName());
                addSC();
            }
            out.println();
        }
    }

    /** prints constructors with annotations. */
    private void printConstructors() {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 0) {
            for (int i = 0; i < constructors.length; i++) {
                printAnnotation(constructors[i].getAnnotations());
                addTab(1);
                out.print(getModifiers(constructors[i].getModifiers()) + clazz.getSimpleName() + "(");
                out.print(getParameters(constructors[i].getParameterTypes()) + ")");
                printExceptions(constructors[i].getExceptionTypes());
                addSC();
            }
            out.println();
        }
    }

    /** prints methods with annotations. */
    private void printMethods() {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            printAnnotation(methods[i].getAnnotations());
            addTab(1);
            out.print(getModifiers(methods[i].getModifiers()) + getType(methods[i].getReturnType()) + " " + methods[i].getName() + "(");
            out.print(getParameters(methods[i].getParameterTypes()) + ")");
            printExceptions(methods[i].getExceptionTypes());
            addSC();
        }
        out.print("}");
    }

    /** prints semicolon. */
    private void addSC() {
        out.println(";");
    }

    /**
     * pprints tab.
     * @param n amount of tabs you want to print
     */
    private void addTab(int n) {
        for (int i = 0; i < n; i++) {
            out.print("\t");
        }
    }

    /** gets Modifiers. */
    private String getModifiers(int i) {
        String modifiers = "";
        if (Modifier.isPublic(i)) modifiers += "public ";
        if (Modifier.isProtected(i)) modifiers += "protected ";
        if (Modifier.isPrivate(i)) modifiers += "private ";
        if (Modifier.isStatic(i)) modifiers += "static ";
        if (Modifier.isAbstract(i)) modifiers += "abstract ";
        return modifiers;
    }

    /** gets Type of class "clazz". */
    private String getType(Class clazz) {
        String type = clazz.getSimpleName();
        return type;
    }

    /** gets parameters. */
    private String getParameters(Class[] parameters) {
        String parameter = "";
        for (int i = 0; i < parameters.length; i++) {
            if (i > 0)
                parameter += ", ";
            parameter += getType(parameters[i]) + " parameter" + i;
        }
        return parameter;
    }

    /** prints annotations. */
    private void printAnnotation(Annotation[] annotations) {
        for (int i = 0; i < annotations.length; i++) {
            addTab(1);
            out.println("@" + annotations[i].annotationType().getSimpleName());
        }
    }

    /** prints exceptions. */
    private void printExceptions(Class<?>[] exceptionTypes) {
        if (exceptionTypes.length != 0) {
            for (int i = 0; i < exceptionTypes.length; i++) {
                if (i == 0)
                    out.print(" throws ");
                else
                    out.print(",");
                out.print(exceptionTypes[i].getSimpleName());
            }
        }
    }
}