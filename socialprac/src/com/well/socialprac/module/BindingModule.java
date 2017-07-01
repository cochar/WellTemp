package com.well.socialprac.module;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/binding")
public class BindingModule extends BaseModule {

	@At("/token")
	@Ok("json")
	public String getToken(String id){

		return "success";
	}
}
