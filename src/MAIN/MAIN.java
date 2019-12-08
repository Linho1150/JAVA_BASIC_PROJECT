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
	private JButton ok = new JButton("확인");
	private JTextField tx = new JTextField(20);
	private JTable table;
	private JTable table2;
	private JLabel total_price;

	public addFrame(JTable table, JTable table2, JLabel total_price) {
		this.table = table;
		this.table2 = table2;
		this.total_price = total_price;

		setTitle("add");
		Font f1 = new Font("돋움", Font.PLAIN, 10);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel lb = new JLabel("수량 입력");

		lb.setFont(f1);
		ok.setFont(f1);

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
	private JButton ok = new JButton("확인");
	private String header[] = { "품목명", "단위", "등급", "가격", "산지", "친환경구분", "입력일" };

	public Seeall(List<List<String>> data2) {
		String[][] array = new String[data2.size()][];
		for (int i = 0; i < data2.size(); i++) { 
			List<String> row = data2.get(i);
			array[i] = row.toArray(new String[row.size()]); 
		}
		
		setTitle("add");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel lb = new JLabel("전체 목록");
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
				//window 닫기 기능
				setVisible(false);
			}
		});
	}
}

public class MAIN extends JFrame {
	public String header[] = { "품목명", "단위", "등급", "가격", "산지", "친환경구분", "입력일" };
	public String header2[] = { "품목명", "단위", "등급", "가격", "산지", "수량" };
	public Object combo1 = "";
	public Object combo2 = "";

	MAIN() {
		// 초기세팅---------------------------------------------------
		setTitle("농수산물 구매 프로세스");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = getContentPane();
		//// ----------------------------------------------------------

		// 첫화면 실행---------------------------------------------------
		Tools t1 = new Tools();
		List<List<String>> data = t1.run();
		//// ----------------------------------------------------------

		// Jtabel에 넣기 위한 List to String array
		// ---------------------------------------------------
		//String[][] array = new String[data.size()][];
		/*
		 * for (int i = 0; i < conten.size(); i++) { List<String> row = conten.get(i);
		 * array[i] = row.toArray(new String[row.size()]); }
		 */
		//// ----------------------------------------------------------

		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();

		j1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
		j2.setLayout(new FlowLayout());

		c.add(j1, BorderLayout.NORTH);
		c.add(j2);

		// panel j1
		// Jcombobox1
		JLabel label1 = new JLabel("상품목록 : ");
		String[] label1_list = t1.getItemlist();
		JComboBox<String> label1_combobox = new JComboBox<String>(label1_list); // 생선이름데이터 리스트
		j1.add(label1);
		j1.add(label1_combobox);

		// Jcombobox2
		JLabel name1 = new JLabel(" 원산지 : ");
		String[] namelist1 = t1.getItemlist();
		JComboBox<String> comboname1 = new JComboBox<String>(namelist1); // 생선이름데이터 리스트
		j1.add(name1);
		j1.add(comboname1);
		comboname1.setEnabled(false);

		JLabel total = new JLabel(" 총금액 : ");
		j1.add(total);
		JLabel total_price = new JLabel("0");
		j1.add(total_price);

		// panel j2
		// JTable원산지
		String[][] array = new String[0][];
		DefaultTableModel model = new DefaultTableModel(array, header);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		j2.add(sc);
		
		// 오른쪽 테이블
		DefaultTableModel model2 = new DefaultTableModel(array, header2);
		JTable table2 = new JTable(model2);
		JScrollPane sc2 = new JScrollPane(table2);
		j2.add(sc2);

		// button
		JButton add = new JButton("상품 추가");
		JButton remove_one = new JButton("선택 상품 제거");
		JButton remove_all = new JButton("전체 상품 제거");
		JButton additems = new JButton("상품 전체 리스트");

		j2.add(add);
		j2.add(remove_one);
		j2.add(remove_all);
		j2.add(additems);

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

		additems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Seeall(data);
			}

		}); // insert/remove listener

		// combobox listener
		label1_combobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				combo1 = jcb.getSelectedItem();

				comboname1.setEnabled(true);
				comboname1.removeAllItems();

				// 맞춤 table 가져오기---------------------------------------
				List<List<String>> tmp = new ArrayList<List<String>>();
				for (int i = 0; i < data.size(); i++) {
					List<String> row = data.get(i);
					if (row.get(0).equals(combo1)) {
						tmp.add(row);
					}
				}
				//// ---------------------------------------------------

				// TABLE에 맞는 형변환---------------------------------------
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
		setSize(1000, 650);
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
			br = new BufferedReader(new InputStreamReader(new FileInputStream("농수산물_경매_2018.csv"), "UTF-8"));
			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				line = line.replace("\"", "");
				String[] field = line.split(cvsSplitBy);
				itemList.add(field[0]);
				tmpList = Arrays.asList(field);
				ret.add(tmpList);
			}
			System.out.print("안내 : CSV파일을 변수로 저장하였습니다.\n");
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
		System.out.print("안내 : itemList 로딩을 완료하였습니다.\n");
		String[] stockArr = new String[tmp_itemList.size()];
		stockArr = tmp_itemList.toArray(stockArr);
		return stockArr;
	}

}
