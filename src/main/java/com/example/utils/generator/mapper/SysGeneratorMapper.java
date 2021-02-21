package com.example.utils.generator.mapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器 
 */
public interface SysGeneratorMapper {
	
	List<Map<String, Object>> queryList(Map<String, Object> map) throws Exception;
	
	int queryTotal(Map<String, Object> map) throws Exception;
	
	Map<String, String> queryTable(String tableName) throws Exception;
	
	List<Map<String, String>> queryColumns(String tableName) throws Exception;
}
