package MAIN;

import java.io.*;
import java.util.*;

public class MAIN {
	public static void main(String[] args) {
		int choose=0;
		String input_type="",input_name="";
		boolean loop=true;
		Tools tl=new Tools();
		while(loop) {
			Scanner sc = new Scanner(System.in);
			System.out.print("�ش������ �Է��ϼ��� >> ");
			try {choose=sc.nextInt();}catch(Exception e){System.out.println("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");continue;}
			switch(choose) {
				case 0: //��� ����Ʈ ã��
					tl.countItem();
					break;
				case 1: //����Ʈ�� �߰��ϱ�
					System.out.print("������ �Է��ϼ��� >>");
					input_type=sc.next();
					System.out.print("�߰��� ǰ���� �Է��ϼ��� >>");
					input_name=sc.next();
					tl.setItem(input_type,input_name);
					break;
				case 2: //����Ʈ ǰ������
					System.out.print("������ ǰ���� �Է��ϼ��� >>");
					input_name=sc.next();
					tl.removeItem(input_name);
					break;
				case 3:
					loop=false;
					break;
			}
		}
	}
}
class Tools{
	File file = new File("item.txt");	
	//item ����Ʈ�� �ҷ��ɴϴ�.
	public List getItem() {
		try
		{
			List item_list = new ArrayList();
			String text_one_line = "";
	        FileReader FR = new FileReader(file);
	        BufferedReader BR = new BufferedReader(FR);
	        while((text_one_line = BR.readLine()) != null){
	        	String[] spt=text_one_line.split(",");
	        	item_list.add(spt[0]+","+spt[1]);
	        }
	        FR.close();
	        BR.close();
	        return item_list;
	    }
		catch (FileNotFoundException e){System.out.println("ERROR >>"+e);}
		catch(IOException e){System.out.println("ERROR >>"+e);}
		return null;
	}
	
	//item ����Ʈ�� ��� ����մϴ�.
	public void countItem() {
		try {
			int count=1;
			List item_list = getItem();
			Iterator iterator = item_list.iterator();
			while (iterator.hasNext()) {
			    String element = (String) iterator.next();
			    String [] spt=element.split(",");
			    System.out.println(String.valueOf(count)+". ���� :"+spt[0]+" �̸�:"+spt[1]);
			    
			    count++;
			}
		}
		catch(Exception e){
			System.out.println("item ����Ʈ�� �ҷ����� �������� ������ �߻��߽��ϴ�.");
			System.out.println("�����ڵ� >>"+e);
		}
	}
	
	//item�� �߰��մϴ�.
	public void setItem(String input_type,String input_name) {
		try{
			FileWriter FW = new FileWriter(file,true);
			FW.write(input_type+","+input_name+"\n");
			FW.close();
		} catch (IOException e) {
			System.out.println("�����ڵ� >>"+e);
		}
	}
	//item�� �����մϴ�.
	public void removeItem(String input) {
		try{
			List item_list = getItem();
			Iterator iterator = item_list.iterator();
			FileWriter FW = new FileWriter(file);
			while (iterator.hasNext()) {
				String element = (String) iterator.next();
				if(element.contains(input)) {
					continue;
				}
				String [] spt=element.split(",");
			    FW.write(spt[0]+","+spt[1]+"\n");
			}
			FW.close();
			System.out.println("�����Ǿ����ϴ�.");
		}
		catch(Exception e){
			System.out.println("�����ڵ� >>"+e);
		}
	}
}