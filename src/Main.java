import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;


public class Main {

    public static void main(String[] args) throws Exception {
        Class c = MyClass.class;
        Object testObj = null;
        Method[] methods = c.getDeclaredMethods();
        ArrayList<Method> al = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;
        for (Method o : c.getDeclaredMethods()) {
            if (o.isAnnotationPresent(Test.class)) {
                al.add(o);
            }
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) beforeMethod = o;
                else throw new RuntimeException("Встречается больше одного метода с аннотацией BeforeSuite");
            }
            if (o.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) afterMethod = o;
                else throw new RuntimeException("Встречается больше одного метода с аннотацией AfterSuite");
            }
            al.sort(new Comparator<Method>() {
                @Override
                public int compare(Method o1, Method o2) {
                    return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
                }
            });

        }
        if (beforeMethod != null) beforeMethod.invoke(testObj);
        for (Method o : al) o.invoke(testObj);
        if (afterMethod != null) afterMethod.invoke(testObj);
    }
}