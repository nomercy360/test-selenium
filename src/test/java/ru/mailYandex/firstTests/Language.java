package ru.mailYandex.firstTests;

public enum Language{
    RUS("//a[@data-params='lang=ru']", "Русский"),
    ENG("//a[@data-params='lang=en']", "English");
    private String xpath;
    private String languageName;
    Language(String  xpath, String languageName) {
        this.xpath = xpath;
        this.languageName = languageName;
    }
    public String getPath() {
        return xpath;
    }
    public String getLanguageName() {

        return languageName;
    }
}
