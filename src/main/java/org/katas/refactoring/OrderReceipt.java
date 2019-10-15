package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;
    private double totalSalesTax = 0d;
    private double totalAmount = 0d;
    private double DISCOUNT = .10;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        output.append("======Printing Orders======\n");
        output.append(appendCustomerDetails());


        for (LineItem lineItem : order.getLineItems()) {
            output.append(appendLineItemDetails(lineItem));
            double salesTax = lineItem.totalAmount() * DISCOUNT;
            computeTotalSalesTaxAndAmount(lineItem.totalAmount(), salesTax);
        }

        output.append("Sales Tax").append('\t').append(totalSalesTax);
        output.append("Total Amount").append('\t').append(totalAmount);
        return output.toString();
    }

    private void computeTotalSalesTaxAndAmount(double itemAmount, double salesTax) {
        totalSalesTax += salesTax;
        totalAmount+= itemAmount  + salesTax;
    }

    private String appendLineItemDetails(LineItem lineItem) {
        return lineItem.getDescription() + "\t" +
                lineItem.getPrice() + "\t" +
                lineItem.getQuantity() + "\t" +
                lineItem.totalAmount() + "\n";
    }

    private String appendCustomerDetails() {
        return order.getCustomerName() + "\n" +
               order.getCustomerAddress();
    }
}