import java.util.*;
import java.util.regex.*;

abstract class Question {
    String questionText;
    String correctAnswer;
    String studentAnswer;
    double points;

    Question(String q, String c, String s, double p) {
        questionText = q;
        correctAnswer = c;
        studentAnswer = s;
        points = p;
    }

    abstract double grade();

    abstract String getType();
}

class MCQ extends Question {
    MCQ(String q, String c, String s, double p) {
        super(q, c, s, p);
    }

    double grade() {
        return studentAnswer.equals(correctAnswer)
                ? points : 0;
    }

    String getType() {
        return "MCQ";
    }
}

class TrueFalse extends Question {
    TrueFalse(String q, String c, String s, double p) {
        super(q, c, s, p);
    }

    double grade() {
        return studentAnswer.equals(correctAnswer)
                ? points : 0;
    }

    String getType() {
        return "TF";
    }
}

class Essay extends Question {
    Essay(String q, String c, String s, double p) {
        super(q, c, s, p);
    }

    double grade() {
        String[] keywords = correctAnswer.split(",");
        int count = 0;

        for (String keyword : keywords) {
            if (studentAnswer.toLowerCase().contains(
                    keyword.trim().toLowerCase())) {
                count++;
            }
        }

        if (count >= 2) {
            return points * 0.75;
        } else if (count == 1) {
            return points * 0.50;
        }

        return 0;
    }

    String getType() {
        return "ESSAY";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;

        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String type = line.substring(0, line.indexOf(' '))
                             .toUpperCase();

            Matcher matcher = pattern.matcher(line);
            List<String> fields = new ArrayList<>();

            while (matcher.find()) {
                fields.add(matcher.group(1));
            }

            String q = fields.get(0);
            String correct = fields.get(1);
            String student = fields.get(2);

            String remaining = line.substring(
                    line.lastIndexOf('"') + 1).trim();

            double points = Double.parseDouble(remaining);

            Question question;

            switch (type) {
                case "MCQ":
                    question = new MCQ(q, correct, student, points);
                    break;

                case "TF":
                    question = new TrueFalse(
                            q, correct, student, points);
                    break;

                case "ESSAY":
                    question = new Essay(
                            q, correct, student, points);
                    break;

                default:
                    continue;
            }

            double score = question.grade();

            System.out.printf("%s: %.2f%n",
                    question.getType(), score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
