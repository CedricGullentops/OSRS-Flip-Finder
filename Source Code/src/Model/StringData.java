package Model;

public class StringData {
	private int id = 0;
	private String name = "";
	private boolean ismember = false;
	private int sp = 0;
	private int buy_average = 0;
	private int sell_average = 0;
	private int overall_average = 0;
	private int overall_quantity = 0;
	private int diff = 0;
	private int buying_quantity = 0;
	private int selling_quantity = 0;
	private float normalizedDiff = 0;
	private float normalizedQuantity = 0;
	private int max_amount = 0;

	public StringData(int id, String name, boolean ismember, int sp, int buy_average, int buy_quantity,int sell_average, int sell_quantity,int overall_average, int overall_quantity,int diff){
		this.id = id;
		this.name = name;
		this.ismember = ismember;
		this.sp = sp;
		this.buy_average = buy_average;
		this.buying_quantity = buy_quantity;
		this.sell_average = sell_average;
		this.selling_quantity = sell_quantity;
		this.overall_average = overall_average;
		this.overall_quantity = overall_quantity;
		this.diff = diff;
	}

	public int getOverall_quantity() {
		return overall_quantity;
	}

	public void setOverall_quantity(int overall_quantity) {
		this.overall_quantity = overall_quantity;
	}
	
	public int getMax_amount() {
		return max_amount;
	}


	public void setMax_amount(int max_amount) {
		this.max_amount = max_amount;
	}
	
	public float getNormalizedDiff() {
		return normalizedDiff;
	}


	public void setNormalizedDiff(float normalizedDiff) {
		this.normalizedDiff = normalizedDiff;
	}

	public float getNormalizedQuantity() {
		return normalizedQuantity;
	}

	public void setNormalizedQuantity(float normalizedQuantity) {
		this.normalizedQuantity = normalizedQuantity;
	}
	
	public int getBuying_quantity() {
		return buying_quantity;
	}

	public void setBuying_quantity(int buying_quantity) {
		this.buying_quantity = buying_quantity;
	}

	public int getSelling_quantity() {
		return selling_quantity;
	}

	public void setSelling_quantity(int selling_quantity) {
		this.selling_quantity = selling_quantity;
	}
	
	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsmember() {
		return ismember;
	}

	public void setIsmember(boolean ismember) {
		this.ismember = ismember;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getBuy_average() {
		return buy_average;
	}

	public void setBuy_average(int buy_average) {
		this.buy_average = buy_average;
	}

	public int getSell_average() {
		return sell_average;
	}

	public void setSell_average(int sell_average) {
		this.sell_average = sell_average;
	}

	public int getOverall_average() {
		return overall_average;
	}

	public void setOverall_average(int overall_average) {
		this.overall_average = overall_average;
	}
}
