package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import Model.StringData;
import View.FlipHelper;

public class Main {
	private static int cash;
	private static ArrayList<StringData> ItemsList;
	
	public static void main(String[] args) throws IOException {
		//Get cash to flip
		String cashString = JOptionPane.showInputDialog("How much cash do you have to flip?");
		cash = Integer.parseInt(cashString);
		
		FlipHelper fh = new FlipHelper();
		
		File file = new File("limits.txt");
		boolean empty = file.length() == 0;
		if(empty == true){
			fh.setStatus("Getting limits from ge-tracker");
			getLimits();
		}
		
		//Get data from all items from rsbuddy summary.json and put them into StringData object into ItemsList
		fh.setStatus("Getting data from rsbuddy");
		URL url = new URL("https://rsbuddy.com/exchange/summary.json");
		URLConnection urlConn = url.openConnection();
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		ItemsList = new ArrayList<StringData>();
		String line = buff.readLine();
		while (line != null){
			line = line.substring(1, line.length() - 2);
			String[] items = line.split("},");
			ArrayList<String> itemlist = new ArrayList<String>(Arrays.asList(items));
			for (int i = 0; i< itemlist.size() ; i++){
				String s = itemlist.get(i);
				int index = 0;
				while(s.charAt(index) != '{'){
					index++;
				}
				index++;
				s = s.substring(index);
				itemlist.set(i,s);
				String[] stringdata = s.split(",");
				ArrayList<String> stringdatalist = new ArrayList<String>(Arrays.asList(stringdata));
				
				int id = 0;
				String name = "";
				boolean ismember = false;
				int sp = 0;
				int buy_average = 0;
				int buy_quantity = 0;
				int sell_average = 0;
				int sell_quantity = 0;
				int overall_average = 0;
				int overall_quantity = 0;
				
				for (int k = 0; k<10; k++){
					String string = stringdatalist.get(k);
					String[] rightpart = string.split(":");
					switch (k){
					case 0:
						id = Integer.parseInt(rightpart[1]);
						break;
					case 1:
						name = rightpart[1];
						break;
					case 2:
						if(rightpart[1] == "true"){
							ismember = true;
						}
						else {
							ismember = false;
						}
						break;
					case 3:
						sp = Integer.parseInt(rightpart[1]);
						break;
					case 4:
						buy_average = Integer.parseInt(rightpart[1]);
						break;
					case 5:
						buy_quantity = Integer.parseInt(rightpart[1]);
						break;
					case 6:
						sell_average = Integer.parseInt(rightpart[1]);
						break;
					case 7:
						sell_quantity = Integer.parseInt(rightpart[1]);
						break;
					case 8:
						overall_average = Integer.parseInt(rightpart[1]);
						break;
					case 9:
						overall_quantity = Integer.parseInt(rightpart[1]);
						break;
					}
				}
				int diff = 0;
				if (sell_average != 0 && buy_average != 0){
					if (sell_average > buy_average){
						diff = sell_average - buy_average;
					}
					else if (buy_average >= sell_average){
						diff = buy_average - sell_average;
					}
				}
				StringData std = new StringData(id,name,ismember,sp,buy_average,buy_quantity,sell_average,sell_quantity,overall_average,overall_quantity,diff);
				ItemsList.add(std);
			}
			line = buff.readLine();
		}
		fh.setStatus("Sorting Data");
		ItemsList = sortDiff(ItemsList);
		ItemsList = fh.SortedBest(ItemsList);
		fh.setBest(ItemsList);
		fh.setStatus("Getting limits from file");
		fh.limits();
		fh.setStatus("Done!");
		fh.setText();
	}
	
	/* sorteert itemlijst op grootte van diff, gooit items met diff 0 weg */
	public static ArrayList<StringData> sortDiff(ArrayList<StringData> oldlist){
		ArrayList<StringData> newlist = new ArrayList<StringData>();
		newlist.add(oldlist.get(0));
		for (int i = 1; i<oldlist.size(); i++){
			int diff = oldlist.get(i).getDiff();
			boolean added = false;
			for (int j = 0; j<newlist.size(); j++){
				if (diff > newlist.get(j).getDiff() && added != true && diff!=0 && cash > oldlist.get(i).getBuy_average()){
					newlist.add(j, oldlist.get(i));
					added = true;
				}
			}
			if (added == false && diff!=0 && cash > oldlist.get(i).getBuy_average()){
				newlist.add(oldlist.get(i));
			}
		}
		return newlist;
	}
	
	/*gets limits from ge-tracker and puts them in txt file*/
	public static void getLimits() throws IOException{
		URL url = new URL("https://www.ge-tracker.com/osrs-item-list/0");
		URLConnection urlConn = url.openConnection();
		urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		//String price = "not found";
		String line = buff.readLine();
		String s="";
		while (line != null){
			if (line.contains("var fe_item_list")){
				line = line.substring(42, line.length() - 17);
				//split lijnen
				String[] items = line.split("},");
				ArrayList<String> itemlist = new ArrayList<String>(Arrays.asList(items));
				for (int i=0; i<itemlist.size(); i++){
					//splits per data-item
					String[] data = itemlist.get(i).split(",");
					ArrayList<String> datasplit=new ArrayList<String>((Arrays.asList(data)));
					for (int j=0; j<datasplit.size(); j++){
						String[] toadd = datasplit.get(j).split(":");
						if (j==3 || j==5){
							s+=toadd[1];
						}	
						if (j==3){
							s+=":";
						}	
						if (j==5 && j!=datasplit.size()-1){
							s+=",";
						}	
					}
				}
			}
			line = buff.readLine();
		}
		PrintWriter out = new PrintWriter("limits.txt");
		out.println(s);
		out.close();
	}
}
