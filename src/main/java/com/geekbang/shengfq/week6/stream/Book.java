package com.geekbang.shengfq.week6.stream;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
/**
 * 生产资料
 * @author sheng
 * */
@Data
public class Book implements Comparable, Serializable {
	private int id;
	private String name;
	private double price;
	private String type;
	private LocalDate publishDate;

	public Book(int id, String name, double price, String type, LocalDate publishDate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.publishDate = publishDate;
	}

	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Book)){
			return false;
		}
		Book target=(Book)obj;
		return this.name.equalsIgnoreCase(target.getName());
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", publishDate="
				+ publishDate + "]";
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}
