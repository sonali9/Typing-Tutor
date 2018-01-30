package august5;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {
	private JLabel timer;
	private JLabel score;
	private JLabel word;
	private JTextField text;
	private JButton btnstart;
	private JButton btnstop;
	private Timer time = null;
	private boolean running = false;
	private int timeremaining = 50;
	private int Score = 0;
	private String[] words = null;

	public TypingTutor(String[] args) {
		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);
		words = args;

		Font font = new Font("Comic Sans MS", 1, 100);
		timer = new JLabel("TIMER");
		timer.setFont(font);
		super.add(timer);

		score = new JLabel("SCORE");
		score.setFont(font);
		super.add(score);

		word = new JLabel("");
		word.setFont(font);
		super.add(word);

		text = new JTextField("");
		text.setFont(font);
		super.add(text);

		btnstart = new JButton("START");
		btnstart.setFont(font);
		btnstart.addActionListener(this);
		super.add(btnstart);
		btnstart.setBackground(Color.GREEN);

		btnstop = new JButton("STOP");
		btnstop.setFont(font);
		btnstop.addActionListener(this);
		super.add(btnstop);
		btnstop.setBackground(Color.RED);

		super.setTitle("Typing Tutor");
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setVisible(true);
		setup();
	}

	public void setup() {
		time = new Timer(2000, this);
		running = false;
		timeremaining = 50;
		Score = 0;
		timer.setText("TIMER: " + timeremaining);
		score.setText("SCORE: " + Score);
		word.setText("");
		text.setText("");
		btnstart.setText("START");
		btnstop.setText("STOP");
		text.setEnabled(false);
		btnstop.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnstart) {
			handlestart();
		} else if (e.getSource() == btnstop) {
			handlestop();
		} else {
			handletimer();
		}
	}

	private void handletimer() {
		timeremaining--;

		String actual, expected;
		actual = text.getText();
		expected = word.getText();
		if (expected.length() > 0 && actual.equals(expected)) {
			Score++;
		}

		score.setText("SCORE: " + Score);
		if (timeremaining == -1) {
			handlestop();
		} else {
			timer.setText("TIMER: " + timeremaining);
			text.setText("");
			int ridx = (int) (Math.random() * words.length);
			word.setText(words[ridx]);
		}
	}

	private void handlestop() {
		// TODO Auto-generated method stub
		time.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Score: " + Score + ".Replay?");
		if (choice == JOptionPane.YES_OPTION) {
			setup();
		} else if (choice == JOptionPane.NO_OPTION) {
			super.dispose();
		} else {
			if (timeremaining == -1) {
				setup();
			} else {
				time.start();
			}
		}

	}

	private void handlestart() {
		// TODO Auto-generated method stub
		if (running == true) {
			time.stop();
			running = false;
			btnstop.setEnabled(false);
			text.setEnabled(false);
			btnstart.setText("Resume");
			btnstart.setBackground(Color.CYAN);
		} else {
			time.start();
			running = true;
			btnstop.setEnabled(true);
			text.setEnabled(true);
			btnstart.setText("Pause");
			btnstart.setBackground(Color.BLUE);

		}
	}

}
