import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class CreationFrame extends JFrame implements ActionListener {
	private JButton add, fine;
	private JTextField dom, risp[];
	private JCheckBox vera[];
	private String testo = "";
	private int cont=0;

	public CreationFrame() {
		super("Creazione Quesiti");

		Container c = this.getContentPane();
		JPanel p = new JPanel(new GridLayout(5, 1));
		JPanel pDom = new JPanel();
		JPanel pRisp[] = new JPanel[4];
		JPanel pPuls = new JPanel();
		risp = new JTextField[4];
		vera = new JCheckBox[4];

		for (int i = 0; i < 4; i++) {
			pRisp[i] = new JPanel();
			risp[i] = new JTextField("Risposta " + (i + 1), 13);
			vera[i] = new JCheckBox();
			pRisp[i].add(risp[i]);
			pRisp[i].add(vera[i]);
		}
		add = new JButton("Aggiungi domanda");
		fine = new JButton("Fine");
		dom = new JTextField("Domanda?", 16);

		pDom.add(dom);
		pPuls.add(add);
		pPuls.add(fine);

		p.add(pDom);
		p.add(pRisp[0]);
		p.add(pRisp[1]);
		p.add(pRisp[2]);
		p.add(pRisp[3]);

		c.add(p);
		c.add(pPuls, "South");

		add.addActionListener(this);
		fine.addActionListener(this);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 350);
		setLocation(400, 200);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==add){
			testo+=dom.getText()+"\n";
			for(int i=0;i<4;i++){
				if(vera[i].isSelected())
					testo+="-";
				testo+=risp[i].getText()+"\n";
				
				vera[i].setSelected(false);
				risp[i].setText("Risposta "+(i+1));
			}
			dom.setText("Domanda?");
			cont++;
		}else if(o==fine){
			File newf = null;
			JFileChooser jf = new JFileChooser("C:\\Users\\Andrea Felline\\Desktop\\");
			int i = jf.showSaveDialog(null);
			if (i == JFileChooser.APPROVE_OPTION) {
				testo=cont+"\n"+testo;
				newf = jf.getSelectedFile();
				try {
					newf.createNewFile();
					FileWriter fout = new FileWriter(newf.getAbsolutePath());
					PrintWriter out = new PrintWriter(fout);
					StringTokenizer st = new StringTokenizer(testo,"\n");
					while(st.hasMoreTokens())
						out.println(st.nextToken());
					out.close();
					fout.close();
				} catch (IOException err) {
					System.out.println(err);
				}
				
				//settaggio
				testo="";
				for(int j=0;j<4;j++){
					vera[j].setSelected(false);
					risp[j].setText("Risposta "+(j+1));
				}
				dom.setText("Domanda?");
			}
		}
	}

	public static void main(String[] args) {
		CreationFrame cf = new CreationFrame();
	}

}
