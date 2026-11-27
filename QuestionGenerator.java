import java.util.Random;

public class QuestionGenerator {
    Random r = new Random();
    String q;
    int ans;

    void generate() {
        while (true) {
            int terms = 3 + r.nextInt(2);
            int[] nums = new int[terms];
            char[] ops = new char[terms - 1];
            char[] all = {'+', '-', '*', '/'};

            for (int i = 0; i < terms; i++) {
                nums[i] = 2 + r.nextInt(11);
            }
            for (int i = 0; i < terms - 1; i++) {
                ops[i] = all[r.nextInt(4)];
            }

            double res = solve(nums, ops);
            if (res == (int) res && res >= -100 && res <= 500) {
                q = "";
                for (int i = 0; i < terms; i++) {
                    q = q + nums[i];
                    if (i < terms - 1) {
                        q = q + " " + ops[i] + " ";
                    }
                }
                ans = (int) res;
                break;
            }
        }
    }

    double solve(int[] nums, char[] ops) {
        double[] n = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = nums[i];
        }
        char[] o = new char[ops.length];
        for (int i = 0; i < ops.length; i++) {
            o[i] = ops[i];
        }

        int count = o.length;
        for (int i = 0; i < count; i++) {
            if (o[i] == '*' || o[i] == '/') {
                if (o[i] == '*') {
                    n[i] = n[i] * n[i + 1];
                } else {
                    n[i] = n[i] / n[i + 1];
                }
                for (int j = i + 1; j < count; j++) {
                    n[j] = n[j + 1];
                    o[j - 1] = o[j];
                }
                count--;
                i--;
            }
        }

        double res = n[0];
        for (int i = 0; i < count; i++) {
            if (o[i] == '+') {
                res = res + n[i + 1];
            } else {
                res = res - n[i + 1];
            }
        }
        return res;
    }
}
