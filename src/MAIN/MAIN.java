package MAIN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;

class checkItemcount extends JFrame {
	private JButton ok = new JButton("Ȯ��");
	private JTextField tx = new JTextField(20);
	private JTable table;
	private JTable table2;
	private JLabel total_price;

	public checkItemcount(JTable table, JTable table2, JLabel total_price) {
		this.table = table;
		this.table2 = table2;
		this.total_price = total_price;

		setTitle("��ǰ�߰�");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());

		JLabel lb = new JLabel("���� �Է�");

		container.add(lb);
		container.add(tx);
		container.add(ok);

		setSize(300, 150);
		setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCart();
				setVisible(false);
			}
		});
	}

	public void addCart() {
		int tmp = Integer.parseInt(total_price.getText());
		TableModel copymodel = table.getModel();
		int indexs = table.getSelectedRow();
		DefaultTableModel copymode2 = (DefaultTableModel) table2.getModel();
		Object[] copyrow = new Object[8];
		copyrow[0] = copymodel.getValueAt(indexs, 0);
		copyrow[1] = copymodel.getValueAt(indexs, 1);
		copyrow[2] = copymodel.getValueAt(indexs, 2);
		copyrow[3] = copymodel.getValueAt(indexs, 3);
		copyrow[4] = copymodel.getValueAt(indexs, 4);
		copyrow[5] = tx.getText();
		copymode2.addRow(copyrow);
		tmp = tmp + (Integer.parseInt(tx.getText()) * Integer.parseInt(copyrow[3].toString()));
		total_price.setText(String.valueOf(tmp));			
	}
}

class checkAlldata extends JFrame {
	String header[] = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "��������"};

	public checkAlldata(List<List<String>> data) {
		
		String[][] array = new String[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			List<String> row = data.get(i);
			array[i] = row.toArray(new String[row.size()]);
		}

		setTitle("��ǰ ��ü ����Ʈ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());

		JLabel lb = new JLabel("��ü ���");
		DefaultTableModel model = new DefaultTableModel(array, header);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);

		JButton ok = new JButton("Ȯ��");
		
		container.add(lb);
		container.add(sc);
		container.add(ok);

		setSize(500, 600);
		setVisible(true);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// window �ݱ� ���
				setVisible(false);
			}
		});
	}
}

class changeData extends JFrame {
	private JComboBox<String> combobox;
	private JTextField jt1;
	private List<List<String>> data;
	private List<String> tmp_data;
	
	public changeData(List<List<String>> data,List<String> tmp_data) {
		this.data=data;
		this.tmp_data=tmp_data;
		
		String[] header = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "��������" };
		
		setTitle("�׸� ����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		JPanel tp = new JPanel();
		tp.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		JPanel ap = new JPanel();

		JLabel title1 = new JLabel("�����׸��Դϴ�.");
		
		JLabel lb3 = new JLabel(header[2]+" : "+tmp_data.get(2).toString());
		JLabel lb4 = new JLabel(header[3]+" : "+tmp_data.get(3).toString());
		JLabel lb5 = new JLabel(header[4]+" : "+tmp_data.get(4).toString());
		JLabel lb6 = new JLabel(header[5]+" : "+tmp_data.get(5).toString());
		JLabel lb7 = new JLabel(header[6]+" : "+tmp_data.get(6).toString());
		
		JLabel title2 = new JLabel("� �׸��� �����Ͻðڽ��ϱ�?");
		
		combobox = new JComboBox<String>(header); // �����̸������� ����Ʈ
		jt1 = new JTextField(20);
		JButton ok = new JButton("����");
		
		tp.add(title1);
		
		jp.add(new JLabel(header[0]+" : "+tmp_data.get(0).toString()));
		jp.add(new JLabel(header[1]+" : "+tmp_data.get(1).toString()));
		jp.add(lb3);
		jp.add(lb4);
		jp.add(lb5);
		jp.add(lb6);
		jp.add(lb7);
		
		ap.add(combobox);
		ap.add(jt1);
		ap.add(ok);
		
		container.add(tp,BorderLayout.NORTH);
		container.add(jp,BorderLayout.CENTER);
		container.add(title2,BorderLayout.SOUTH);
		container.add(ap,BorderLayout.PAGE_END);

		setSize(800, 130);
		setVisible(true);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change();
				setVisible(false);
			}
		});
	}
	public void change() {
		if(jt1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "����� ���� �����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);	
		}
		else {
			List<String> tmp = new ArrayList();
			int index=combobox.getSelectedIndex();
			data.remove(tmp_data);
			
			for(int i=0;i<tmp_data.size();i++) {
				if(i==index) {tmp.add(jt1.getText().toString());continue;}
				tmp.add(tmp_data.get(i));
			}
			data.add(tmp);
			
			Tools tool = new Tools();
			tool.saveData(data);	
		}
	}
}

class addItem extends JFrame {
	private String header[] = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "��������" };
	private List<List<String>> data;
	private List<String> tmp_list = new ArrayList();
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JTextField jt3 = new JTextField(20);
	private JTextField jt4 = new JTextField(20);
	private JTextField jt5 = new JTextField(20);
	private JTextField jt6 = new JTextField(20);
	private JTextField jt7 = new JTextField(20);

	public addItem(List<List<String>> data) {
		this.data = data;
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
		
		JButton jb1 = new JButton("���");

		c.add(lb1);c.add(jt1);c.add(lb2);c.add(jt2);c.add(lb3);c.add(jt3);c.add(lb4);c.add(jt4);c.add(lb5);c.add(jt5);c.add(lb6);c.add(jt6);c.add(lb7);c.add(jt7);c.add(jb1);
		getTime();

		setSize(250, 500);
		setVisible(true);

		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����Ʈ�� �׸� �߰��ؼ� ��ü ����Ʈ csv ���Ϸ� �����ϴ� ��� �����ϱ�.
				addData();
				setVisible(false);
			}
		});
	}

	public void getTime() {
		SimpleDateFormat time_format = new SimpleDateFormat("yyyyMMdd");
		Date time = new Date();
		String result_time = time_format.format(time);
		jt7.setText(result_time);
	}

	public void addData() {
		if(jt1.getText().equals("") || jt2.getText().equals("") || jt3.getText().equals("") || jt4.getText().equals("") || jt5.getText().equals("") || jt6.getText().equals("") || jt7.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "�Էµ� ���� Ȯ�����ּ���.","���� �˸�",JOptionPane.ERROR_MESSAGE);	
			new addItem(data);
		}
		else {
			tmp_list.add(jt1.getText());
			tmp_list.add(jt2.getText());
			tmp_list.add(jt3.getText());
			tmp_list.add(jt4.getText());
			tmp_list.add(jt5.getText());
			tmp_list.add(jt6.getText());
			tmp_list.add(jt7.getText());
			data.add(tmp_list);		
			try {
				String str_tmp = "\"" + tmp_list.get(0).toString() + "\"" + ";" + "\"" + tmp_list.get(1).toString() + "\""
						+ ";" + "\"" + tmp_list.get(2).toString() + "\"" + ";" + "\"" + tmp_list.get(3).toString() + "\""
						+ ";" + "\"" + tmp_list.get(4).toString() + "\"" + ";" + "\"" + tmp_list.get(5).toString() + "\""
						+ ";" + "\"" + tmp_list.get(6).toString() + "\"";
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("����깰_���_2018.txt", true), "UTF-8"));
				bw.write(str_tmp);
				bw.newLine();
				bw.flush();
				bw.close();
				JOptionPane.showMessageDialog(null, "�� ��ǰ�� �߰��Ǿ� ���ΰ�ħ�մϴ�! ��ø� ��ٷ��ֽʽÿ�.","�Ϸ� �˸�",JOptionPane.INFORMATION_MESSAGE);
				MAIN.mframe=new MAIN();
			} catch (IOException e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "�Էµ� �� Ȥ�� ������ ������ Ȯ�����ּ���.","���� �˸�",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

public class MAIN extends JFrame {
	public static MAIN mframe;
	public String header[] = { "ǰ���", "����", "���", "����", "����", "ģȯ�汸��", "��������" };
	public String header2[] = { "ǰ���", "����", "���", "����", "����", "����" };
	public Object combo1 = "";
	public Object combo2 = "";
	public JLabel label1;
	public JLabel label2;
	public List<List<String>> data;
	public static JComboBox<String> label1_combobox;
	public JComboBox<String> label2_combobox;

	MAIN() {
		// �ʱ⼼��---------------------------------------------------
		setTitle("����깰 ���� ���μ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container container = getContentPane();

		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();
		JPanel j3 = new JPanel();
		
		JPanel j2_1 = new JPanel();
		JPanel j2_2 = new JPanel();

		j1.setLayout(new FlowLayout());
		j2.setLayout(new FlowLayout());
		j3.setLayout(new FlowLayout());
		j2_1.setLayout(new BorderLayout());
		j2_2.setLayout(new BorderLayout());

		container.add(j1, BorderLayout.PAGE_START);
		container.add(j2, BorderLayout.CENTER);
		container.add(j3, BorderLayout.PAGE_END);

		// panel j1
		Tools tool = new Tools();
		data = tool.run();

		// Jcombobox1
		label1 = new JLabel("��ǰ��� : ");
		String[] label1_list = tool.getItemlist();
		label1_combobox = new JComboBox<String>(label1_list); // �����̸������� ����Ʈ
		j1.add(label1);
		j1.add(label1_combobox);

		// Jcombobox2
		label2 = new JLabel(" ������ : ");
		String[] label2_list = tool.getItemlist();
		label2_combobox = new JComboBox<String>(label2_list);
		j1.add(label2);
		j1.add(label2_combobox);
		label2_combobox.setEnabled(false);
		
		JLabel label3 = new JLabel("��ǰ����Ʈ (��ǰ ����� �������ּ���)");
		JLabel label4 = new JLabel("��ٱ���");
		JLabel total = new JLabel(" (�ѱݾ� : ");
		JLabel total_price = new JLabel("0");
		JLabel won = new JLabel("�� )");	
		
		JPanel tmp_jp1 = new JPanel();
		tmp_jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel tmp_jp2 = new JPanel();
		tmp_jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		tmp_jp1.add(label3);
		tmp_jp2.add(label4);
		tmp_jp2.add(total);
		tmp_jp2.add(total_price);
		tmp_jp2.add(won);
		
		//�������̺�
		String[][] array = new String[0][];
		DefaultTableModel table_model1 = new DefaultTableModel(array, header);
		JTable table1 = new JTable(table_model1);
		JScrollPane sc = new JScrollPane(table1);


		//���������̺�
		DefaultTableModel table_model2 = new DefaultTableModel(array, header2);
		JTable table2 = new JTable(table_model2);
		JScrollPane sc2 = new JScrollPane(table2);		
		j2_1.add(tmp_jp1,BorderLayout.NORTH);
		j2_1.add(sc,BorderLayout.CENTER);
		j2_2.add(tmp_jp2,BorderLayout.NORTH);
		j2_2.add(sc2,BorderLayout.CENTER);		
		j2.add(j2_1);
		j2.add(j2_2);
		
		// button
		JButton cart_add = new JButton("�����׸� ��ٱ��� �߰�");
		JButton cart_remove_one = new JButton("�����׸� ��ٱ��� ����");
		JButton cart_remove_all = new JButton("��ü�׸� ��ٱ��� ����");
		JButton all_item = new JButton("��ǰ ��ü ����Ʈ");
		JButton all_add = new JButton("��ǰ ���");
		JButton all_remove = new JButton("��ǰ ����");
		JButton all_change = new JButton("��ǰ ����");
		JButton all_buy = new JButton("�����ϱ�");


		j3.add(cart_add);
		j3.add(cart_remove_one);
		j3.add(cart_remove_all);
		j3.add(all_item);
		j3.add(all_add);
		j3.add(all_remove);
		j3.add(all_change);
		j3.add(all_buy);

		all_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "��ǰ����Ʈ�� ���õǾ��ִ��� Ȯ�����ּ���.","���� �˸�",JOptionPane.ERROR_MESSAGE);					
				}
				else {
					JOptionPane.showMessageDialog(null, "��ǰ�� ���ŵǾ� ���ΰ�ħ�մϴ�! ��ø� ��ٷ��ֽʽÿ�.","�Ϸ� �˸�",JOptionPane.INFORMATION_MESSAGE);
					mframe.dispose();	
					tool.removeItem(data, table1);
				}
			}
		});
		
		cart_remove_one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table2.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "��ٱ��ϰ� üũ�Ǿ����� Ȯ�����ּ���!","���� �˸�",JOptionPane.ERROR_MESSAGE);
				}
				else {
					int tmp = Integer.parseInt(total_price.getText())
							- (Integer.parseInt(table_model2.getValueAt(table2.getSelectedRow(), 3).toString())
									* Integer.parseInt(table_model2.getValueAt(table2.getSelectedRow(), 5).toString()));
					total_price.setText(String.valueOf(tmp));
					table_model2.removeRow(table2.getSelectedRow());					
				}

			}
		});

		cart_remove_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_model2.setNumRows(0);
				total_price.setText("0");
			}
		});

		all_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new checkAlldata(data);
			}

		});

		all_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mframe.dispose();
				new addItem(data);
			}
		});
		
		all_buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			List<List<String>> all_tmp = new ArrayList();
			List<String> one_tmp = new ArrayList();
				for(int i=0;i<table2.getRowCount();i++) {
					one_tmp = new ArrayList();
					for(int j=0;j<table2.getColumnCount();j++) {
						one_tmp.add(table2.getValueAt(i, j).toString());	
					}
					all_tmp.add(one_tmp);
				}

				try {
					String str_time=getTime();
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str_time+"���Ÿ��.txt"), "UTF-8"));
					for (int i = 0; i < all_tmp.size(); i++) {
						one_tmp= all_tmp.get(i);
						String str_tmp = "ǰ���: " + one_tmp.get(0).toString() + " | ����: " + one_tmp.get(1).toString() + " | ���: " + one_tmp.get(2).toString()+" | ����: " + one_tmp.get(3).toString() + " | ����: "+ one_tmp.get(4).toString()+" | ����: "+one_tmp.get(5).toString();
						bw.write(str_tmp);
						bw.newLine();
					}
					bw.write("�ѱݾ� : "+total_price.getText().toString()+"��");
					bw.newLine();
					bw.flush();
					bw.close();
					JOptionPane.showMessageDialog(null, "���ſϷ�! �������� "+str_time+"���Ÿ��.txt�� Ȯ���ϼ���!","���� �˸�",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "������ ������ Ȯ�����ּ���!","���� �˸�",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		all_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow()!=-1)
				{
					mframe.dispose();
					List<String> tmp_data = new ArrayList();
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),0).toString());
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),1).toString());
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),2).toString());
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),3).toString());
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),4).toString());
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),5).toString());
					tmp_data.add(table1.getValueAt(table1.getSelectedRow(),6).toString());
					new changeData(data,tmp_data);
				}
				else {
					JOptionPane.showMessageDialog(null, "��ǰ����Ʈ�� ���õǾ��ִ��� Ȯ�����ּ���.","���� �˸�",JOptionPane.ERROR_MESSAGE);					
				}
			}
		});
		
		

		// combobox listener
		label1_combobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				combo1 = jcb.getSelectedItem();

				label2_combobox.setEnabled(true);
				label2_combobox.removeAllItems();

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

				DefaultTableModel table_model1 = new DefaultTableModel(array, header);
				table1.setModel(table_model1);
				List<String> numdata = new ArrayList<String>();
				for (int count = 0; count < table_model1.getRowCount(); count++) {
					String tmp_numdata = table_model1.getValueAt(count, 4).toString();
					if (numdata.contains(tmp_numdata)) {
						continue;
					} else {
						numdata.add(table_model1.getValueAt(count, 4).toString());
						label2_combobox.addItem(table_model1.getValueAt(count, 4).toString());
					}
				}
			}
		});

		label2_combobox.addActionListener(new ActionListener() {
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
				DefaultTableModel table_model1 = new DefaultTableModel(array, header);
				table1.setModel(table_model1);
			}
		});
		
		cart_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow()!=-1) {
					new checkItemcount(table1, table2, total_price);
				}
				else {
					JOptionPane.showMessageDialog(null, "��ǰ����Ʈ�� ���õǾ��ִ��� Ȯ�����ּ���.","���� �˸�",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ui
		setSize(1100, 590);
		setVisible(true);
	}
	MAIN(List<List<String>> data) {
		this.data=data;
	}
	public static void main(String[] args) {
		mframe = new MAIN();            
    }
	public String getTime() {
		SimpleDateFormat time_format = new SimpleDateFormat("yyyyMMddhhmmss");
		Date time = new Date();
		String result_time = time_format.format(time);
		return result_time;
	}
}

class Tools {
	List<List<String>> data = new ArrayList<List<String>>();
	List<String> itemList = new ArrayList<String>();

	public List<List<String>> run() {
		BufferedReader br = null;
		String line;
		String cvsSplitBy = ";";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("����깰_���_2018.txt"), "UTF-8"));
			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				line = line.replace("\"", "");
				String[] field = line.split(cvsSplitBy);
				itemList.add(field[0]);
				tmpList = Arrays.asList(field);
				data.add(tmpList);
			}
			System.out.print("�ȳ� : ������ ������ �����Ͽ����ϴ�.\n");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ ������ Ȯ�����ּ���!","���� �˸�",JOptionPane.ERROR_MESSAGE);

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public String[] getItemlist() {
		HashSet<String> tmp_hasharray = new HashSet<String>(itemList);
		ArrayList<String> tmp_itemList = new ArrayList<String>(tmp_hasharray);
		Collections.sort(tmp_itemList);
		String[] tmp_string_array = new String[tmp_itemList.size()];
		tmp_string_array = tmp_itemList.toArray(tmp_string_array);
		return tmp_string_array;
	}

	public void removeItem(List<List<String>> data, JTable j1) {
		List<String> tmp_data = new ArrayList();
		int row_index = j1.getSelectedRow();
		tmp_data.add(j1.getValueAt(row_index, 0).toString());
		tmp_data.add(j1.getValueAt(row_index, 1).toString());
		tmp_data.add(j1.getValueAt(row_index, 2).toString());
		tmp_data.add(j1.getValueAt(row_index, 3).toString());
		tmp_data.add(j1.getValueAt(row_index, 4).toString());
		tmp_data.add(j1.getValueAt(row_index, 5).toString());
		tmp_data.add(j1.getValueAt(row_index, 6).toString());
		data.remove(tmp_data);
		saveData(data);
		MAIN.label1_combobox.removeAllItems();
		String[] tmp_itemlist=getItemlist();
		for(int i=0;i<tmp_itemlist.length;i++) {
			MAIN.label1_combobox.addItem(tmp_itemlist[i]);	
		}
	}
	public void saveData(List<List<String>> data) {
		List<String> tmp_data_bw = new ArrayList();
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("����깰_���_2018.txt"), "UTF-8"));
			for (int i = 0; i < data.size(); i++) {
				tmp_data_bw = data.get(i);
				String str_tmp = "\"" + tmp_data_bw.get(0).toString() + "\";\"" + tmp_data_bw.get(1).toString() + "\";\"" + tmp_data_bw.get(2).toString()+"\";\"" + tmp_data_bw.get(3).toString() + "\";\""+ tmp_data_bw.get(4).toString()+"\";\""+tmp_data_bw.get(5).toString()+"\";\""+tmp_data_bw.get(6).toString()+"\"";
				bw.write(str_tmp);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ ������ Ȯ�����ּ���!","���� �˸�",JOptionPane.ERROR_MESSAGE);
		}
		finally {
			MAIN.mframe=new MAIN();
		}
	}
}
