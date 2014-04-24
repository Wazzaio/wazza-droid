package com.wazza.android.sdk.service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.wazza.android.sdk.domain.User;

import java.util.LinkedList;
import java.util.List;

public class UserService {

	private Context context;

    private User user;

    public User getUser() {
        return user;
    }

	public void setUsername(){
		String username;
		AccountManager manager = AccountManager.get(context);
	    Account[] accounts = manager.getAccountsByType("com.google");
	    List<String> possibleEmails = new LinkedList<String>();

	    for (Account account : accounts) {
	      // TODO: Check possibleEmail against an email regex or treat
	      // account.name as an email address only for certain account.type values.
	      possibleEmails.add(account.name);
	    }

	    if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
	        String email = possibleEmails.get(0);
	        String[] parts = email.split("@");
	        if(parts.length > 0 && parts[0] != null)
	            username = parts[0];
	        else
	        	username = null;
	    }else
	    	username = null;
	    
	    user.setUsername(username);
	}
	
	public String getUsername(){
		return user.getUsername();
   	}

	public UserService(Context context) {
		this.context = context;
		user = new User();
		setUsername();
	}
	
}
