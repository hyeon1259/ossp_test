package hyeon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements ActionListener {
	
	JButton btnArr[][] = new JButton[4][4];
	int arr[][] = new int[4][4];
	int turn = 0;
	
	JButton winBtn = new JButton();
	JButton reBtn = new JButton();
	JButton returnBtn = new JButton();
	JButton exitBtn = new JButton();
	
	Color backgroundColor = new Color(255,255,255);
	Color btnColor = new Color(255,200,200);
	Color pushedBtnColor1 = new Color(0,255,200);
	Color pushedBtnColor2 = new Color(0,200,255);
	Color optionBtnColor = new Color(255,200,200);
	Font gameBtnFont = new Font("고딕",Font.PLAIN,20);
	Font optionBtnFont = new Font("고딕",Font.BOLD,15);
	
	MyPanel() {
		
		this.setLayout(null);
		this.setBackground(backgroundColor);
//		this.setSize(400,460);
//		frame.setSize(400, 460);
//		Toolkit tk2 = Toolkit.getDefaultToolkit();
//		Dimension size2 = tk2.getScreenSize();
//		frame.setLocation(size.width/2-200, size.height/2-230);
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				btnArr[i][j] = new JButton();
				btnArr[i][j].setBackground(btnColor); //버튼색상
				btnArr[i][j].setFont(gameBtnFont); //폰트
				btnArr[i][j].setText(i*4+j+1+"");
				btnArr[i][j].setSize(100, 100);
				btnArr[i][j].setLocation(43+j*100, 30+i*100);
				btnArr[i][j].addActionListener(this);
				this.add(btnArr[i][j]);
				arr[i][j] = 0;
			}
		}
		

		winBtn.setText("Winner?");
		winBtn.setSize(140, 40);
		winBtn.setLocation(40, 460);		// 왼쪽 상단 0,0 기준으로 지정함.
		winBtn.setBackground(optionBtnColor);	//버튼색상
		winBtn.setFont(optionBtnFont);
		winBtn.addActionListener(this);
		this.add(winBtn);
		
		reBtn.setText("RePlay");
		reBtn.setSize(120, 40);
		reBtn.setLocation(190, 460);
		reBtn.setBackground(optionBtnColor);	//버튼색상
		reBtn.setFont(optionBtnFont);
		reBtn.addActionListener(this);
		this.add(reBtn);	
		
		
		returnBtn.setText("ReTurn");
		returnBtn.setSize(120, 40);
		returnBtn.setLocation(320, 460);
		returnBtn.setBackground(optionBtnColor);	//버튼색상
		returnBtn.setFont(optionBtnFont);
		returnBtn.addActionListener(this);
		this.add(returnBtn);	
		
		exitBtn.setText("EXIT");
		exitBtn.setSize(120, 40);
		exitBtn.setLocation(320, 520);
		exitBtn.setBackground(optionBtnColor);	//버튼색상
		exitBtn.setFont(optionBtnFont);
		exitBtn.addActionListener(this);
		this.add(exitBtn);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(e.getSource() == btnArr[i][j]) {
					if(turn % 2 == 0) {
						btnArr[i][j].setText("O");
						btnArr[i][j].setBackground(pushedBtnColor1);
						arr[i][j] = 1;						
					}
					else {
						btnArr[i][j].setText("X");
						btnArr[i][j].setBackground(pushedBtnColor2);
						arr[i][j] = 2;						
					}
					btnArr[i][j].setEnabled(false);
					turn++;					
				}
			}
		}
		
		if(e.getSource() == reBtn) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					btnArr[i][j].setText(i*4+j+1+"");
					btnArr[i][j].setEnabled(true);
					btnArr[i][j].setBackground(btnColor);
					winBtn.setBackground(btnColor);
					arr[i][j] = 0;
				}
			}
			winBtn.setText("Winner?");
			turn = 0;
		}
		// 게임종료 버튼
		if(e.getSource() == exitBtn) {
			System.exit(0);
		}
		
	    /* returnBon 설명
		if(e.getSource() == returnBtn) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					btnArr[i][j].setText(i*4+j+1+"");
					btnArr[i][j].setEnabled(true);
					btnArr[i][j].setBackground(btnColor);
					winBtn.setBackground(btnColor);
					arr[i][j] = 0;
					
				}
			}
			
			turn = 0;
		}
		*/
		
		if(check() == 1) {
			winBtn.setText("Player1 WIN!");
			winBtn.setBackground(pushedBtnColor1);
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					btnArr[i][j].setEnabled(false);					
				}
			}
		}
		if(check() == 2) {
			winBtn.setText("Player2 WIN!");	
			winBtn.setBackground(pushedBtnColor2);
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					btnArr[i][j].setEnabled(false);					
				}
			}
		}
	/*	
	 // 무승부일때 출력하는 경우 고민좀 해봐야함
	  if(check() == 0) {
			winBtn.setText("No Winner!");	
			winBtn.setBackground(pushedBtnColor2);
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					btnArr[i][j].setEnabled(false);					
				}
			}
		}
		*/
	}
		
	int check() {
		// 수정 완료
		int j=0;
		for(int i=0; i<4; i++) {
			if(arr[i][j] == 1 && arr[i][j+1] == 1 && arr[i][j+2] == 1 && arr[i][j+3] == 1) {
				return 1;
			}
			else if(arr[i][j] == 2 && arr[i][j+1] == 2 && arr[i][j+2] == 2 && arr[i][j+3] == 2) {
				return 2;
			}
		}
		
		//수정완료
		int i=0;
		for(j=0; j<4; j++) {
			if(arr[i][j] == 1 && arr[i+1][j] == 1 && arr[i+2][j] == 1 && arr[i+3][j] == 1) {
				return 1;
			}
			else if(arr[i][j] == 2 && arr[i+1][j] == 2 && arr[i+2][j] == 2 && arr[i+3][j] == 2) {
				return 2;
			}
		}
		
		// 수정완료
		// 대각선 검사(\)
		i=0;
		if(arr[i][i] == 1 && arr[i+1][i+1] == 1 && arr[i+2][i+2] == 1 && arr[i+3][i+3] == 1) {
			return 1;
		}
		else if(arr[i][i] == 2 && arr[i+1][i+1] == 2 && arr[i+2][i+2] == 2 && arr[i+3][i+3] == 2) {
			return 2;
		}
		
		// 수정완료
		// 대각선 검사(/)
		i=0;
		if(arr[i][i+3] == 1 && arr[i+1][i+2] == 1 && arr[i+2][i+1] == 1 && arr[i+3][i] == 1) {
			return 1;
		}
		else if(arr[i][i+3] == 2 && arr[i+1][i+2] == 2 && arr[i+2][i+1] == 2 && arr[i+3][i] == 2) {
			return 2;
		}	
		
		return 0;
	}	
}

public class main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Tic Tac Toe");
		
		frame.setSize(500, 660);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension size = tk.getScreenSize();
		frame.setLocation((size.width-400)/2, (size.height-460)/2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);


		frame.add(new MyPanel());
	
		frame.revalidate();		
	}
}