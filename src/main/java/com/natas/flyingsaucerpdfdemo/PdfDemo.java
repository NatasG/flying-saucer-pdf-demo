package com.natas.flyingsaucerpdfdemo;

import com.natas.flyingsaucerpdfdemo.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PdfDemo implements ApplicationRunner {

  @Autowired
  private PdfService service;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    service.generatePdf();
  }
}
