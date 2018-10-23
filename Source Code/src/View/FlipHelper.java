package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JProgressBar;

import Model.StringData;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FlipHelper {
	private ArrayList<StringData> best;
	JProgressBar progressBar = new JProgressBar();
	private int index = 0;
	private ArrayList<JLabel> item1 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item2 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item3 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item4 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item5 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item6 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item7 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item8 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item9 = new ArrayList<JLabel>(); 
	private ArrayList<JLabel> item10 = new ArrayList<JLabel>(); 
	private ArrayList<ArrayList<JLabel>> items = new ArrayList<ArrayList<JLabel>>();
	private JFrame frmBullseyesOsrsFlip;
	private JLabel lblStatus;

	/**
	 * Create the application.
	 */
	public FlipHelper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBullseyesOsrsFlip = new JFrame();
		frmBullseyesOsrsFlip.setTitle("Bullseye's OSRS Flip Helper");
		frmBullseyesOsrsFlip.setBounds(100, 100, 1000, 500);
		frmBullseyesOsrsFlip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmBullseyesOsrsFlip.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		JPanel Item1 = new JPanel();
		panel.add(Item1);
		Item1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblItem1 = new JLabel("Name:");
		Item1.add(lblItem1);
		
		item1.add(lblItem1);
		
		JPanel Item2 = new JPanel();
		panel.add(Item2);
		
		JLabel lblItem2 = new JLabel("Name:");
		Item2.add(lblItem2);
		
		item2.add(lblItem2);
		
		JPanel Item3 = new JPanel();
		panel.add(Item3);
		
		JLabel lblItem3 = new JLabel("Name:");
		Item3.add(lblItem3);
		
		item3.add(lblItem3);
		
		JPanel Item4 = new JPanel();
		panel.add(Item4);
		
		JLabel lblItem4 = new JLabel("Name:");
		Item4.add(lblItem4);
		
		item4.add(lblItem4);
		
		JPanel Item5 = new JPanel();
		panel.add(Item5);
		
		JLabel lblItem5 = new JLabel("Name:");
		Item5.add(lblItem5);
		
		item5.add(lblItem5);
		
		JPanel Item6 = new JPanel();
		panel.add(Item6);
		
		JLabel lblItem6 = new JLabel("Name:");
		Item6.add(lblItem6);
		
		item6.add(lblItem6);
		
		JPanel Item7 = new JPanel();
		panel.add(Item7);
		
		JLabel lblItem7 = new JLabel("Name:");
		Item7.add(lblItem7);
		
		item7.add(lblItem7);
		
		JPanel Item8 = new JPanel();
		panel.add(Item8);
		
		JLabel lblItem8 = new JLabel("Name:");
		Item8.add(lblItem8);
		
		item8.add(lblItem8);
		
		JPanel Item9 = new JPanel();
		panel.add(Item9);
		
		JLabel lblItem9 = new JLabel("Name:");
		Item9.add(lblItem9);
		
		item9.add(lblItem9);
		
		JPanel Item10 = new JPanel();
		panel.add(Item10);
		
		JLabel lblItem10 = new JLabel("Name:");
		Item10.add(lblItem10);
		
		item10.add(lblItem10);
		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		items.add(item8);
		items.add(item9);
		items.add(item10);
		
		JPanel panel_1 = new JPanel();
		frmBullseyesOsrsFlip.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblStatus = new JLabel(" Status:");
		panel_1.add(lblStatus);
		
		JPanel panel_2 = new JPanel();
		frmBullseyesOsrsFlip.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnPrevious = new JButton("Previous 10");
		btnPrevious.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				index -= 10;
				if (index < 10){
					index = 0;
				}	
				setText();
			}		
		});
		panel_2.add(btnPrevious);
		
		JButton btnNewButton = new JButton("Next 10");
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				index += 10;
				if (index >= best.size()){
					index = best.size();
				}
				setText();
			}		
		});
		panel_2.add(btnNewButton);
		
		frmBullseyesOsrsFlip.setVisible(true);
	}
	
	public void limits(){
		ArrayList<String[]> limitlist = getLimits();
		for (int i=0; i<best.size(); i++){
			for (int j=0; j< limitlist.size(); j++){
				if (best.get(i).getId() == Integer.parseInt(limitlist.get(j)[0])){
					best.get(i).setMax_amount(Integer.parseInt(limitlist.get(j)[1]));
				}
			}
		}
	}
	
	public void setText(){
		int max=0;
		if (best.size()>10){max=10;}else{max=best.size();}
		for (int i=0; i<max; i++){
			String s ="<html>";
			s+= best.get(index+i).getName();
			if (s.contains("\\u0027")){
				s.replace("\\u0027", "");
			}
			if (best.get(index+i).getBuy_average() >= best.get(index+i).getSell_average()){
				s+="<br/>B@:"+Integer.toString(best.get(index+i).getSell_average());
				s+="<br/>S@:"+Integer.toString(best.get(index+i).getBuy_average());
			}
			else{
				s+="<br/>B@:"+Integer.toString(best.get(index+i).getBuy_average());
				s+="<br/>S@:"+Integer.toString(best.get(index+i).getSell_average());
			}
			if (best.get(index+i).getMax_amount() == 0){
				s+="<br/>Limit: Infinite";
			}
			else {
				s+="<br/>Limit:"+Integer.toString(best.get(index+i).getMax_amount());
			}
			s+="<br/>Profit:"+Integer.toString(best.get(index+i).getDiff());
			s+="<br/>Buy Quantity:"+Integer.toString(best.get(index+i).getBuying_quantity());
			s+="<br/>Sell Quantity:"+Integer.toString(best.get(index+i).getSelling_quantity());
			s+="<html>";
			items.get(i).get(0).setText(s);
		}
	}
	
	/*normalizeer diff en quantities en sorteer de lijst op deze genormalizeerde waarden*/
	public ArrayList<StringData> SortedBest(ArrayList<StringData> oldlist){
		ArrayList<StringData> newlist = new ArrayList<StringData>();
		float averageDiff = 0;
		float averageQuantity = 0;
		int highestDiff = 0;
		int highestQuantities = 0;
		for (int i=0; i<oldlist.size(); i++){
			averageDiff+=oldlist.get(i).getDiff();
			averageQuantity+=oldlist.get(i).getBuying_quantity()+oldlist.get(i).getSelling_quantity();
			if(oldlist.get(i).getDiff() > highestDiff){
				highestDiff = oldlist.get(i).getDiff();
			}
			if(oldlist.get(i).getBuying_quantity()+oldlist.get(i).getSelling_quantity() > highestQuantities){
				highestQuantities = oldlist.get(i).getBuying_quantity()+oldlist.get(i).getSelling_quantity();
			}
		}
		averageDiff = averageDiff / (oldlist.size()*highestDiff);
		averageQuantity = averageQuantity / (oldlist.size()*highestQuantities);
		for (int i=0; i<oldlist.size(); i++){
			oldlist.get(i).setNormalizedDiff((float) oldlist.get(i).getDiff()/highestDiff);
			oldlist.get(i).setNormalizedQuantity((float) (oldlist.get(i).getBuying_quantity()+oldlist.get(i).getSelling_quantity())/highestQuantities);
		}	
		newlist.add(oldlist.get(0));
		for (int i = 1; i<oldlist.size(); i++){
			if (oldlist.get(i).getNormalizedDiff() >= 0.01*averageDiff && oldlist.get(i).getNormalizedQuantity() >= 0.00*averageQuantity && oldlist.get(i).getDiff()>(float)0.05*oldlist.get(i).getBuy_average()){
				float NormalizedDiff = oldlist.get(i).getNormalizedDiff();
				float NormalizedQuantity = oldlist.get(i).getNormalizedQuantity();
				float NormalizedProduct = NormalizedDiff * NormalizedQuantity;
				boolean added = false;
				for (int j = 0; j<newlist.size(); j++){
					if (NormalizedProduct > newlist.get(j).getNormalizedDiff()*newlist.get(j).getNormalizedQuantity() && added != true){
						newlist.add(j, oldlist.get(i));
						added = true;
					}
				}
				if (added == false){
					newlist.add(oldlist.get(i));
				}
			}
		}	
		return newlist;
	}
	
	//Gets the limits from the txt file
	public static ArrayList<String[]> getLimits(){
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<String[]> limitlist = new ArrayList<String[]>();
		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("limits.txt");
			br = new BufferedReader(fr);
			
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] items = sCurrentLine.split(",");
				ArrayList<String> itemlist = new ArrayList<String>(Arrays.asList(items));
				for (int i=0; i<itemlist.size(); i++){
					limitlist.add(itemlist.get(i).split(":"));
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return limitlist;
	}
	
	public void setStatus(String s){
		lblStatus.setText(s);
	}
	
	public void updateProgress(int i, int max){
		progressBar.setMaximum(max);
		progressBar.setValue(i);
	}
	
	public ArrayList<StringData> getBest() {
		return best;
	}

	public void setBest(ArrayList<StringData> best) {
		this.best = best;
	}
}
