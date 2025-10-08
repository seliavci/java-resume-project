package com.selinavci;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String summary;

    public Person(String name, String email, String phone, String address, String summary) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.summary = summary;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getSummary() { return summary; }
}

class JobExperience {
    private String company;
    private String position;
    private String duration;
    private String description;

    public JobExperience(String company, String position, String duration, String description) {
        this.company = company;
        this.position = position;
        this.duration = duration;
        this.description = description;
    }

    public String getCompany() { return company; }
    public String getPosition() { return position; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
}

class ResumeBuilder {
    private Person person;
    private List<JobExperience> experiences;
    private String photoPath;

    public ResumeBuilder(Person person, List<JobExperience> experiences, String photoPath) {
        this.person = person;
        this.experiences = experiences;
        this.photoPath = photoPath;
    }

    private BaseFont loadBaseFont() {
        try {
            return BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            throw new RuntimeException("Font yüklenemedi: " + e.getMessage(), e);
        }
    }

    private Image loadImageIfExists(String providedPath) {
        try {
            File f = new File(providedPath);
            if (f.exists()) {
                return Image.getInstance(f.getAbsolutePath());
            }
        } catch (Exception ignored) {}
        return null;
    }

    public void build(String filePath) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            BaseFont bf = loadBaseFont();
            Font titleFont = new Font(bf, 18, Font.BOLD);
            Font sectionFont = new Font(bf, 14, Font.BOLD);
            Font normalFont = new Font(bf, 11, Font.NORMAL);

            // ÜST KISIM: metin + fotoğraf
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{0.75f, 0.25f});

            PdfPCell left = new PdfPCell();
            left.setBorder(Rectangle.NO_BORDER);
            left.addElement(new Paragraph(person.getName(), titleFont));
            left.addElement(new Paragraph(person.getEmail() + " | " + person.getPhone(), normalFont));
            left.addElement(new Paragraph(person.getAddress(), normalFont));
            headerTable.addCell(left);

            PdfPCell right = new PdfPCell();
            right.setBorder(Rectangle.NO_BORDER);
            Image img = loadImageIfExists(photoPath);
            if (img != null) {
                img.scaleToFit(120, 120);
                img.setAlignment(Image.ALIGN_RIGHT);
                right.addElement(img);
            }
            headerTable.addCell(right);

            document.add(headerTable);
            document.add(Chunk.NEWLINE);

            // Özet
            document.add(new Paragraph("Kisa Özet", sectionFont));
            document.add(new Paragraph(person.getSummary(), normalFont));
            document.add(Chunk.NEWLINE);

            // İş Deneyimleri
            document.add(new Paragraph("Deneyimlerim", sectionFont));
            for (int i = 0; i < experiences.size(); i++) {
                JobExperience ex = experiences.get(i);
                document.add(new Paragraph(
                        (i + 1) + ". " + ex.getCompany() + " - " + ex.getPosition() + " (" + ex.getDuration() + ")",
                        normalFont
                ));
                document.add(new Paragraph(ex.getDescription(), normalFont));
                document.add(Chunk.NEWLINE);
            }

            document.close();
            System.out.println("✅ Özgeçmiş PDF başarıyla oluşturuldu: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
            if (document.isOpen()) document.close();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person(
                "Selin Avci - Yazilim Mühendisligi 2. Sinif Öğrencisi",
                "selinavci2004@icloud.com",
                "530 372 82 82",
                "Bursa, Türkiye",
                "Yazilim mühendisligi ögrencisiyim. Java ve nesneye yönelik programlama konularıinda kendimi gelistiriyorum. Takim çalismasina yatkin, analitik düsünebilen bir gelistirici adayiyim."
        );

        List<JobExperience> experiences = new ArrayList<>();
        experiences.add(new JobExperience(
                "KodLab Yazilim",
                "Stajyer Java Gelistirici",
                "06/2024 - 09/2024",
                "Java kullanarak masaüstü uygulamalar gelistirdim. OOP prensipleriyle modüler yapı tasarladim ve PDF çikti alma özelligi üzerinde çalistim."
        ));
        experiences.add(new JobExperience(
                "ByteWorks Teknoloji",
                "Backend Gelistirici Adayi",
                "01/2024 - 05/2024",
                "Spring Boot kullanarak RESTful servisler gelistirdim. Veritabani baglantilari ve katmanli mimari konularinda deneyim kazandim."
        ));
        experiences.add(new JobExperience(
                "TechNova Proje Ekibi",
                "Takim Projesi Üyesi",
                "10/2023 - 12/2023",
                "Üniversite proje ekibinde grup tabanli ögrenci yönetim sistemi gelistirdik. Veri modelleme ve raporlama bilesenlerinden sorumluydum."
        ));

        String photoPath = "/Users/selinavci/IdeaProjects/ozgecmis/src/main/resources/foto.jpeg";

        ResumeBuilder builder = new ResumeBuilder(person, experiences, photoPath);
        builder.build("ozgecmis.pdf");
    }
}
