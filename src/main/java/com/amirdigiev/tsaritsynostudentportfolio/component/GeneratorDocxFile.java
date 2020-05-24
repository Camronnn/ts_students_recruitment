package com.amirdigiev.tsaritsynostudentportfolio.component;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import org.apache.poi.util.Units;
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

    @Value("${application.avatar-folder}")
    private String avatarFolder;

    public void createPortfolio(Student student,
                                String education,
                                String collegeSpecialty,
                                String startTraining,
                                String endTraining,
                                String additionalEducation
                                ) throws Exception
    {
        String name = student.getUser().getName();
        String surname = student.getUser().getSurname();
        String patronymic = student.getUser().getPatronymic();
        String avatar = student.getUser().getAvatar();
        LocalDate birthday = student.getUser().getBirthday();
        String number = student.getUser().getNumber();
        String email = student.getUser().getEmail();

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

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();

        FileInputStream inputStream = new FileInputStream(avatarFolder + File.separator + avatar);

        if (avatar.endsWith(".png")) {
            paragraph.createRun().addPicture(
                    inputStream,
                    XWPFDocument.PICTURE_TYPE_PNG,
                    avatar,
                    Units.toEMU(250),
                    Units.toEMU(250)
            );
        } else {
            paragraph.createRun().addPicture(
                    inputStream,
                    XWPFDocument.PICTURE_TYPE_JPEG,
                    avatar,
                    Units.toEMU(250),
                    Units.toEMU(250));
        }
        inputStream.close();

        paragraph = document.createParagraph();

//      Fullname
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Фамилия, имя, отчество: ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setText(surname + " " + name + " " + patronymic);

        paragraph = document.createParagraph();

//      Birthday
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("Дата рождения: ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString() + " г.");

        paragraph = document.createParagraph();

//      Education
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Образование (какую школу окончил, год окончания): ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(education);

        paragraph = document.createParagraph();

//      Specialty
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Специальность, получаемая в колледже: ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(collegeSpecialty);

        paragraph = document.createParagraph();

//      Timing training
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Сроки обучения по специальности: ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(
                startTraining.replaceAll("-", ".")
                + " - "
                + endTraining.replaceAll("-", "."));

        paragraph = document.createParagraph();

//      Phone number
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Контактный телефон: ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(number);

        paragraph = document.createParagraph();

//      Email
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Email: ");

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(email);

        paragraph = document.createParagraph();

//      Additional education
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText(
                "Сведения о дополнительном образовании " +
                        "(музыкальная, художественная, спортивная, " +
                        "школа иностранных языков  или иная школа): "
        );

        run = paragraph.createRun();
        run.setFontSize(14);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily("Times New Roman");
        run.setText(additionalEducation);


        document.write(outputStream);
        outputStream.close();
    }
}
