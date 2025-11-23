package com.mcp_server.smartdoc.tools;

import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PdfToolMcp {
    @McpTool(name="extractPdfText")
    public String extractPdfText(@McpArg(description="PDF File") MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream(); PDDocument doc = PDDocument.load(is)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc);
        }
    }
}
