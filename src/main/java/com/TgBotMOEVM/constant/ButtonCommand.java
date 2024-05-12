package com.TgBotMOEVM.constant;


import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface ButtonCommand {

    BotCommand START = new BotCommand("/start", "Start");

    BotCommand TO_MAIN_MENU = new BotCommand("/toMainMenu", "В главное меню");
    BotCommand UNIVERSITY = new BotCommand("/university", "О структуре университета");
    BotCommand LINKS = new BotCommand("/links", "Интернет-ресурсы");

    BotCommand EMAIL = new BotCommand("/email", "Электронная почта");

    BotCommand RECTOR = new BotCommand("/rector", "Ректор");
    BotCommand DEANERY = new BotCommand("/deanery", "Деканат");
    BotCommand MOEVM = new BotCommand("/moevm", "Кафедра МО ЭВМ");
    BotCommand DEPARTMENTS = new BotCommand("/departments", "Другие кафедры");
    BotCommand UNION = new BotCommand("/union", "Профком");

    BotCommand LOCATIONS = new BotCommand("/locations", "Важные локации");
    BotCommand HEALTH = new BotCommand("/health", "Здоровье");
    BotCommand MILITARY = new BotCommand("/military", "Военный стол");
    BotCommand EDUCATIONAL = new BotCommand("/educational", "Об учёбе");
    BotCommand ACTIVITIES = new BotCommand("/activities", "Внеучебная деятельность");
    BotCommand NEXT = new BotCommand("/next", "Следующая страница");

    BotCommand HEALTH_CENTER = new BotCommand("/health_center", "Здравпункт");
    BotCommand CLINICS = new BotCommand("/clinics", "Поликлиники");
    BotCommand FOREIGNERS = new BotCommand("/foreigners", "Для иностранцев");

    BotCommand COURSEWORK = new BotCommand("/coursework", "Курсовые");
    BotCommand TESTS = new BotCommand("/tests", "Зачеты");
    BotCommand EXAMS = new BotCommand("/exams", "Экзамены");
    BotCommand TEST_WEEK = new BotCommand("/test_week", "Зачётная неделя");
    BotCommand QUALITY_DAY = new BotCommand("/quality_day", "День качества");
    BotCommand ADD_SESSION = new BotCommand("/add_session", "Доп. сессия");
    BotCommand COMMISSION = new BotCommand("/commission", "Комиссия");

    BotCommand OFFICE = new BotCommand("/office", "Студенческая канцелярия");
    BotCommand SOCIAL_DEPARTMENT = new BotCommand("/social_department", "Приёмная комиссия");
    BotCommand PAID = new BotCommand("/paid", "Платное обучение");
    BotCommand DORMITORY = new BotCommand("/dormitory", "Общежитие");
    BotCommand FOREIGN_CITIZENS = new BotCommand("/foreign_citizens", "Иностранным гражданам");
    BotCommand LIBRARY = new BotCommand("/library", "Библиотека");



}
