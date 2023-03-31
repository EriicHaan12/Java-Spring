package com.springbasic2.domain;

public class ProductVO {
	private String productNo;
	private String productName;
	private int price;

	
	
	
//	기본생성자 생성
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ProductVO(String productNo, String productName, int price) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", productName=" + productName + ", price=" + price + "]";
	}

}
