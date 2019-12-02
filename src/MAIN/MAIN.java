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
			System.out.print("해당사항을 입력하세요 >> ");
			try {choose=sc.nextInt();}catch(Exception e){System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");continue;}
			switch(choose) {
				case 0: //모든 리스트 찾기
					tl.countItem();
					break;
				case 1: //리스트에 추가하기
					System.out.print("종류를 입력하세요 >>");
					input_type=sc.next();
					System.out.print("추가할 품목을 입력하세요 >>");
					input_name=sc.next();
					tl.setItem(input_type,input_name);
					break;
				case 2: //리스트 품목제거
					System.out.print("삭제할 품목을 입력하세요 >>");
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
	//item 리스트를 불러옵니다.
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
	
	//item 리스트를 모두 출력합니다.
	public void countItem() {
		try {
			int count=1;
			List item_list = getItem();
			Iterator iterator = item_list.iterator();
			while (iterator.hasNext()) {
			    String element = (String) iterator.next();
			    String [] spt=element.split(",");
			    System.out.println(String.valueOf(count)+". 종류 :"+spt[0]+" 이름:"+spt[1]);
			    
			    count++;
			}
		}
		catch(Exception e){
			System.out.println("item 리스트를 불러오는 과정에서 오류가 발생했습니다.");
			System.out.println("오류코드 >>"+e);
		}
	}
	
	//item을 추가합니다.
	public void setItem(String input_type,String input_name) {
		try{
			FileWriter FW = new FileWriter(file,true);
			FW.write(input_type+","+input_name+"\n");
			FW.close();
		} catch (IOException e) {
			System.out.println("오류코드 >>"+e);
		}
	}
	//item을 삭제합니다.
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
			System.out.println("삭제되었습니다.");
		}
		catch(Exception e){
			System.out.println("오류코드 >>"+e);
		}
	}
}