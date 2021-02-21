package com.example.utils.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;
import com.example.utils.generator.mapper.SysGeneratorMapper;
import com.example.utils.generator.service.SysGeneratorService;
import com.example.utils.generator.util.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {

	@Autowired
	private SysGeneratorMapper sysGeneratorMapper;

	@Override
	public Map<String, Object> queryList(Map<String, Object> pramasMap) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", sysGeneratorMapper.queryTotal(pramasMap));
		map.put("data", sysGeneratorMapper.queryList(pramasMap));
		return map;
	}

	@Override
	public byte[] generatorCode(String[] tableNames) throws Exception{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = sysGeneratorMapper.queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = sysGeneratorMapper.queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
