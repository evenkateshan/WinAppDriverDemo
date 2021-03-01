package com.win.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Pages<T> {
	// An object of type T is declared
	T page;

	Pages(T page) {
		this.page = page;
	} // constructor

	public T getPage() {
		return this.page;
	}
}
