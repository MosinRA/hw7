public class MyClass {
    @BeforeSuite
    public static void init() {
        System.out.println("init");
    }

    @Test(priority = 3)
    public static void test1() {
        System.out.println("test1");
    }

    @Test(priority = 7)
    public static void test2() {
        System.out.println("test2");
    }

    @Test(priority = 1)
    public static void test3() {
        System.out.println("test3");
    }

    @Test(priority = 4)
    public static void test4() {
        System.out.println("test4");
    }

    public static void method() {
        System.out.println("method");
    }


    @AfterSuite
    public static void stop() {
        System.out.println("stop");
    }
}
