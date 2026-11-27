import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
    QuestionGenerator gen = new QuestionGenerator();
    HangmanPanel panel = new HangmanPanel();
    JLabel lblQ = new JLabel();
    JLabel lblScore = new JLabel();
    JLabel lblMsg = new JLabel("Solve using BODMAS rule");
    JLabel lblAns = new JLabel("Answer:");
    JTextField txtAns = new JTextField();
    JButton btnSubmit = new JButton("Submit");
    JButton btnAgain = new JButton("Play Again");

    int qNo = 1;
    int score = 0;
    int wrong = 0;

    GameFrame() {
        setTitle("Math Hangman Game");
        setSize(520, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblQ.setBounds(20, 15, 480, 30);
        lblQ.setFont(new Font("Arial", Font.BOLD, 18));
        lblQ.setHorizontalAlignment(JLabel.CENTER);
        add(lblQ);

        lblScore.setBounds(20, 45, 480, 20);
        lblScore.setHorizontalAlignment(JLabel.CENTER);
        add(lblScore);

        panel.setBounds(120, 75, 280, 280);
        add(panel);

        lblAns.setBounds(110, 370, 60, 25);
        add(lblAns);

        txtAns.setBounds(175, 370, 120, 25);
        add(txtAns);

        btnSubmit.setBounds(305, 370, 90, 25);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnAgain.setBounds(305, 370, 90, 25);
        btnAgain.addActionListener(this);
        btnAgain.setVisible(false);
        add(btnAgain);

        lblMsg.setBounds(20, 405, 480, 20);
        lblMsg.setHorizontalAlignment(JLabel.CENTER);
        add(lblMsg);

        nextQuestion();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void nextQuestion() {
        gen.generate();
        lblQ.setText("Question " + qNo + "/10:  " + gen.q + " = ?");
        lblScore.setText("Score: " + score + "   Wrong: " + wrong + "/5");
        txtAns.setText("");
        txtAns.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgain) {
            qNo = 1;
            score = 0;
            wrong = 0;
            panel.wrong = 0;
            panel.repaint();
            txtAns.setEnabled(true);
            btnSubmit.setVisible(true);
            btnAgain.setVisible(false);
            lblMsg.setText("Solve using BODMAS rule");
            nextQuestion();
            return;
        }

        int userAns;
        try {
            userAns = Integer.parseInt(txtAns.getText().trim());
        } catch (NumberFormatException ex) {
            lblMsg.setText("Please enter a number");
            return;
        }

        if (userAns == gen.ans) {
            score++;
            lblMsg.setText("Correct!");
        } else {
            wrong++;
            panel.wrong = wrong;
            panel.repaint();
            lblMsg.setText("Wrong! Correct answer was " + gen.ans);
        }

        if (wrong == 5) {
            lblQ.setText("Game Over! The man is hanged.");
            lblScore.setText("Final Score: " + score + "/10");
            txtAns.setEnabled(false);
            btnSubmit.setVisible(false);
            btnAgain.setVisible(true);
            return;
        }

        if (qNo == 10) {
            lblQ.setText("You Win! You saved the man.");
            lblScore.setText("Final Score: " + score + "/10");
            txtAns.setEnabled(false);
            btnSubmit.setVisible(false);
            btnAgain.setVisible(true);
            return;
        }

        qNo++;
        nextQuestion();
    }
}
