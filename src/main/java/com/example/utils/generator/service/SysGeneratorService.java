package com.example.utils.generator.service;

import java.util.Map;

/**
 * 代码生成器 
 */
public interface SysGeneratorService {

	/**
	 *  列表
	 */
	Map<String, Object> queryList(Map<String, Object> map) throws Exception;
	
	/**
	 *  生成代码
	 */
	byte[] generatorCode(String[] tableNames) throws Exception;
}
