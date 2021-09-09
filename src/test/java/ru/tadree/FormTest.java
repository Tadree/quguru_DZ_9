package ru.tadree;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;


public class FormTest {

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = faker.demographic().sex();
    String phone = faker.numerify("##########");
    String day = "1";
    String month = "January";
    String year = "2000";
    String subject = "Biology";
    String hobby = "Music";
    String picture = "DZ2.png";
    String currentAddress = faker.address().fullAddress();
    String state = "Uttar Pradesh";
    String city = "Agra";


    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest () {

        practiceFormPage.openPage();

        //заполнение формы
        practiceFormPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhone(phone)
                .setDateOfBirth("01","January", "2000")
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState("Uttar Pradesh")
                .setCity("Agra")
                .submit();

        //проверка введенных данных
        practiceFormPage.checkResultsValue(firstName + " " + lastName)
                .checkResultsValue(email)
                .checkResultsValue(gender)
                .checkResultsValue(phone)
                .checkResultsValue(day + " " + month + "," + year)
                .checkResultsValue(subject)
                .checkResultsValue(hobby)
                .checkResultsValue(picture)
                .checkResultsValue(currentAddress)
                .checkResultsValue(state + " " + city);

    }
}
