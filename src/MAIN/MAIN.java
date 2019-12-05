package MAIN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


class RightFrame extends JFrame {
	// ��ٱ��Ͽ� �߰��ϴ� ��ư (>>) Frame����
	public RightFrame() {
		setTitle("add");
		Font f1 = new Font("����", Font.PLAIN, 10);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel lb = new JLabel("���� �Է�");
		JTextField tx = new JTextField(20);
		JButton ok = new JButton("Ȯ��");

		lb.setFont(f1);
		ok.setFont(f1);

		c.add(lb);
		c.add(tx);
		c.add(ok);

		setSize(700, 150);
		setVisible(true);
	}
}

class LeftFrame extends JFrame {
	public LeftFrame() {
		setTitle("remove");
		Font f1 = new Font("����", Font.PLAIN, 10);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel lb = new JLabel("���� �Է�");
		JTextField tx = new JTextField(20);
		JButton ok = new JButton("Ȯ��");

		lb.setFont(f1);
		ok.setFont(f1);

		c.add(lb);
		c.add(tx);
		c.add(ok);

		setSize(300, 150);
		setVisible(true);

	}
}

public class MAIN extends JFrame {
	public String header[] = { "ǰ���","����","���","����","����","ģȯ�汸��","�Է���" };
	public String contents[][]= {{"a","b"}};

	MAIN() {
		//ùȭ�� ����---------------------------------------------------
		Tools t1=new Tools();
		List<List<String>> conten = t1.run();
		////----------------------------------------------------------
		
		//Jtabel�� �ֱ� ���� List to String array ---------------------------------------------------
		String[][] array = new String[conten.size()][];
		for (int i = 0; i < conten.size(); i++) {
		    List<String> row = conten.get(i);
		    array[i] = row.toArray(new String[row.size()]);
		}
		////----------------------------------------------------------
		
		
		
		setTitle("���깰 ��ٱ���");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ���� �̸� ������

		Container c = getContentPane();

		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();

		j1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		j2.setLayout(null);

		c.add(j1, BorderLayout.NORTH);
		c.add(j2);

		// panel j1
		// Jcombobox
		JLabel name = new JLabel("category");
		String[] namelist=t1.getItem();
		JComboBox<String> comboname = new JComboBox<String>(namelist); // �����̸������� ����Ʈ
		j1.add(name);
		j1.add(comboname);

		// panel j2
		// JTable������
		DefaultTableModel model = new DefaultTableModel(array, header);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(20, 20, 400, 200);
		j2.add(sc);

		// ������ ���̺�
		String header2[] = { "������", "����" };
		String contents2[][] = { { "ex1", "����" }, { "ex2", "����" }, { "ex3", "����" } };
		DefaultTableModel model2 = new DefaultTableModel(contents2, header2);
		JTable table2 = new JTable(model2);
		JScrollPane sc2 = new JScrollPane(table2);
		sc2.setBounds(500, 20, 400, 200);
		j2.add(sc2);

		// button
		Font f1 = new Font("����", Font.PLAIN, 10);
		Font f2 = new Font("�������", Font.PLAIN, 10);
		JButton add = new JButton("Add all");
		JButton remove = new JButton("Remove all");
		JButton additems = new JButton("Add/remove items");
		JButton graph = new JButton("graph");
		JButton right = new JButton(">>");
		JButton left = new JButton("<<");

		add.setFont(f1);
		add.setBounds(20, 340, 100, 30);

		remove.setFont(f1);
		remove.setBounds(370, 340, 100, 30);

		additems.setFont(f1);
		additems.setBounds(190, 240, 130, 30);

		right.setFont(f2);
		right.setBounds(220, 60, 50, 30);

		left.setFont(f2);
		left.setBounds(220, 140, 50, 30);

		j2.add(add);
		j2.add(remove);
		j2.add(additems);
		j2.add(graph);
		j2.add(right);
		j2.add(left);

		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RightFrame();

			}
		});

		left.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new LeftFrame();

			}
		});
		additems.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});

		remove.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				model2.setNumRows(0);
			}
		});

		additems.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("insert/remove data");
				jf.setLayout(new FlowLayout());

				JLabel lb = new JLabel("������ �Է�");
				JTextField tx = new JTextField(20);
				JLabel lb2 = new JLabel("���� �Է�");
				JTextField tx2 = new JTextField(20);
				JButton addbtn = new JButton("�߰�");
				JButton removebtn = new JButton("����");

				lb.setFont(f1);
				lb2.setFont(f1);
				addbtn.setFont(f1);
				removebtn.setFont(f1);

				jf.add(lb);
				jf.add(tx);
				jf.add(lb2);
				jf.add(tx2);
				jf.add(addbtn);
				jf.add(removebtn);

				// insert/remove items
				jf.setSize(1000, 150);
				jf.setVisible(true);

				addbtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String inputstr[] = new String[2];

						inputstr[0] = tx.getText();
						inputstr[1] = tx2.getText();
						model.addRow(inputstr);

						tx.setText("");
						tx2.setText("");

					}
				}); // addbtn listener
				removebtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (table.getSelectedRow() == -1) {
							return;
						} else {
							model.removeRow(table.getSelectedRow());
						}

					}
				}); // remove button

			}

		}); // insert/remove listener

		// combobox listener
		comboname.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				Object name = jcb.getSelectedItem();
				List<List<String>> tmp = new ArrayList<List<String>>();
				for(int i=0; i<conten.size();i++) {
				    List<String> row = conten.get(i);
				    if(row.get(0).equals(name)) {
				    	tmp.add(row);
				    }
				}
				String[][] array = new String[tmp.size()][];
				for (int i = 0; i < tmp.size(); i++) {
				    List<String> row = tmp.get(i);
					array[i] = row.toArray(new String[row.size()]);					
				}		
				DefaultTableModel model = new DefaultTableModel(array, header);
				table.setModel(model);
			}
		});
		// Add all button listener
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < contents.length; i++) {
					model2.addRow(contents[i]);
				}

			}
		});

		// ui
		setSize(8000, 650);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MAIN();
	}
}

class Tools {
	List<List<String>> ret = new ArrayList<List<String>>();
    List<String> itemList = new ArrayList<String>();
	public List<List<String>> run() {
		BufferedReader br = null;
	    String line;
	    String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("����깰_���_2018.csv"), "UTF-8"));
            while ((line = br.readLine()) != null) {
	            List<String> tmpList = new ArrayList<String>();
	            line=line.replace("\"","");
                String[] field = line.split(cvsSplitBy);
                itemList.add(field[0]);
                tmpList = Arrays.asList(field);
	            ret.add(tmpList);
            }
            System.out.print("�ȳ� : CSV������ ������ �����Ͽ����ϴ�.\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return ret;
    }

	public String[] getItem() {
	    HashSet<String> arr2 = new HashSet<String>(itemList);
	    ArrayList<String> arr3 = new ArrayList<String>(arr2);
	    System.out.print("�ȳ� : item �ε��� �Ϸ��Ͽ����ϴ�.\n");
	    String[] stockArr = new String[arr3.size()];
	    stockArr = arr3.toArray(stockArr);
	    return stockArr;
	}
}
