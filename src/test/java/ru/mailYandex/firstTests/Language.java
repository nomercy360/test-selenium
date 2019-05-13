package ru.mailYandex.firstTests;

public enum Language{
    RUS("ru", "Русский"),
    ENG("en", "English");
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
