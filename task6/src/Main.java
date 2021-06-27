import java.util.*;
public class Main {

    public static void main(String[] args) {
        int[] maht = isExact(6);
        for (int i: maht){
            System.out.print    (i + " ");
        }
        //System.out.println(maht);
    }

    //1

    public static int[] getLetterSet(String str) {
        int[] set = new int[26];
        for (char c : str.toCharArray())
            set[c - 97]++;
        return set;
    }

    public static String onlyLetters(String str) {
        str = str.toLowerCase();
        String res = "";
        for (char c : str.toCharArray())
            if (97 <= c && c <= 122)
                res += c;
        return res;
    }

    public static String hiddenAnagram(String a, String b) {
        a = onlyLetters(a);
        b = onlyLetters(b);
        int[] setB = getLetterSet(b);
        for (int i = 0; i <= a.length() - b.length(); i++) {
            String substr = a.substring(i, i+b.length());
            int[] setA = getLetterSet(substr);
            if (Arrays.equals(setA, setB)) {
                return substr;
            }
        }
        return "noutfond";
    }

    //2

    public static List<String> collect(String str,int n) {
        List<String> list= new ArrayList<String>();
        col(list,str,n);
        Collections.sort(list);
        return list;
    }
    public static void col(List<String> list,String str, int n) {
        if(str.length()<n)
            return;
        list.add(str.substring(0,n));
        col(list,str.substring(n),n);
    }

    //3

    public static int[] getCharset(String word) {
        int[] charset = new int[127];
        for (char c : word.toCharArray()) charset[c]++;
        return charset;
    }

    public static String nicoCipher(String message, String key) {
        for (int i = 0; i < (message.length()+key.length()) % key.length(); i++)
            message += ' ';
        int[] set = getCharset(key);
        int[] setCount = set.clone();
        int counter = 1;
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0) {
                if (set[i] > 1)
                    counter += set[i] - 1;
                set[i] = counter++;
            }
        int[] offsets = new int[key.length()];
        for (int i = 0; i < key.length(); i++)
            offsets[set[key.charAt(i)]-setCount[key.charAt(i)]--] = i;
        String res = "";
        for (int i = 0; i < message.length(); i++) {
            int index = (i / offsets.length) * offsets.length + offsets[i % offsets.length];
            res += message.charAt(index);
        }

        return res;
    }

    //4

    public static int[] twoProduct(int[] arr, int val) {
        int first = 0, second = 0;
        int[] answer = new int[2];
        for (int i = arr.length - 1; i > 0; --i) {
            second = arr[i];
            for (int j = i - 1; j >= 0; --j) {
                first = arr[j];
                if (first * second == val) {
                    answer[0] = first;
                    answer[1] = second;
                }
            }
        }
        return answer;
    }

    //5

    public static int[] isExact(int val) {
        int[] answer = new int[0];
        int number = isFact(val,2);
        if(number!=-1)
        {
            answer=new int[] {val,number};
        }
        return answer;
    }
    public static int isFact(int number, int k) {
        if(number==1) {
            return k-1;
        }
        if(number%k!=0)
            return -1;
        return isFact(number/k,k+1);
    }

    //6

    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
        if (startBracket != -1) {
            String f = frac.substring(startBracket+1, frac.length()-1); //после
            frac = frac.substring(0, startBracket); //до
            for (int i = 0; i < 9 - f.length(); i++)
                frac += f;
        }
        double a = Double.parseDouble(frac);
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001) div++;
        return (int)Math.ceil(a * div) + "/" + div;
    }

    //7

    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0;
        while (str.length() > 0) {
            int p = pi.charAt(piIndex) - 48;
            int n = Math.min(p, str.length());
            res += str.substring(0, n);
            if(str.length() - p <0){
                for(int i =0; i < p - str.length(); i++){
                    res+=str.charAt(str.length()-1);
                }
            }
            str = str.substring(n);
            piIndex++;
            if (str.length() > 0) res += ' ';
            if (piIndex == 15) break;
        }
        return res;
    }

    //8

    public static String generateNonconsecutive(int num) {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < Math.pow(2, num); i++) {
            sb.delete(0, sb.length());
            sb.append(Integer.toBinaryString(i));

            while(sb.length() < num) {
                sb.insert(0, '0');
            }

            if (!sb.toString().contains("11")) {
                res.append(sb).append(" ");
            }
        }
        return res.toString();
    }

    //9

    public static String isValid(String str) {
        int[] set = getLetterSet(str);
        Arrays.sort(set);
        int min = 0;
        for(int i: set){
            if(i!=0){
                min = i;
                break;
            }
        }
        boolean help = true;
        for (int i = 0; i< set.length; i++){
            if(set[i]==0 || set[i]==min){ }
            else{
                if(help && set[i]-1 == min){
                    help = false;
                }
                else{
                    return "НЕТ";
                }
            }
        }
        return "ДА";

    }

    //10

    public static int[][] sumsUp(int[] arr) {
        int[][] res = new int[arr.length / 2][2];
        int index = 0;

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] + arr[j] == 8) {
                    res[index][0] = Math.min(arr[i], arr[j]);
                    res[index][1] = Math.max(arr[i], arr[j]);
                    ++index;
                }
            }
        }
        return res;
    }
}