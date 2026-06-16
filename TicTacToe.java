import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Applet implements ActionListener {

    Button b[] = new Button[9];
    Button retry;
    String turn = "X";
    Label status;

    public void init() {

        setLayout(new BorderLayout());

        Panel grid = new Panel(new GridLayout(3,3,5,5));
        grid.setBackground(Color.ORANGE);
        grid.setPreferredSize(new Dimension(1850,950)); 

        for(int i=0;i<9;i++) {

            Panel cell = new Panel(new BorderLayout());
            cell.setBackground(Color.ORANGE);

            b[i] = new Button("");
            b[i].setFont(new Font("Arial", Font.BOLD, 36)); 
            b[i].setForeground(Color.BLUE);
            b[i].addActionListener(this);

            cell.add(b[i], BorderLayout.CENTER);
            grid.add(cell);
        }

        Panel center = new Panel();
        center.add(grid);

        status = new Label("Player X Turn", Label.CENTER);
        status.setBackground(new Color(139,69,19));
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Arial", Font.BOLD, 20));

        retry = new Button("Retry");
        retry.addActionListener(this);
        retry.setFont(new Font("Arial", Font.BOLD, 16));

        Panel bottom = new Panel(new BorderLayout());
        bottom.setBackground(new Color(139,69,19));
        bottom.setPreferredSize(new Dimension(400, 80)); 

        bottom.add(status, BorderLayout.CENTER);
        bottom.add(retry, BorderLayout.EAST);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == retry) {
            resetGame();
            return;
        }

        Button clicked = (Button)e.getSource();

        if(clicked.getLabel().equals("")) {
            clicked.setLabel(turn);

            if(checkWinner()) {
                status.setText("Player " + turn + " Wins!");
                disableButtons();
            }
            else if(checkDraw()) {
                status.setText("Match Draw!");
            }
            else {
                turn = turn.equals("X") ? "O" : "X";
                status.setText("Player " + turn + " Turn");
            }
        }
    }

    boolean checkWinner() {
        int win[][] = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for(int i=0;i<8;i++) {
            if(!b[win[i][0]].getLabel().equals("") &&
               b[win[i][0]].getLabel().equals(b[win[i][1]].getLabel()) &&
               b[win[i][1]].getLabel().equals(b[win[i][2]].getLabel())) {
                return true;
            }
        }
        return false;
    }

    boolean checkDraw() {
        for(int i=0;i<9;i++) {
            if(b[i].getLabel().equals(""))
                return false;
        }
        return true;
    }

    void disableButtons() {
        for(int i=0;i<9;i++)
            b[i].setEnabled(false);
    }

    void resetGame() {
        for(int i=0;i<9;i++) {
            b[i].setLabel("");
            b[i].setEnabled(true);
        }
        turn = "X";
        status.setText("Player X Turn");
    }
}