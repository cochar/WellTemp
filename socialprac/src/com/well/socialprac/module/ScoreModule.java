package com.well.socialprac.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.mvc.annotation.At;

@At("score")
public class ScoreModule extends BaseModule {

	@At
	public Map<String,Object> scoreList(){
		Map<String,Object> result=new HashMap<String,Object>();
//		Sql sql = "selet score teamScore from " ;
		return result;
	}
}
