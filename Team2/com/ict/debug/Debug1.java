package Team2.com.ict.debug;

public class Debug1 {
    public static void main(String[] args) {
        String str = null;
        try {
            // null인 문자열에 접근하여 NullPointerException 발생
            int 길이 = str.length();
            System.out.println("길이: " + 길이);
        } catch (NullPointerException e) {
            System.out.println("오류: " + e.getMessage());
        } finally {
            System.out.println("Finally 블록 실행");
        }
        System.out.println("예외 발생 후 프로그램이 계속 실행됩니다");
    }
}