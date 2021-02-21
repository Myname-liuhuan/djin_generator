package com.example.utils.generator.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.utils.generator.service.SysGeneratorService;
import com.example.utils.generator.util.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成器 
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController{

	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 页面
	 */
	@RequestMapping("/page")
	public String page(@RequestParam Map<String, Object> params){
		return "sys/generatorlist";
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		Map<String, Object> map = new HashMap<String, Object>();;
		try {
			map = sysGeneratorService.queryList(query);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 200);
		}
		return map;
	}
	
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(@RequestParam String[] tables,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取表名，不进行xss过滤
		byte[] data = sysGeneratorService.generatorCode(tables);
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"sourcecode.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
        IOUtils.write(data, response.getOutputStream());  
	}
}
