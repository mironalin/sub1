package ro.ase.sub1;

import java.io.Serializable;

public class Invoice implements Serializable {
    private long customerCode;
    private String invoiceType;
    private float amount;

    public Invoice(long customerCode, String invoiceType, float amount) {
        setCustomerCode(customerCode);
        setInvoiceType(invoiceType);
        setAmount(amount);
    }

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        if (String.valueOf(customerCode).length() != 13) {
            throw new RuntimeException("customer code invalid");
        }
        this.customerCode = customerCode;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        if (invoiceType == null || invoiceType.isEmpty()) {
            throw new RuntimeException("invoice type invalid");
        }
        this.invoiceType = invoiceType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        if (amount < 0) {
            throw new RuntimeException("amount invalid");
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ro.ase.sub1.Invoice{" +
                "customerCode=" + customerCode +
                ", invoiceType='" + invoiceType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
