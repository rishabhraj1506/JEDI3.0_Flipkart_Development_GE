package com.flipkart.bean;

/**
 * 
 * @author JEDI-03
 * Class to store billing information
 * 
 */
public class Billing {

    private String billingID;
    private int studentID;
    private float billamt;
    private boolean status; // true for paid, false for unpaid

    /**
     * Parameterized constructor
     * @param billingID: unique billing identifier
     * @param studentID: ID of the student
     * @param billamt: amount to be billed
     * @param status: payment status
     */
    public Billing(String billingID, int studentID, float billamt, boolean status) {
        this.billingID = billingID;
        this.studentID = studentID;
        this.billamt = billamt;
        this.status = status;
    }

    // Getters and Setters
    public String getBillingID() {
        return billingID;
    }

    public void setBillingID(String billingID) {
        this.billingID = billingID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public float getBillamt() {
        return billamt;
    }

    public void setBillamt(float billamt) {
        this.billamt = billamt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Method to provide information about the payment
     * @return information about the payment
     */
    public String infoAboutPay() {
        return String.format("Billing ID: %s\nStudent ID: %d\nBill Amount: %.2f\nStatus: %s",
                billingID, studentID, billamt, status ? "Paid" : "Unpaid");
    }
}
