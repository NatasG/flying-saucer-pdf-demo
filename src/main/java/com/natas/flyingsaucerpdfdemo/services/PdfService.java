package com.natas.flyingsaucerpdfdemo.services;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfService {

  @Autowired
  private TemplateEngine templateEngine;

  @Value("${pdf.path}")
  private String pdfPath;

  private String getHtml(String imageAbsolutePath, String msg) {
    var ctx = new Context();
    ctx.setVariable("msg", msg);
    ctx.setVariable("imageSrc", imageAbsolutePath);
    return templateEngine.process("report", ctx);
  }

  public void generatePdf() throws IOException, DocumentException {
    var image = new ClassPathResource("static/images/cat.jpeg");
    var imageAbsolutePath = image.getFile().getAbsolutePath();
    var outputStream = new FileOutputStream(pdfPath);
    var renderer = new ITextRenderer();
    var html = getHtml(imageAbsolutePath, "hello kitty");
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(outputStream);
    outputStream.close();
  }

}
