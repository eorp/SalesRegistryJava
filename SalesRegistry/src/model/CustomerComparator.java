package model;

import java.util.Comparator;

public enum CustomerComparator implements Comparator<Customer> {


	ID_SORT {
        public int compare(Customer o1, Customer o2) {
            return Integer.valueOf(o1.getId()).compareTo(o2.getId());
        }},
    NAME_SORT {
        public int compare(Customer o1, Customer o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }},
        DATE_SORT {
            public int compare(Customer o1, Customer o2) {
                return o1.getProduct().getPaymentDate().compareTo(o2.getProduct().getPaymentDate());
            }},
            EMAIL_SORT {
                public int compare(Customer o1, Customer o2) {
                    return o1.getEmail().compareTo(o2.getEmail());
                }},
                COUNTRY_SORT {
                    public int compare(Customer o1, Customer o2) {
                        return o1.getAddress().getCountry().compareTo(o2.getAddress().getCountry());
                    }},       
        PRICE_SORT {
            public int compare(Customer o1, Customer o2) {
            	return Double.compare(o1.getProduct().getPrice(), o2.getProduct().getPrice());
               // return Double.valueOf(o1.getProduct().getPrice().compareTo(o2.getFullName()));
            }};
       
    public static Comparator<Customer> decending(final Comparator<Customer> other) {
        return new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                return -1 * other.compare(o1, o2);
            }
        };
    }

    public static Comparator<Customer> getComparator(final CustomerComparator... multipleOptions) {
        return new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                for (CustomerComparator option : multipleOptions) {
                    int result = option.compare(o1, o2);
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        };
    }

}
