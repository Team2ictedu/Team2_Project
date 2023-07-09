package project_java;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calendarmain extends JFrame implements ActionListener {
	String clickedDate;
    Container container = getContentPane();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    JButton buttonBefore = new JButton("Before");
    JButton buttonAfter = new JButton("After");

    JLabel label = new JLabel("00년 0월");

    JButton[] buttons = new JButton[49];
    String[] dayNames = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };

    CalendarFunction cF = new CalendarFunction();
    private Planner_Create planner_Create; // OtherClass 객체를 저장하기 위한 변수

    public Calendarmain(Planner_Create planner_Create) {
        this.planner_Create = planner_Create; // OtherClass 객체 저장
        setTitle("만년 달력");
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2-275, ds.height/2-200, 550, 400);
        init();
        start();
    }

    private void init() {
        container.setLayout(new BorderLayout());
        container.add("North", panel1);
        container.add("Center", panel2);

        panel1.setLayout(new FlowLayout());
        panel1.add(buttonBefore);
        panel1.add(label);
        panel1.add(buttonAfter);

        Font font = new Font("SansSerif", Font.BOLD, 20);
        buttonAfter.setFont(font);
        buttonBefore.setFont(font);
        label.setFont(font);

        label.setText(cF.getCalText());

        panel2.setLayout(new GridLayout(7, 7, 5, 5));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            panel2.add(buttons[i]);

            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 24));

            if (i < 7)
                buttons[i].setText(dayNames[i]);

            if (i % 7 == 0)
                buttons[i].setForeground(Color.RED);
            if (i % 7 == 6)
                buttons[i].setForeground(Color.BLUE);

            // ActionListener 등록
            buttons[i].addActionListener(this);
        }
        cF.setButtons(buttons);
        cF.calSet();
    }

    private void start() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttonAfter.addActionListener(this);
        buttonBefore.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int gap = 0;
        if (e.getSource() == buttonAfter) { // 1달 후
            gap = 1;
            cF.allInit(gap);
            label.setText(cF.getCalText()); // 년월 글자 갱신
        } else if (e.getSource() == buttonBefore) { // 1달 전
            gap = -1;
            cF.allInit(gap);
            label.setText(cF.getCalText()); // 년월 글자 갱신
        }  else if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            String date = clickedButton.getText();
            String yearMonth = label.getText();
            String[] yearMonthArr = yearMonth.split("년|월");
            clickedDate = yearMonthArr[0] + "-" + yearMonthArr[1] + "-" + date;
            planner_Create.jtf_date.setText(clickedDate);
            setVisible(false);
        }
    }
}
