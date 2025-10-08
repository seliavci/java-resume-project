# java-resume-project
Java ile nesne yönelimli programlama kullanılarak hazırlanmış PDF özgeçmiş uygulaması.


# Java Resume PDF Generator

## Proje Amacı
Bu proje, Java dili kullanılarak hazırlanmış ve nesne yönelimli programlama (OOP) prensiplerine uygun bir PDF özgeçmiş oluşturma uygulamasıdır.
Uygulama, kullanıcı bilgilerini, kısa özeti, üç iş deneyimini ve bir profil fotoğrafını içeren bir PDF dosyası üretir.

---

## Kullanılan Teknolojiler ve Araçlar
- **Java 8 veya üzeri**
- **iText 5** PDF kütüphanesi
- **IntelliJ IDEA** IDE
- Git ve GitHub (versiyon kontrol ve proje paylaşımı)

---

## Proje Yapısı
JavaResumeProject/
├─ src/main/java/com/selinavci/
│ ├─ Main.java
│ ├─ Person.java
│ ├─ JobExperience.java
│ └─ ResumeBuilder.java
├─ src/main/resources/
│ └─ foto.jpg
├─ ozgecmis.pdf
└─ README.md



- `Person.java` → Kişisel bilgileri tutar.  
- `JobExperience.java` → İş deneyimlerini tutar.  
- `ResumeBuilder.java` → PDF oluşturmayı yönetir.  
- `Main.java` → Programı başlatır ve nesneleri oluşturur.  
- `foto.jpg` → Özgeçmiş fotoğrafı.  
- `ozgecmis.pdf` → Program çalıştırıldığında üretilen PDF.

---

## Nasıl Çalışır
1. `Main.java` içinde kişisel bilgiler ve iş deneyimleri nesneleri oluşturulur.  
2. `ResumeBuilder` sınıfı kullanılarak PDF oluşturulur.  
3. PDF’de başlık, kişisel bilgiler, özet, iş deneyimleri ve fotoğraf yer alır.  
4. Sonuç olarak `ozgecmis.pdf` dosyası proje dizininde oluşur.

---

## Özellikler
- Nesne yönelimli yapı sayesinde modüler ve kolay bakım yapılabilir.  
- Fotoğraf ve iş deneyimleri kolayca eklenip değiştirilebilir.  
- PDF formatında çıktılar kullanıcı dostudur.

---

## Kurulum ve Çalıştırma
1. Projeyi klonlayın:  
```bash
git clone https://github.com/kullaniciAdi/JavaResumeProject.git


Ödev Notları

Bu proje, Java ile nesne yönelimli programlama ve PDF oluşturma konularını göstermek için hazırlanmıştır.

Proje public repo olarak GitHub’a yüklenmiştir ve ödev kriterlerine uygundur.








































