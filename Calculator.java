
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {
	LinkedList<String> Terms = new LinkedList<String>();
	JFrame frame;
	JTextField Calculations;
	JTextField textfield;
	JPanel panel;

	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];

	JButton buttonAdd;
	JButton buttonSub;
	JButton buttonDiv;
	JButton buttonMult;

	JButton buttonDec;
	JButton buttonEqu;
	JButton buttonDel;
	JButton buttonClr;
	JButton buttonPercent;

	public Calculator() {

		frame = new JFrame("Calculator");
		// frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 525);
		frame.setLayout(null);

		Calculations = new JTextField();
		Calculations.setBounds(50, 22, 285, 20);
		Calculations.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Color color = Color.LIGHT_GRAY;
		Calculations.setBackground(color);

		textfield = new JTextField();
		textfield.setBounds(50, 40, 285, 50);

		buttonAdd = new JButton("+");
		buttonSub = new JButton("-");
		buttonDiv = new JButton("/");
		buttonMult = new JButton("x");
		buttonDec = new JButton(".");
		buttonEqu = new JButton("=");
		buttonDel = new JButton("Del");
		buttonClr = new JButton("Clr");
		buttonPercent = new JButton("%");

		functionButtons[0] = buttonPercent;
		functionButtons[1] = buttonSub;
		functionButtons[2] = buttonDiv;
		functionButtons[3] = buttonMult;
		functionButtons[4] = buttonAdd;
		functionButtons[5] = buttonDec;
		functionButtons[6] = buttonDel;
		functionButtons[7] = buttonClr;
		functionButtons[8] = buttonEqu;

		for (int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFocusable(false);
		}

		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(Integer.toString(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFocusable(false);
		}

		numberButtons[0].setBounds(50, 410, 137, 50);
		buttonDec.setBounds(197, 410, 63, 50);
		buttonEqu.setBounds(270, 410, 63, 50);

		frame.add(numberButtons[0]);
		frame.add(buttonDec);
		frame.add(buttonEqu);

		panel = new JPanel();
		panel.setBounds(50, 100, 285, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		// panel.setBackground(Color.GRAY);

		panel.add(buttonDel);
		panel.add(buttonClr);
		panel.add(buttonPercent);
		panel.add(buttonDiv);

		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(buttonMult);

		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(buttonSub);

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(buttonAdd);

		frame.add(panel);
		frame.add(textfield);
		frame.add(Calculations);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(Integer.toString(i)));
			}
		}

		for (int i = 0; i < 4; i++) {
			if (e.getSource() == functionButtons[i]) {
				Calculations.setText(
						Calculations.getText().concat(textfield.getText().concat(functionButtons[i].getText())));
				// Fix this
				if (textfield.getText() == "") {
					Terms.add(functionButtons[i].getText());
				} else {

					Terms.add(textfield.getText());
					Terms.add(functionButtons[i].getText());
					// Terms.add("time");
					textfield.setText("");
				}

			}
		}
		if (e.getSource() == buttonClr) {
			if (!(textfield.getText().length() == 0)) {
				textfield.setText("");
			} else if (!(Calculations.getText().length() == 0)) {
				Calculations.setText("");
			}

		}

		if (e.getSource() == buttonDel) {
			if (!(textfield.getText().length() == 0)) {
				String str = textfield.getText();
				textfield.setText(str.substring(0, str.length() - 1));
			} else if (!(Calculations.getText().length() == 0)) {
				String str = Calculations.getText();
				Calculations.setText(str.substring(0, str.length() - 1));
			}
		}
		if (e.getSource() == buttonDec) {
			textfield.setText(textfield.getText().concat(buttonDec.getText()));

		}

		if (e.getSource() == buttonAdd) {
			Terms.add(textfield.getText());
			Calculations.setText(Calculations.getText().concat(textfield.getText().concat("+")));
			textfield.setText("");
		}

		if (e.getSource() == buttonEqu) {
//			String temp = textfield.getText();
//			char[] tokens = temp.toCharArray();
//
//			Stack<Integer> values = new Stack<Integer>();
//			Stack<Character> ops = new Stack<Character>();
			Terms.add(textfield.getText());

			System.out.print(Terms);
//			while (itr.hasNext()) {
//				System.out.print(itr.next());
//				 if (itr.next() == "-") {
//				 System.out.println("sub:" + itr.next());
//				 }
			while (!(Terms.indexOf("-") == -1)) {
				int index = (Terms.indexOf("-") + 1);
				String temp = Terms.get(index);
				temp = "-" + temp;
				Terms.set(index, temp);
				Terms.remove(Terms.indexOf("-"));
			}

			System.out.println(Terms);
			// System.out.println(Terms.indexOf("x"));
			// System.out.println(Terms.indexOf("/"));
			while ((Terms.indexOf("x") != -1) && (Terms.indexOf("/") != -1)) {
				int indexMult = (Terms.indexOf("x"));
				int indexDiv = (Terms.indexOf("/"));

				// System.out.println(indexMult);
				if (indexMult > indexDiv) {
					Float num1 = Float.parseFloat(Terms.get(indexDiv + 1));
					Float num2 = Float.parseFloat(Terms.get(indexDiv - 1));

					Terms.set(indexDiv, Float.toString(num2 / num1));
					Terms.remove(indexDiv + 1);
					Terms.remove(indexDiv - 1);
				} else if (indexMult < indexDiv) {
					int num1 = Integer.parseInt(Terms.get(indexMult + 1));
					int num2 = Integer.parseInt(Terms.get(indexMult - 1));

					Terms.set(indexMult, Integer.toString(num2 * num1));
					Terms.remove(indexMult + 1);
					Terms.remove(indexMult - 1);
				}
				System.out.println("step: " + Terms);
				System.out.println("mult index " + indexMult);
				System.out.println("Div index " + indexDiv);
			}
			System.out.println("mult index " + Terms.indexOf("x"));
			System.out.println("Div index " + Terms.indexOf("/"));
			System.out.println("MID: " + Terms);

			while (Terms.indexOf("x") != -1) {
				int indexMult = (Terms.indexOf("x"));
				Float num1 = Float.parseFloat(Terms.get(indexMult + 1));
				Float num2 = Float.parseFloat(Terms.get(indexMult - 1));

				Terms.set(indexMult, Float.toString(num2 * num1));
				Terms.remove(indexMult + 1);
				Terms.remove(indexMult - 1);
			}
			while (Terms.indexOf("/") != -1) {
				int indexDiv = (Terms.indexOf("/"));
				Float num1 = Float.parseFloat(Terms.get(indexDiv + 1));
				Float num2 = Float.parseFloat(Terms.get(indexDiv - 1));

				Terms.set(indexDiv, Float.toString(num2 / num1));
				Terms.remove(indexDiv + 1);
				Terms.remove(indexDiv - 1);
			}
			System.out.println("FINAL: " + Terms);

			Iterator<String> itr = Terms.iterator();
			Float temp = (float) 0;
			while (itr.hasNext()) {
				temp = Float.parseFloat(itr.next()) + temp;

			}
			System.out.print(temp);
			Calculations.setText("");
			Terms.clear();

			textfield.setText(String.valueOf(temp));

		}
		// System.out.print(Terms);

	}
	// public actionPerformed(ActionEvent e) {
}
