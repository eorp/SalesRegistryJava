package view;

import model.InputUtility;

public enum ProductNames {
	only_one_domain,
    up_to_5_domains_,
    up_to_10_domains,
    up_to_20_domains;
	
	/*private String product;
	
	private ProductNames(String product) {
		this.product = product;
	}
	
	public String getProduct() {
		return product;
	}*/
	
	/*@Override
    public String toString() {
        return InputUtility.removeUnderscores(this.getProduct());
    }*/

	private static final String[] array;
    static {
        array = new String[ProductNames.values().length];
        for (ProductNames value : ProductNames.values())
            array[value.ordinal()] = value.toString();
    }
    public static String[] toArray () { return InputUtility.removeUnderscoresArray(array); }
}
