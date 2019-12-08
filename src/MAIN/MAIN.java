package MAIN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;

class addFrame extends JFrame {
	private JButton ok = new JButton("Ȯ��");
	private JTextField tx = new JTextField(20);
	private JTable table;
	private JTable table2;
	private JLabel total_price;

	public addFrame(JTable table, JTable table2, JLabel total_price) {
		this.table = table;
		this.table2 = table2;
		this.total_price = total_price;

		setTitle("��ǰ�߰�");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel lb = new JLabel("���� �Է�");

		c.add(lb);
		c.add(tx);
		c.add(ok);

		setSize(300, 150);
		setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFrame();
				setVisible(false);
			}
		});
	}

	public void setFrame() {
		int tmp = Integer.parseInt(total_price.getText());
		TableModel copymodel = table.getModel();
		int[] indexs = table.getSelectedRows();
		DefaultTableModel copymode2 = (DefaultTableModel) table2.getModel();
		Object[] copyrow = new Object[8];
		for (int i = 0; i < indexs.length; i++) {
			copyrow[0] = copymodel.getValueAt(indexs[i], 0);
			copyrow[1] = copymodel.getValueAt(indexs[i], 1);
			copyrow[2] = copymodel.getValueAt(indexs[i], 2);
			copyrow[3] = copymodel.getValueAt(indexs[i], 3);
			copyrow[4] = copymodel.getValueAt(indexs[i], 4);
			copyrow[5] = tx.getText();
			copymode2.addRow(copyrow);
			tmp = tmp + (Integer.parseInt(tx.getText()) * Integer.parseInt(copyrow[3].toString()));
		}
		total_price.setText(String.valueOf(tmp));
	}
}

class Seeall extends JFrame {
	private JButton ok = new JButton("Ȯ��");
	private String header[] = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "�Է���" };

	public Seeall(List<List<String>> data2) {
		String[][] array = new String[data2.size()][];
		for (int i = 0; i < data2.size(); i++) { 
			List<String> row = data2.get(i);
			array[i] = row.toArray(new String[row.size()]); 
		}
		
		setTitle("��ǰ ��ü ����Ʈ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel lb = new JLabel("��ü ���");
		DefaultTableModel seeall_model = new DefaultTableModel(array, header);
		JTable seeAll_table = new JTable(seeall_model);
		JScrollPane sc = new JScrollPane(seeAll_table);
		
		c.add(lb);
		c.add(sc);	
		c.add(ok);

		setSize(500, 700);
		setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//window �ݱ� ���
				setVisible(false);
			}
		});
	}
}

class addItem extends JFrame {
	private JButton ok = new JButton("Ȯ��");
	private String header[] = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "�Է���" };

	public addItem() {

		setTitle("��ǰ ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel lb1 = new JLabel("��ǰ ��");
		JLabel lb2 = new JLabel("��ǰ ����");
		JLabel lb3 = new JLabel("��ǰ ���");
		JLabel lb4 = new JLabel("��ǰ ����");
		JLabel lb5 = new JLabel("��ǰ ����");
		JLabel lb6 = new JLabel("��ǰ ģȯ�濩��");
		JLabel lb7 = new JLabel("��ǰ �����");

		JTextField jt1 = new JTextField(20);
		JTextField jt2 = new JTextField(20);
		JTextField jt3 = new JTextField(20);
		JTextField jt4 = new JTextField(20);
		JTextField jt5 = new JTextField(20);
		JTextField jt6 = new JTextField(20);
		JTextField jt7 = new JTextField(20);
		
		JButton jb1= new JButton("���");
		
		c.add(lb1);
		c.add(jt1);
		c.add(lb2);
		c.add(jt2);
		c.add(lb3);
		c.add(jt3);
		c.add(lb4);
		c.add(jt4);
		c.add(lb5);
		c.add(jt5);
		c.add(lb6);
		c.add(jt6);
		c.add(lb7);
		c.add(jt7);
		c.add(jb1);

		setSize(250, 500);
		setVisible(true);

		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����Ʈ�� �׸� �߰��ؼ� ��ü ����Ʈ csv ���Ϸ� �����ϴ� ��� �����ϱ�.
				setVisible(false);
			}
		});
	}
}

public class MAIN extends JFrame {
	public String header[] = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "�Է���" };
	public String header2[] = { "ǰ���", "����", "���", "����", "����", "����" };
	public Object combo1 = "";
	public Object combo2 = "";

	MAIN() {
		// �ʱ⼼��---------------------------------------------------
		setTitle("����깰 ���� ���μ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = getContentPane();
		//// ----------------------------------------------------------

		// ùȭ�� ����---------------------------------------------------
		Tools t1 = new Tools();
		List<List<String>> data = t1.run();
		//// ----------------------------------------------------------

		// Jtabel�� �ֱ� ���� List to String array
		// ---------------------------------------------------
		//String[][] array = new String[data.size()][];
		/*
		 * for (int i = 0; i < conten.size(); i++) { List<String> row = conten.get(i);
		 * array[i] = row.toArray(new String[row.size()]); }
		 */
		//// ----------------------------------------------------------

		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();
		JPanel j3 = new JPanel();

		j1.setLayout(new FlowLayout());
		j2.setLayout(new FlowLayout());
		j3.setLayout(new FlowLayout());

		c.add(j1, BorderLayout.NORTH);
		c.add(j2, BorderLayout.CENTER);
		c.add(j3, BorderLayout.SOUTH);

		// panel j1
		// Jcombobox1
		JLabel label1 = new JLabel("��ǰ��� : ");
		String[] label1_list = t1.getItemlist();
		JComboBox<String> label1_combobox = new JComboBox<String>(label1_list); // �����̸������� ����Ʈ
		j1.add(label1);
		j1.add(label1_combobox);

		// Jcombobox2
		JLabel name1 = new JLabel(" ������ : ");
		String[] namelist1 = t1.getItemlist();
		JComboBox<String> comboname1 = new JComboBox<String>(namelist1); // �����̸������� ����Ʈ
		j1.add(name1);
		j1.add(comboname1);
		comboname1.setEnabled(false);

		// panel j2
		// JTable������
		String[][] array = new String[0][];
		DefaultTableModel model = new DefaultTableModel(array, header);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		j2.add(sc);
		
		// ������ ���̺�
		DefaultTableModel model2 = new DefaultTableModel(array, header2);
		JTable table2 = new JTable(model2);
		JScrollPane sc2 = new JScrollPane(table2);
		j2.add(sc2);
		
		// �� �ݾ�
		JLabel total = new JLabel(" �ѱݾ� : ");
		j2.add(total);
		JLabel total_price = new JLabel("0");
		j2.add(total_price);
		JLabel won = new JLabel("��");
		j2.add(won);

		// button
		JButton add = new JButton("�����׸� ��ٱ��� �߰�");
		JButton remove_one = new JButton("�����׸� ��ٱ��� ����");
		JButton remove_all = new JButton("��ü�׸� ��ٱ��� ����");
		JButton seeitem = new JButton("��ǰ ��ü ����Ʈ");
		JButton additem = new JButton("��ǰ ���");

		j3.add(add);
		j3.add(remove_one);
		j3.add(remove_all);
		j3.add(seeitem);
		j3.add(additem);

		remove_one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmp = Integer.parseInt(total_price.getText())-(Integer.parseInt(model2.getValueAt(table2.getSelectedRow(), 3).toString())*Integer.parseInt(model2.getValueAt(table2.getSelectedRow(), 5).toString()));
				total_price.setText(String.valueOf(tmp));
				model2.removeRow(table2.getSelectedRow());
			}
		});

		remove_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.setNumRows(0);
				total_price.setText("0");
			}
		});

		seeitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Seeall(data);
			}

		}); 
		
		additem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addItem();
			}

		}); 

		// combobox listener
		label1_combobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				combo1 = jcb.getSelectedItem();

				comboname1.setEnabled(true);
				comboname1.removeAllItems();

				// ���� table ��������---------------------------------------
				List<List<String>> tmp = new ArrayList<List<String>>();
				for (int i = 0; i < data.size(); i++) {
					List<String> row = data.get(i);
					if (row.get(0).equals(combo1)) {
						tmp.add(row);
					}
				}
				//// ---------------------------------------------------

				// TABLE�� �´� ����ȯ---------------------------------------
				String[][] array = new String[tmp.size()][];
				for (int i = 0; i < tmp.size(); i++) {
					List<String> row = tmp.get(i);
					array[i] = row.toArray(new String[row.size()]);
				}
				//// ---------------------------------------------------

				DefaultTableModel model = new DefaultTableModel(array, header);
				table.setModel(model);
				List<String> numdata = new ArrayList<String>();
				for (int count = 0; count < model.getRowCount(); count++) {
					String tmp_numdata = model.getValueAt(count, 4).toString();
					if (numdata.contains(tmp_numdata)) {
						continue;
					} else {
						numdata.add(model.getValueAt(count, 4).toString());
						comboname1.addItem(model.getValueAt(count, 4).toString());
					}
				}
			}
		});

		// combobox listener
		comboname1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				combo2 = jcb.getSelectedItem();

				sc.setEnabled(true);
				sc2.setEnabled(true);

				List<List<String>> tmp = new ArrayList<List<String>>();
				for (int i = 0; i < data.size(); i++) {
					List<String> row = data.get(i);
					if (row.get(0).equals(combo1) && row.get(4).equals(combo2)) {
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
				addFrame addT = new addFrame(table, table2, total_price);
			}
		});
		// ui
		setSize(950, 580);
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
				line = line.replace("\"", "");
				String[] field = line.split(cvsSplitBy);
				itemList.add(field[0]);
				tmpList = Arrays.asList(field);
				ret.add(tmpList);
			}
			System.out.print("�ȳ� : CSV������ ������ �����Ͽ����ϴ�.\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public String[] getItemlist() {
		HashSet<String> tmp_hasharray = new HashSet<String>(itemList);
		ArrayList<String> tmp_itemList = new ArrayList<String>(tmp_hasharray);
		System.out.print("�ȳ� : itemList �ε��� �Ϸ��Ͽ����ϴ�.\n");
		String[] stockArr = new String[tmp_itemList.size()];
		stockArr = tmp_itemList.toArray(stockArr);
		return stockArr;
	}

}
