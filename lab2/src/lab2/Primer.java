package lab2;

//123jj.1j.12jj.123jjj.jj.12345 .6789.11.1...kk.1k2...1111111

public class Primer {
    public static void main(String[] args) {
        String str = String.join(" ", args);
        StringBuilder strBuilder = new StringBuilder(str);
        for (int i = 0; i < strBuilder.length(); i++) {
            if(strBuilder.charAt(i) == '.' && i + 2 < strBuilder.length()) {
                if(Character.isDigit(strBuilder.charAt(i + 1)) && Character.isDigit(strBuilder.charAt(i + 2))) {
                    int index = i + 3;
                    for (int j = index; j < strBuilder.length(); j++) {
                        if (!Character.isDigit(strBuilder.charAt(j)))
                            break;
                        index++;
                    }
                    strBuilder.delete(i + 3, index);
                    i += 2;
                }
            }
        }
        str = strBuilder.toString();
        System.out.println(str);
    }
}
