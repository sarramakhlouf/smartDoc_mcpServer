package com.mcp_server.smartdoc.tools;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvToolMcp {
	@McpTool(name="readCsv")
    public List<Map<String,String>> readCsv(@McpArg(description="CSV File") MultipartFile file) throws Exception {
        try (Reader reader = new java.io.InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            List<Map<String,String>> rows = new ArrayList<>();
            for (CSVRecord r : records) {
                rows.add(r.toMap());
            }
            return rows;
        }
    }

}
