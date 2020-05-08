package com.amirdigiev.tsaritsynostudentportfolio.component;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@SuppressWarnings("Duplicates")
public class GeneratorDocxFile {

    @Value("${application.docx-folder}")
    private String docxFolder;

    public void createPortfolio(Student student) throws Exception {
        String name = student.getUser().getName();
        String surname = student.getUser().getSurname();
        String patronymic = student.getUser().getPatronymic();
        String avatar = student.getUser().getAvatar();
        LocalDate birthday = student.getUser().getBirthday();
        String number = student.getUser().getNumber();
        String email = student.getUser().getMail();

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph;
        XWPFRun run;

        FileOutputStream outputStream = new FileOutputStream(new File(
                docxFolder + File.separator + name + " " + surname + " портфолио.docx"
        ));

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();
        run.setBold(true);
        run.setCapitalized(true);
        run.setText("портфолио");
        run.setFontSize(16);
        run.setFontFamily("Times New Roman");

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();
        run.setBold(true);
        run.setCapitalized(true);
        run.setText("студента");
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();
        run.setBold(true);
        run.setCapitalized(true);
        run.setFontSize(14);
        run.setText(
                "государственного бюджетного проффесионального образовательного учреждения московского" +
                        " колледжа управления, гостиничного бизнеса и информационных технологий «Царицыно»"
        );
        run.setFontSize(14);
        run.setItalic(true);
        run.setFontFamily("Times New Roman");

//        XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
//        if (headerFooterPolicy == null)
//            headerFooterPolicy = document.createHeaderFooterPolicy();
//
//        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
//
//        paragraph = header.createParagraph();
//        paragraph.setAlignment(ParagraphAlignment.CENTER);
//
//        run = paragraph.createRun();
//        run.setText("The Header:");
//
//        FileInputStream inputStream = new FileInputStream(docxFolder + File.separator + avatar);
//
//        if (avatar.endsWith(".png"))
//            paragraph.createRun().addPicture(
//                    inputStream,
//                    XWPFDocument.PICTURE_TYPE_PNG,
//                    avatar,
//                    Units.toEMU(200),
//                    Units.toEMU(200)
//            );
//        else
//            paragraph.createRun().addPicture(
//                    inputStream,
//                    XWPFDocument.PICTURE_TYPE_JPEG,
//                    avatar,
//                    Units.toEMU(200),
//                    Units.toEMU(200));
//        inputStream.close();


        paragraph = document.createParagraph();

//      Fullname
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Фамилия, имя, отчество: " + surname + " " + name + " " + patronymic);
//        run.setUnderline(UnderlinePatterns.SINGLE);

        paragraph = document.createParagraph();

//      Birthday
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Дата рождения: "
                + birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString() + " г.");

        paragraph = document.createParagraph();

//      Education
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Образование (какую школу окончил, год окончания):");

        paragraph = document.createParagraph();

//      Specialty
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Специальность, получаемая в колледже:");

        paragraph = document.createParagraph();

//      Timing training
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Сроки обучения по специальности:");

        paragraph = document.createParagraph();

//      Phone number
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Контактный телефон: " + number);

        paragraph = document.createParagraph();

//      Email
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Email: " + email);

        paragraph = document.createParagraph();

//      Additional education
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText(
                "Сведения о дополнительном образовании " +
                        "(музыкальная, художественная, спортивная, " +
                        "школа иностранных языков  или иная школа):"
        );

        document.write(outputStream);
        outputStream.close();
    }
}
