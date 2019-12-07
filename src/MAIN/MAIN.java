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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.*;


public class MAIN extends JFrame {
	public String header[] = {"품목명","단위","등급","가격","산지","친환경구분","입력일"};
	public Object combo1 = "";
	public Object combo2 = "";

	MAIN() {
		//초기세팅---------------------------------------------------
		setTitle("농수산물 구매 프로세스");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		////----------------------------------------------------------
		
		//첫화면 실행---------------------------------------------------
		Tools t1=new Tools();
		List<List<String>> conten = t1.run();
		////----------------------------------------------------------
		
		//Jtabel에 넣기 위한 List to String array ---------------------------------------------------
		String[][] array = new String[conten.size()][];
		/*for (int i = 0; i < conten.size(); i++) {
		    List<String> row = conten.get(i);
		    array[i] = row.toArray(new String[row.size()]);
		}*/
		////----------------------------------------------------------
		
		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();

		j1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		j2.setLayout(null);

		c.add(j1, BorderLayout.NORTH);
		c.add(j2);

		// panel j1
		// Jcombobox1
		JLabel name = new JLabel("category");
		String[] namelist=t1.getItemlist();
		JComboBox<String> comboname = new JComboBox<String>(namelist); // 생선이름데이터 리스트
		j1.add(name);
		j1.add(comboname);
		
		// Jcombobox2
		JLabel name1 = new JLabel("cate123123gory");
		String[] namelist1=t1.getItemlist();
		JComboBox<String> comboname1 = new JComboBox<String>(namelist1); // 생선이름데이터 리스트
		j1.add(name1);
		j1.add(comboname1);
		comboname1.setEnabled(false);


		// panel j2
		// JTable원산지
		DefaultTableModel model = new DefaultTableModel(array, header);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(20, 20, 400, 200);
		j2.add(sc);
		sc.setEnabled(false);
		
		// 오른쪽 테이블
		String contents2[][] = {};
		DefaultTableModel model2 = new DefaultTableModel(contents2, header);
		JTable table2 = new JTable(model2);
		JScrollPane sc2 = new JScrollPane(table2);
		sc2.setBounds(500, 20, 400, 200);
		j2.add(sc2);
		sc2.setEnabled(false);

		//button
		Font f1 = new Font("돋움", Font.PLAIN, 10);
		Font f2 = new Font("맑은고딕", Font.PLAIN, 10);
		JButton add = new JButton("Add all");
		JButton remove = new JButton("Remove all");
		JButton additems = new JButton("Add/remove items");
		JButton graph = new JButton("graph");

		add.setFont(f1);
		add.setBounds(20, 340, 100, 30);

		remove.setFont(f1);
		remove.setBounds(370, 340, 100, 30);

		additems.setFont(f1);
		additems.setBounds(190, 240, 130, 30);

		j2.add(add);
		j2.add(remove);
		j2.add(additems);
		j2.add(graph);

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

				JLabel lb = new JLabel("원산지 입력");
				JTextField tx = new JTextField(20);
				JLabel lb2 = new JLabel("가격 입력");
				JTextField tx2 = new JTextField(20);
				JButton addbtn = new JButton("추가");
				JButton removebtn = new JButton("삭제");

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
				combo1 = jcb.getSelectedItem();				
				
				comboname1.setEnabled(true);
				comboname1.removeAllItems();
				
				//맞춤 table 가져오기---------------------------------------
				List<List<String>> tmp = new ArrayList<List<String>>();
				for(int i=0; i<conten.size();i++) {
				    List<String> row = conten.get(i);
				    if(row.get(0).equals(combo1)) {
				    	tmp.add(row);
				    }
				}
				////---------------------------------------------------
				
				//TABLE에 맞는 형변환---------------------------------------
				String[][] array = new String[tmp.size()][];
				for (int i = 0; i < tmp.size(); i++) {
				    List<String> row = tmp.get(i);
					array[i] = row.toArray(new String[row.size()]);					
				}	
				////---------------------------------------------------
				
				DefaultTableModel model = new DefaultTableModel(array, header);
				table.setModel(model);
				List<String> numdata = new ArrayList<String>();
			    for (int count = 0; count < model.getRowCount(); count++){
			    	String tmp_numdata=model.getValueAt(count, 4).toString();
			    	if(numdata.contains(tmp_numdata)) {continue;}else {
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
				for(int i=0; i<conten.size();i++) {
				    List<String> row = conten.get(i);
				    if(row.get(0).equals(combo1)&& row.get(4).equals(combo2)) {
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
				TableModel copymodel = table.getModel();
				int [] indexs = table.getSelectedRows();
				Object[] copyrow = new Object[7];
				DefaultTableModel copymode2=(DefaultTableModel) table2.getModel();
				for(int i = 0; i<indexs.length; i++) {
					copyrow[0]=copymodel.getValueAt(indexs[i], 0);
					copyrow[1]=copymodel.getValueAt(indexs[i], 1);
					copyrow[2]=copymodel.getValueAt(indexs[i], 2);
					copyrow[3]=copymodel.getValueAt(indexs[i], 3);
					copyrow[4]=copymodel.getValueAt(indexs[i], 4);
					copyrow[5]=copymodel.getValueAt(indexs[i], 5);
					copyrow[6]=copymodel.getValueAt(indexs[i], 6);

					copymode2.addRow(copyrow);
				}

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
	            line=line.replace("\"","");
                String[] field = line.split(cvsSplitBy);
                itemList.add(field[0]);              
                tmpList = Arrays.asList(field);
	            ret.add(tmpList);
            }
            System.out.print("안내 : CSV파일을 변수로 저장하였습니다.\n");
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

	public String[] getItemlist() {
	    HashSet<String> tmp_hasharray = new HashSet<String>(itemList);
	    ArrayList<String> tmp_itemList = new ArrayList<String>(tmp_hasharray);
	    System.out.print("안내 : itemList 로딩을 완료하였습니다.\n");
	    String[] stockArr = new String[tmp_itemList.size()];
	    stockArr = tmp_itemList.toArray(stockArr);
	    return stockArr;
	}

}
