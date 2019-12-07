package array.pk0;

public class _66 {
    public int[] plusOne(int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--){
            int sum = digits[i] + 1 + carry;
            carry = sum / 10;
            digits[i] = sum % 10;
        }
        if (carry == 1) {
            int[] news = new int[digits.length + 1];
            news[0] = carry;
            for (int i = 1; i< news.length; i++){
                news[i] = digits[i - 1];
            }
            return news;
        }
        return digits;
    }
}
