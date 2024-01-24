package ra.demo.util;

public class Validate {
    public static String getStudentNameValidate(){
        while(true) {
            String input = InputMethods.getString();
            if (input.trim().length()>=6){
                return input;
            }
            System.err.println("Độ dài tối thiểu 6 kí tự");
        }
    }
}
