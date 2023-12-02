package src.main.java.org.example;

import java.util.List;

public class Option {
    private int code;
    private String message;
    private int ChatbotCodeLink;
    private int InitialFlowCodeLink;
    private List<String> Keyword;

    public Option(int code, String message, int chatbotCodeLink, int initialFlowCodeLink, List<String> keyword) {
        this.code = code;
        this.message = message;
        ChatbotCodeLink = chatbotCodeLink;
        InitialFlowCodeLink = initialFlowCodeLink;
        Keyword = keyword;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChatbotCodeLink() {
        return ChatbotCodeLink;
    }

    public void setChatbotCodeLink(int chatbotCodeLink) {
        ChatbotCodeLink = chatbotCodeLink;
    }

    public int getInitialFlowCodeLink() {
        return InitialFlowCodeLink;
    }

    public void setInitialFlowCodeLink(int initialFlowCodeLink) {
        InitialFlowCodeLink = initialFlowCodeLink;
    }

    public List<String> getKeyword() {
        return Keyword;
    }

    public void setKeyword(List<String> keyword) {
        Keyword = keyword;
    }
}
