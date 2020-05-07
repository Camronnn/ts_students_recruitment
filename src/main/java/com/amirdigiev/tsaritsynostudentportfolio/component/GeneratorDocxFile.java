package com.amirdigiev.tsaritsynostudentportfolio.component;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class GeneratorDocxFile {

    @Value("${application.docx-folder}")
    private String docxFolder;

    public void createDocxFilePortfolio(Student student) throws IOException {
        String name = student.getUser().getName();
        String surname = student.getUser().getSurname();

        XWPFDocument document = new XWPFDocument();
        FileOutputStream outputStream = new FileOutputStream(new File(
                docxFolder + File.separator + name + " " + surname + " портфолио.docx"
        ));

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun xwpfRun = paragraph.createRun();
        xwpfRun.setBold(true);
        xwpfRun.setCapitalized(true);
        xwpfRun.setText("портфолио");
        xwpfRun.setTextPosition(500);

        document.write(outputStream);
        outputStream.close();
    }
}
